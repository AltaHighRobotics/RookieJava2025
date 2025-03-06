// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.cert.CertPathValidatorException.BasicReason;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.InputConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.FullArmCommand;
import frc.robot.commands.FullArmCommand.ArmState;
import frc.robot.commands.SuckNBlowCommands.BlowCommand;
import frc.robot.commands.SuckNBlowCommands.SuckCommand;
import frc.robot.commands.Swerve.ResetOrientationCommand;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.commands.elevator.ElevatorHeightChangeTestCommand;
import frc.robot.commands.elevator.StopElevatorCommand;
import frc.robot.commands.elevator.ElevatorHeightChangeTestCommand.ElevatorHeightChangeDirection;
import frc.robot.subsystems.SuckNBlowSubsystem;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.ClawRotationCommand;
import frc.robot.commands.ClawRotationCommand.ClawDirection;
import frc.robot.subsystems.ApriltagSubsystem;
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

  public RobotContainer() {
    this.driverController = new Joystick(InputConstants.DRIVER_CONTROLLER_PORT);

    this.drive = new SwerveDriveSubsystem();
    this.suckNBlowSubsystem = new SuckNBlowSubsystem();
    this.elevatorSubsystem = new ElevatorSubsystem();
    this.clawSubsystem = new ClawSubsystem();

    configureBindings();

    this.drive.setDefaultCommand(new SwerveDriveCommand(drive, driverController));
  }

  private void addStateBinding(int buttonNumber, ArmState armState) {
    JoystickButton stateButton = new JoystickButton(driverController, buttonNumber);
    stateButton.whileTrue(new FullArmCommand(
      this.elevatorSubsystem, this.elevatorSubsystem, armState
    ));
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

    JoystickButton gyroResetButton = new JoystickButton(driverController, 7);
    gyroResetButton.onTrue(new ResetOrientationCommand(this.drive));

    // JoystickButton followApriltagButton = new JoystickButton(driverController, 4);
    // followApriltagButton.onTrue(new FollowApriltagCommand(this.drive, this.apriltagSubsystem));

    JoystickButton suckButton = new JoystickButton(driverController, 1);
    suckButton.whileTrue(new SuckCommand(this.suckNBlowSubsystem));

    JoystickButton blowButton = new JoystickButton(driverController, 2);
    blowButton.whileTrue(new BlowCommand(this.suckNBlowSubsystem));

    JoystickButton elevatorUPButton = new JoystickButton(driverController, 5);
    JoystickButton elevatorDownButton = new JoystickButton(driverController, 3);

    elevatorUPButton.whileFalse(new ElevatorHeightChangeTestCommand(this.elevatorSubsystem, ElevatorHeightChangeDirection.STOP));
    elevatorDownButton.whileFalse(new ElevatorHeightChangeTestCommand(this.elevatorSubsystem,  ElevatorHeightChangeDirection.STOP));
    elevatorUPButton.whileTrue(new ElevatorHeightChangeTestCommand(this.elevatorSubsystem, ElevatorHeightChangeDirection.UP));
    elevatorDownButton.whileTrue(new ElevatorHeightChangeTestCommand(this.elevatorSubsystem,  ElevatorHeightChangeDirection.DOWN));

    JoystickButton elevatorStopButton = new JoystickButton(driverController, 6);
    elevatorStopButton.whileTrue(new StopElevatorCommand(this.elevatorSubsystem));
    
    // Claw commands for testing 1
    JoystickButton TestButton1 = new JoystickButton(driverController, 6);
    JoystickButton TestButton2 = new JoystickButton(driverController, 4);

    TestButton1.whileFalse(new ClawRotationCommand(this.clawSubsystem, ClawDirection.STOP)); // Down
    TestButton2.whileFalse(new ClawRotationCommand(this.clawSubsystem, ClawDirection.STOP)); // Down
    TestButton1.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawDirection.FORWARDS)); // Down
    TestButton2.whileTrue(new ClawRotationCommand(this.clawSubsystem, ClawDirection.BACKWARDS)); // Forward POSITION)
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
