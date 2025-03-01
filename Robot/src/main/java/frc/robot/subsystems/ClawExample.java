/**
 * Phoenix Software License Agreement
 *
 * Copyright (C) Cross The Road Electronics.  All rights
 * reserved.
 * 
 * Cross The Road Electronics (CTRE) licenses to you the right to 
 * use, publish, and distribute copies of CRF (Cross The Road) firmware files (*.crf) and 
 * Phoenix Software API Libraries ONLY when in use with CTR Electronics hardware products
 * as well as the FRC roboRIO when in use in FRC Competition.
 * 
 * THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT
 * LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT SHALL
 * CROSS THE ROAD ELECTRONICS BE LIABLE FOR ANY INCIDENTAL, SPECIAL, 
 * INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF
 * PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR SERVICES, ANY CLAIMS
 * BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE
 * THEREOF), ANY CLAIMS FOR INDEMNITY OR CONTRIBUTION, OR OTHER
 * SIMILAR COSTS, WHETHER ASSERTED ON THE BASIS OF CONTRACT, TORT
 * (INCLUDING NEGLIGENCE), BREACH OF WARRANTY, OR OTHERWISE
 */

/**
 * 
 * This gist is a modification to Robot.java within the example project provided by CTRE
 *   (modified for use with the TalonFX(Falon500s).
 * For other files required to compile this project see:
 *   https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java/MotionMagic_AuxStraightPigeon
 *
 *
 * ------------------------------------------------------------------------------------------
 *
 * Description:
 *
 * The MotionMagic_AuxStraightPigeon example demonstrates the new Talon/Victor auxiliary and 
 * remote features to peform complex closed loops. This example has the robot performing 
 * Motion Magic with an auxiliary closed loop on Pigeon Yaw to keep the robot straight.
 * 
 * This example uses:
 * - 2x Quad Encoders, One on both sides of robot for Primary Closed Loop on Position
 * A Talon/Victor calculates the distance by taking the sum of both sensors and dividing it by 2.
 * - Pigeon IMU wired on CAN Bus for Auxiliary Closed Loop on Yaw
 * 
 * This example has two modes of operation, which can be switched between with Button 2.
 * 1.) Arcade Drive
 * 2.) Motion Magic with Quadrature Encoders and Drive Straight With Pigeon yaw
 * 
 * Controls:
 * Button 1: When pressed, zero sensors. Set quadrature encoders' ostions + Pigeon yaw to 0.
 * Button 2: When pressed, toggle between Arcade Drive and Motion Magic
 * 	When toggling into Motion Magic, the current heading is saved and used as the 
 * 	auxiliary closed loop target. Can be changed by toggling out and in again.
 * Button 5(Left shoulder): When pushed, will decrement the smoothing of the motion magic down to 0
 * Button 6(Right shoulder): When pushed, will increment the smoothing of the motion magic up to 8
 * Left Joystick Y-Axis: 
 * 	+ Arcade Drive: Drive robot forward and reverse
 * 	+ Motion Magic: Servo robot forward and reverse [-6, 6] rotations
 * Right Joystick X-Axis: 
 *  + Arcade Drive: Turn robot in left and right direction
 *  + Motion Magic: Not used
 * 
 * Gains for Motion Magic and Auxiliary may need to be adjusted in Constants.java
 * 
 * Supported Version:
 * - Talon SRX: 4.00
 * - Victor SPX: 4.00
 * - Pigeon IMU: 4.00
 * - CANifier: 4.00
 */
