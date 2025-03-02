// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.InputConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.commands.FollowApriltagCommand;
import frc.robot.commands.ResetOrientationCommand;
import frc.robot.commands.TESTINGClawRotationCommand;
import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
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
  private ApriltagSubsystem apriltagSubsystem;
  private ClawSubsystem clawSubsystem;

  public RobotContainer() {
    this.driverController = new Joystick(InputConstants.DRIVER_CONTROLLER_PORT);

    this.drive = new SwerveDriveSubsystem();
    this.apriltagSubsystem = new ApriltagSubsystem();
    this.clawSubsystem = new ClawSubsystem();

    configureBindings();

    this.drive.setDefaultCommand(new SwerveDriveCommand(drive, driverController));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    // .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    JoystickButton gyroResetButton = new JoystickButton(driverController, 5);
    gyroResetButton.onTrue(new ResetOrientationCommand(this.drive));

    JoystickButton followApriltagButton = new JoystickButton(driverController, 4);
    followApriltagButton.onTrue(new FollowApriltagCommand(this.drive, this.apriltagSubsystem));

    // Claw commands
    JoystickButton MoveNinetyDegrees = new JoystickButton(driverController, 1);
    MoveZeroDegrees.onTrue(new TESTINGClawRotationCommand(this.clawSubsystem, 0)); //The "0" sets the position to the POSITION1 enum, 0 degrees
    JoystickButton MoveNinetyDegrees = new JoystickButton(driverController, 1);
    MoveNinetyDegrees.onTrue(new TESTINGClawRotationCommand(this.clawSubsystem, 1)); //The "1" sets the position to the POSITION2 enum, 90 degrees
    JoystickButton MoveNinetyDegrees = new JoystickButton(driverController, 1);
    MoveOne_EightyDegrees.onTrue(new TESTINGClawRotationCommand(this.clawSubsystem, 2)); //The "2" sets the position to the POSITION3 enum, 180 degrees
    JoystickButton MoveNinetyDegrees = new JoystickButton(driverController, 1);
    MoveTwo_SeventyDegrees.onTrue(new TESTINGClawRotationCommand(this.clawSubsystem, 3)); //The "0" sets the position to the POSITION4 enum, 270 degrees
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.stationAlign(drive, this.apriltagSubsystem);
  }
}
