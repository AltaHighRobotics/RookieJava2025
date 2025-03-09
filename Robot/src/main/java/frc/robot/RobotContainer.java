// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.cert.CertPathValidatorException.BasicReason;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.InputConstants;
import frc.robot.Constants.SwerveDriveConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.FullArmCommand;
import frc.robot.commands.PauseArm;
import frc.robot.commands.FullArmCommand.ArmState;
import frc.robot.commands.SuckNBlowCommands.BlowCommand;
import frc.robot.commands.SuckNBlowCommands.SuckCommand;
import frc.robot.commands.Swerve.EncoderTestCommand;
import frc.robot.commands.Swerve.ResetOrientationCommand;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.commands.claw.ClawGoToTarget;
import frc.robot.commands.claw.ClawStopCommand;
import frc.robot.commands.claw.ClawTickBackwardCommand;
import frc.robot.commands.claw.ClawTickForwardCommand;
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.commands.elevator.ElevatorTickBackwards;
import frc.robot.commands.elevator.ElevatorTickUpwards;
import frc.robot.commands.elevator.StopElevatorCommand;
import frc.robot.subsystems.SuckNBlowSubsystem;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;
import frc.robot.subsystems.Swerve.TESTSwervesusbsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ClawSubsystem;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController;

  private SwerveDriveSubsystem drive;
  private SuckNBlowSubsystem suckNBlowSubsystem;
  private ElevatorSubsystem elevatorSubsystem;
  private ClawSubsystem clawSubsystem;

  private TESTSwervesusbsystem swerveTestSubsystem;

  public RobotContainer() {
    this.driverController = new Joystick(InputConstants.DRIVER_CONTROLLER_PORT);

    // this.drive = new SwerveDriveSubsystem();
    // this.suckNBlowSubsystem = new SuckNBlowSubsystem();
    // this.elevatorSubsystem = new ElevatorSubsystem();
    // this.clawSubsystem = new ClawSubsystem();

    this.swerveTestSubsystem = new TESTSwervesusbsystem(SwerveDriveConstants.FRONT_RIGHT_TURN_ID);

    configureBindings();

    // this.drive.setDefaultCommand(new SwerveDriveCommand(drive, driverController));
    // this.elevatorSubsystem.setDefaultCommand(new ElevatorGoToTarget(elevatorSubsystem));
    // this.clawSubsystem.setDefaultCommand(new ClawGoToTarget(clawSubsystem));
  }

  private void addStateBinding(int buttonNumber, ArmState armState, ArmState armState2) {
    JoystickButton stateButton = new JoystickButton(driverController, buttonNumber);
    // stateButton.whileFalse(new PauseArm(elevatorSubsystem, clawSubsystem));
    stateButton.whileTrue(new FullArmCommand(
      this.elevatorSubsystem, this.clawSubsystem, armState, armState2, this.driverController
    ));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   */
  private void configureBindings() {
    // JoystickButton gyroResetButton = new JoystickButton(driverController, 5);
    // gyroResetButton.onTrue(new ResetOrientationCommand(this.drive));

    // JoystickButton myButton = new JoystickButton(driverController, 1);
    // myButton.whileTrue(new EncoderTestCommand(swerveTestSubsystem));

    // Suck N Blow
    JoystickButton suckButton = new JoystickButton(driverController, 1);
    JoystickButton blowButton = new JoystickButton(driverController, 2);
    suckButton.whileTrue(new SuckCommand(this.suckNBlowSubsystem));
    blowButton.whileTrue(new BlowCommand(this.suckNBlowSubsystem));

    // Elevator Manual
    JoystickButton elevatorUPButton = new JoystickButton(driverController, 5);
    JoystickButton elevatorDownButton = new JoystickButton(driverController, 3);
    elevatorUPButton.whileTrue(new ElevatorTickUpwards(this.elevatorSubsystem));
    elevatorDownButton.whileTrue(new ElevatorTickBackwards(this.elevatorSubsystem));
    
    // Claw Manual
    JoystickButton clawForwardButton = new JoystickButton(driverController, 6);
    JoystickButton clawBackwardButton = new JoystickButton(driverController, 4);
    clawForwardButton.whileTrue(new ClawTickForwardCommand(this.clawSubsystem));
    clawBackwardButton.whileTrue(new ClawTickBackwardCommand(this.clawSubsystem));
    
    // States
    addStateBinding(7, ArmState.STOWED, ArmState.CORAL_PICKUP); // Default state, also is the CORAL_CARRY
    addStateBinding(8, ArmState.BALL_PICKUP_1, ArmState.CORAL_SCORE_1);
    addStateBinding(9, ArmState.BALL_PICKUP_2, ArmState.CORAL_SCORE_2);
    addStateBinding(10, ArmState.BALL_SCORE_1, ArmState.CORAL_SCORE_3);
    addStateBinding(11, ArmState.BALL_SCORE_2, ArmState.CORAL_SCORE_4);
    addStateBinding(12, ArmState.BALL_CARRY, ArmState.BLOW);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.stationAlign(drive, this.apriltagSubsystem);
    return null;
  }
}