package org.team2168;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
	/** Hardware */
	private TalonFX ClawMotorController;
    this.ClawMotorController = new TalonFX(1);

	// private TalonFX _rightMaster;
    // this._rightMaster = new TalonFX(15);s
	
	/** Latched values to detect on-press events for buttons */
	boolean[] _previous_currentBtns = new boolean[Constants.kNumButtonsPlusOne];
	boolean[] _currentBtns = new boolean[Constants.kNumButtonsPlusOne];
	
	/** Tracking variables */
	boolean _firstCall = false;
	boolean _state = false;
	double _targetAngle = 0;

	/** How much smoothing [0,8] to use during MotionMagic */
	int _smoothing;

	@Override
	public void robotInit() {
		/* Not used in this project */
	}

	@Override
	public void teleopInit(){
		/* Disable all motor controllers */
		ClawMotorController.set(ControlMode.PercentOutput, 0);

		/* Factory Default all hardware to prevent unexpected behavior */
		ClawMotorController.configFactoryDefault();
		
		/* Set Neutral Mode */
		ClawMotorController.setNeutralMode(NeutralMode.Brake);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor as local QuadEncoder */
		ClawMotorController.configSelectedFeedbackSensor(	FeedbackDevice.IntegratedSensor,				// Local Feedback Source
													Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
													Constants.kTimeoutMs);					// Configuration Timeout

		/* Configure the Pigeon IMU to the other remote slot available on the right Talon */
		_rightMaster.configRemoteFeedbackFilter(_pidgey.getDeviceID(),
												RemoteSensorSource.Pigeon_Yaw,
												Constants.REMOTE_1,	
												Constants.kTimeoutMs);
		
		
		/* Configure output and sensor direction */
    ClawMotorController.setInverted(false);
    ClawMotorController.setSensorPhase(false);
		
		/* Set status frame periods to ensure we don't have stale data */
		ClawMotorController.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, Constants.kTimeoutMs);
		_pidgey.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR , 5, Constants.kTimeoutMs);

		/* Configure neutral deadband */
		_rightMaster.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
		ClawMotorController.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
		
		/* Motion Magic Configurations */
		// _rightMaster.configMotionAcceleration(2000, Constants.kTimeoutMs);
		// _rightMaster.configMotionCruiseVelocity(2000, Constants.kTimeoutMs);

		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		ClawMotorController.configPeakOutputForward(+1.0, Constants.kTimeoutMs);
		ClawMotorController.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);


		/* Initialize */
		_firstCall = true;
		_state = false;
		_rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10);
		zeroSensors();
	}
	




	@Override
	public void teleopPeriodic() {
		/* Gamepad processing */
		double forward = -1 * _gamepad.getY();
		double turn = _gamepad.getTwist();
		forward = Deadband(forward);
        turn = Deadband(turn);
	
		/* Button processing for state toggle and sensor zeroing */
		getButtons(_currentBtns, _gamepad);
		if(_currentBtns[2] && !_previous_currentBtns[2]){
			_state = !_state; 		// Toggle state
			_firstCall = true;		// State change, do first call operation
      _targetAngle = _rightMaster.getSelectedSensorPosition(1);
      System.out.print(_state);
		}else if (_currentBtns[1] && !_previous_currentBtns[1]) {
			zeroSensors();			// Zero Sensors
		}
		if(_currentBtns[5] && !_previous_currentBtns[5]) {
			_smoothing--; // Decrement smoothing
			if(_smoothing < 0) _smoothing = 0; // Cap smoothing
			_rightMaster.configMotionSCurveStrength(_smoothing);

			System.out.println("Smoothing value is: " + _smoothing);
		}
		if(_currentBtns[6] && !_previous_currentBtns[6]) {
			_smoothing++; // Increment smoothing
			if(_smoothing > 8) _smoothing = 8; // Cap smoothing
			_rightMaster.configMotionSCurveStrength(_smoothing);
			
			System.out.println("Smoothing value is: " + _smoothing);
		}
		System.arraycopy(_currentBtns, 0, _previous_currentBtns, 0, Constants.kNumButtonsPlusOne);
				
		if(!_state){
			if (_firstCall)
				System.out.println("This is Arcade Drive.\n");
			
			ClawMotorController.set(ControlMode.PercentOutput, forward, DemandType.ArbitraryFeedForward, +turn);
			_rightMaster.set(ControlMode.PercentOutput, forward, DemandType.ArbitraryFeedForward, -turn);
		}else{
			if (_firstCall) {
				System.out.println("This is Motion Magic with the Auxiliary PID using the Pigeon yaw.");
				System.out.println("Servo [-6,6] rotations while also maintaining a straight heading.\n");
				zeroDistance();
				
				/* Determine which slot affects which PID */
				_rightMaster.selectProfileSlot(Constants.kSlot_Distanc, Constants.PID_PRIMARY);
				_rightMaster.selectProfileSlot(Constants.kSlot_Turning, Constants.PID_TURN);
			}
			
			/* Calculate targets from gamepad inputs */
			double target_sensorUnits = 2 * forward * Constants.kSensorUnitsPerRotation * Constants.kRotationsToTravel;
      double target_turn = _targetAngle;
      System.out.println("Target-sensor units" + target_sensorUnits);
      System.out.println("commanded heading" + target_turn);
			
			/* Configured for MotionMagic on Quad Encoders' Sum and Auxiliary PID on Pigeon */
			_rightMaster.set(ControlMode.MotionMagic, target_sensorUnits, DemandType.AuxPID, target_turn);
			ClawMotorController.follow(_rightMaster, FollowerType.AuxOutput1);
		}
		_firstCall = false;
	}
	
	/** Zero all sensors, both Talons and Pigeon */
	void zeroSensors() {
		ClawMotorController.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
		_rightMaster.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
		_pidgey.setYaw(0, Constants.kTimeoutMs);
		_pidgey.setAccumZAngle(0, Constants.kTimeoutMs);
		System.out.println("[Integrated Encoders + Pigeon] All sensors are zeroed.\n");
	}
	
	/** Zero QuadEncoders, used to reset position when initializing Motion Magic */
	void zeroDistance(){
		ClawMotorController.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
		_rightMaster.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
		System.out.println("[Integrated Encoders] All encoders are zeroed.\n");
	}
	
	/** Deadband 5 percent, used on the gamepad */
	double Deadband(double value) {
		/* Upper deadband */
		if (value >= +0.5) 
			return value;
		
		/* Lower deadband */
		if (value <= -0.5)
			return value;
		
		/* Outside deadband */
		return 0;
	}
	
	/** Gets all buttons from gamepad */
	void getButtons(boolean[] _currentBtns, Joystick gamepad) {
		for (int i = 1; i < Constants.kNumButtonsPlusOne; ++i) {
			_currentBtns[i] = gamepad.getRawButton(i);
		}
	}
}