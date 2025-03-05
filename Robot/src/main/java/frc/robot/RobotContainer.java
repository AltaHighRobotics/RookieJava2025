// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.InputConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ClawRotationCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.commands.ClawRotationCommand.ClawLevel;
import frc.robot.commands.FollowApriltagCommand;
import frc.robot.commands.ResetOrientationCommand;
import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;


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

  // private SwerveDriveSubsystem drive;
  // private ApriltagSubsystem apriltagSubsystem;
  private ClawSubsystem clawSubsystem;

  public RobotContainer() {
    this.driverController = new Joystick(InputConstants.DRIVER_CONTROLLER_PORT);

    // this.drive = new SwerveDriveSubsystem();
    // this.apriltagSubsystem = new ApriltagSubsystem();
    this.clawSubsystem = new ClawSubsystem();

    configureBindings();

    // this.drive.setDefaultCommand(new SwerveDriveCommand(drive, driverController));
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

    // JoystickButton gyroResetButton = new JoystickButton(driverController, 5);
    // gyroResetButton.onTrue(new ResetOrientationCommand(this.drive));

    // JoystickButton followApriltagButton = new JoystickButton(driverController, 4);
    // followApriltagButton.onTrue(new FollowApriltagCommand(this.drive, this.apriltagSubsystem));

    // FOR CLAW COMMANDS: TURN "whileTrue" BACK TO "onTrue" WHEN DONE TESTING //

    // Claw commands for testing 1
    JoystickButton TestButton1 = new JoystickButton(driverController, 1);
    TestButton1.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawLevel.POSITION1)); // Down

    JoystickButton TestButton2 = new JoystickButton(driverController, 2);
    TestButton2.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawLevel.POSITION2)); // Forward
    
    JoystickButton TestButton3 = new JoystickButton(driverController, 3);
    TestButton3.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawLevel.POSITION3)); // Up
        
    JoystickButton TestButton4 = new JoystickButton(driverController, 4);
    TestButton4.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawLevel.POSITION4)); // Backwards (DO NOT POWER OFF IN THIS POSITION)
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
