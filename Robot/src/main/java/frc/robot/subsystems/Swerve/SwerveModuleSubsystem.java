// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Swerve;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.SwerveDriveConstants;

import com.revrobotics.spark.SparkLowLevel;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;


public class SwerveModuleSubsystem extends SubsystemBase {
  
  private TalonSRX drive;
  private SparkMax turn;
  private RelativeEncoder turnEncoder;
  private ProfiledPIDController turningPIDController;
  private double maxOut;
  private PIDController pidController;

  /**
  * Construct module, pid, and start encoder
  *
  * @param driveID ID of the TalonFX, should be 2-5
  * @param steerID ID of the SparkMax (turn), should be 22-55
  */
  public SwerveModuleSubsystem(int driveID, int steerID, double P, double I, double D) {
    super();

    this.drive = new TalonSRX(driveID);
    this.drive.setNeutralMode(NeutralMode.Brake); // Stop wheel from moving when weren't not driving

    this.turn = new SparkMax(steerID, SparkLowLevel.MotorType.kBrushless);
    this.turnEncoder = this.turn.getEncoder(); // Zero wheels before power on



    this.turningPIDController = new ProfiledPIDController(
      P, I, D,
      new TrapezoidProfile.Constraints(
        SwerveDriveConstants.MODULE_MAX_ANGULAR_VELOCITY,
        SwerveDriveConstants.MODULE_MAX_ANGULAR_ACCELERATION
      )

    );

    SmartDashboard.putNumber("Swerve P", P);
    SmartDashboard.putNumber("Swerve I", I);
    SmartDashboard.putNumber("Swerve D", D);

    this.turningPIDController.enableContinuousInput(-Math.PI, Math.PI);

    // Default maxOutput to 0
    this.maxOut = 0;

    SmartDashboard.putNumber("Gear Ratio", SwerveDriveConstants.SWERVE_TURN_GEAR_RATIO);
  }

  /**
  * Uses the encoder to calculate the current direction of the module
  *
  * @return The Rotation2d of the module
  */
  public Rotation2d getEncoder() {
    final double tau = Math.PI * 2;
    final double gearRatio = SmartDashboard.getNumber("Gear Ratio", 0);
    return new Rotation2d(this.turnEncoder.getPosition() * tau * gearRatio);
  }

  /**
  * Used to limit the speed of the robot
  *
  * @param value 0 - 1, max speed of the motor, 0.5 would be half speed
  */
  public void setMaxOut(double value) {
    this.maxOut = value;
  }

  /**
  * Uses a SwerveModuleState outputed by kinematics.
  * This of course sets the state, so it will keep running after the function is called once.
  *
  * @param desiredState The desired state of the module from kinematics
  */
  public void setDesiredState(final SwerveModuleState desiredState) {

    final double newP = SmartDashboard.getEntry("Swerve P").getDouble(0);
    final double newI = SmartDashboard.getEntry("Swerve I").getDouble(0);
    final double newD = SmartDashboard.getEntry("Swerve D").getDouble(0);
    this.turningPIDController.setPID(newP, newI, newD);

    final double tau = Math.PI * 2;
    final double gearRatio = SmartDashboard.getNumber("Gear Ratio", 0);

    // Gets the current angle of the module
    final Rotation2d encoderRotation = this.getEncoder();

    SwerveModuleState state = desiredState;

    state.angle = desiredState.angle.times(-1); // Inverts the angle
    state.optimize(encoderRotation); // Optimizes out unnessary rotatation when there's a faster way
    state.speedMetersPerSecond *= state.angle.minus(encoderRotation).getCos();

    final double driveOuput = state.speedMetersPerSecond;

    // Uses PID to tell the SparkMax's how much to rotate
    // System.out.printf("---------------------\nTurn Current Pos Raw: %.6f\n", this.turnEncoder.getPosition());
    // System.out.printf("Turn Current Pos Rad: %.6f\n", this.turnEncoder.getPosition() * gearRatio * tau);
    // System.out.printf("Turn Target Rad: %.6f\n", state.angle.getRadians());
    final double turnOutput = this.turningPIDController.calculate(
      encoderRotation.getRadians(), state.angle.getRadians()
    );
    
    // Actually sets the speed of the motors and how much they need to rotate
    final double maxOut = SwerveDriveConstants.SWERVE_MAX_OUTPUT;
    this.drive.set(TalonSRXControlMode.PercentOutput, MathUtil.clamp(driveOuput, -maxOut, maxOut));
    this.turn.setVoltage(turnOutput);
  }
}
