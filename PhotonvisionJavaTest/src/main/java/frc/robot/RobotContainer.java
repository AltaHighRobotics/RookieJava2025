// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.InputConstants;
import frc.robot.commands.AutonomusCommand;
import frc.robot.commands.FCDrive;
import frc.robot.commands.ResetOrientationCommand;
import frc.robot.subsystems.BucketSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StateSubsystem;
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
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick driverController;

  private SwerveDriveSubsystem drive;
  private BucketSubsystem bucket;
  private IntakeSubsystem intake;
  private StateSubsystem state;

  // private SendableChooser<Boolean> autoChooser;

  public RobotContainer() {
    this.driverController = new Joystick(InputConstants.DRIVER_CONTROLLER_PORT);

    this.drive = new SwerveDriveSubsystem();
    this.bucket = new BucketSubsystem();
    this.intake = new IntakeSubsystem();
    this.state = new StateSubsystem(this.drive);

    // this.autoChooser = new SendableChooser<>();
    // this.autoChooser.setDefaultOption("ON", new AutonomusCommand(this.drive, 2));
    // this.autoChooser.addOption("OFF", null);
    // Shuffleboard.getTab("State").add("Auto", this.autoChooser);

    configureBindings();

    this.drive.setDefaultCommand(new FCDrive(drive, driverController));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
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
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return new AutonomusCommand(this.drive, 10000);
  }
}
