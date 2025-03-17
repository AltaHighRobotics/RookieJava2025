// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.cert.CertPathValidatorException.BasicReason;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.InputConstants;
import frc.robot.Constants.SwerveDriveConstants;
import frc.robot.Constants.StateConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.FullArmCommand;
import frc.robot.commands.PauseArm;
import frc.robot.commands.SuckNBlowCommands.BlowCommand;
import frc.robot.commands.SuckNBlowCommands.StopSuckCommand;
import frc.robot.commands.SuckNBlowCommands.SuckCommand;
// import frc.robot.commands.Swerve.EncoderTestCommand;
import frc.robot.commands.Swerve.ResetOrientationCommand;
import frc.robot.commands.Swerve.SwerveDriveCommand;
import frc.robot.commands.apriltag.FollowApriltagCommand;
import frc.robot.commands.apriltag.TestAprilTagCommand;
import frc.robot.commands.apriltag.TravelToApriltagCommand;
import frc.robot.commands.claw.ClawGoToTarget;
import frc.robot.commands.claw.ClawStopCommand;
import frc.robot.commands.claw.ClawTickBackwardCommand;
import frc.robot.commands.claw.ClawTickForwardCommand;
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.commands.elevator.ElevatorTickBackwards;
import frc.robot.commands.elevator.ElevatorTickUpwards;
import frc.robot.commands.elevator.StopElevatorCommand;
import frc.robot.commands.lift.ClockwiseRim;
import frc.robot.commands.lift.CounterClockwiseRim;
import frc.robot.commands.lift.InsertPenetrator;
import frc.robot.commands.lift.PullOutPenetrator;
import frc.robot.commands.orientation.FieldOrient;
import frc.robot.commands.orientation.RobotOrient;
import frc.robot.subsystems.SuckNBlowSubsystem;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;
// import frc.robot.subsystems.Swerve.TESTSwervesusbsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PenetratorSubsystem;
import frc.robot.subsystems.RimmerSubsystem;
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
  private final XboxController driverController;  // Xbox Controller
  private final Joystick armController;     // Flight controller

  private SwerveDriveSubsystem drive;
  private SuckNBlowSubsystem suckNBlowSubsystem;
  private ElevatorSubsystem elevatorSubsystem;
  private ClawSubsystem clawSubsystem;
  private PenetratorSubsystem penetratorSubsystem;
  private RimmerSubsystem rimmerSubsystem;
  private ApriltagSubsystem apriltagSubsystem;

  public RobotContainer() {
    this.driverController = new XboxController(1);
    this.armController = new Joystick(0);

    this.drive = new SwerveDriveSubsystem();
    this.suckNBlowSubsystem = new SuckNBlowSubsystem();
    this.elevatorSubsystem = new ElevatorSubsystem();
    this.clawSubsystem = new ClawSubsystem();
    this.rimmerSubsystem = new RimmerSubsystem();
    this.penetratorSubsystem = new PenetratorSubsystem();
    this.apriltagSubsystem = new ApriltagSubsystem();

    // this.swerveTestSubsystem = new TESTSwervesusbsystem(SwerveDriveConstants.FRONT_RIGHT_TURN_ID);

    configureBindings(); 

    // THIS IS THE SWERVE DRIVE TOGGLE //
    this.drive.setDefaultCommand(new SwerveDriveCommand(drive, driverController));
    this.elevatorSubsystem.setDefaultCommand(new ElevatorGoToTarget(elevatorSubsystem));
    this.clawSubsystem.setDefaultCommand(new ClawGoToTarget(clawSubsystem));
    this.apriltagSubsystem.setDefaultCommand(new TestAprilTagCommand(apriltagSubsystem));
  }

  private void addStateBinding(int buttonNumber, double elevatorHeight, double clawDegrees) {
    JoystickButton stateButton = new JoystickButton(armController, buttonNumber);
    // stateButton.whileFalse(new PauseArm(elevatorSubsystem, clawSubsystem));
    stateButton.whileTrue(new FullArmCommand(
      this.elevatorSubsystem, this.clawSubsystem, elevatorHeight, clawDegrees
    ));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   */
  private void configureBindings() {
    JoystickButton gyroResetButton1 = new JoystickButton(driverController, 7);
    JoystickButton gyroResetButton2 = new JoystickButton(driverController, 8);
    gyroResetButton1.whileTrue(new ResetOrientationCommand(this.drive));
    gyroResetButton2.whileTrue(new ResetOrientationCommand(this.drive));

    JoystickButton fieldButton = new JoystickButton(driverController, 1);
    fieldButton.whileTrue(new RobotOrient(drive));
    fieldButton.whileFalse(new FieldOrient(drive));

    // JoystickButton myButton = new JoystickButton(driverController, 1);
    // myButton.whileTrue(new EncoderTestCommand(swerveTestSubsystem));

    // Suck N Blow
    JoystickButton suckButton = new JoystickButton(armController, 1);
    JoystickButton blowButton = new JoystickButton(armController, 2);
    suckButton.whileTrue(new SuckCommand(this.suckNBlowSubsystem));
    blowButton.whileTrue(new BlowCommand(this.suckNBlowSubsystem));
    // STOP MOVING
    // JoystickButton stopBlowButton = new JoystickButton(armController, 6);
    // stopBlowButton.whileTrue(new StopSuckCommand(this.suckNBlowSubsystem));

    // Elevator Manual
    JoystickButton elevatorUPButton = new JoystickButton(armController, 5);
    JoystickButton elevatorDownButton = new JoystickButton(armController, 3); 
    // JoystickButton elevatorStopButton = new JoystickButton(armController, );
    elevatorUPButton.whileTrue(new ElevatorTickUpwards(this.elevatorSubsystem));
    elevatorDownButton.whileTrue(new ElevatorTickBackwards(this.elevatorSubsystem));
    // elevatorStopButton.whileTrue(new ElevatorTickBackwards(this.elevatorSubsysbtem));
    // STOP ELEVATOR ITS GOING CRAZY
    // JoystickButton stopElevatorCommand = new JoystickButton(armController, 7);
    // stopElevatorCommand.whileTrue(new StopElevatorCommand(this.elevatorSubsystem));
    
    // Claw Manual
    JoystickButton clawForwardButton = new JoystickButton(armController, 4);
    JoystickButton clawBackwardButton = new JoystickButton(armController, 6);
    clawForwardButton.whileTrue(new ClawTickForwardCommand(this.clawSubsystem, this.armController));
    clawBackwardButton.whileTrue(new ClawTickBackwardCommand(this.clawSubsystem, this.armController));
    
    // Lift Manual
    // Penetrator (kind of like elevator, talonfx encoder)
    JoystickButton penetratorForwardButton = new JoystickButton(driverController, 2);
    JoystickButton penetratorBackwardButton = new JoystickButton(driverController , 3);
    penetratorForwardButton.whileTrue(new InsertPenetrator(this.penetratorSubsystem));
    penetratorBackwardButton.whileTrue(new PullOutPenetrator(this.penetratorSubsystem));
    // Rimmer (kind of like sucknblow, sparkmax encoder)
    JoystickButton rimmerForwardButton = new JoystickButton(driverController, 5);
    JoystickButton rimmerBackwardButton = new JoystickButton(driverController, 6);
    rimmerForwardButton.whileTrue(new ClockwiseRim(this.rimmerSubsystem));
    rimmerBackwardButton.whileTrue(new CounterClockwiseRim(this.rimmerSubsystem));

    // Apriltag commands (we need pi with camera, pi is fried)
    // JoystickButton followApriltag = new JoystickButton(armController, 11);
    // JoystickButton travelToApriltag = new JoystickButton(armController , 12);
    // followApriltag.whileTrue(new FollowApriltagCommand(this.drive, this.apriltagSubsystem));
    // travelToApriltag.whileTrue(new TravelToApriltagCommand(this.drive, this.apriltagSubsystem));

    // States
    addStateBinding(7,  StateConstants.CORAL_INTAKE_HEIGHT,      StateConstants.CORAL_INTAKE_ROTATION);           // Coral intake:                          Height: 0.0    Rotation: 92.0
    addStateBinding(8,  StateConstants.CORAL_LEVEL_THREE_HEIGHT, StateConstants.CORAL_HIGHER_LEVEL_ROTATION);     // High level coral                       Height: 0.9    Rotation: -108.0
    addStateBinding(9,  StateConstants.ALGAE_LEVEL_TWO_HEIGHT,   StateConstants.LOWER_DEPOSIT_ROTATION);          // Medium level Algae [HEIGHT IS WRONG]   Height: 0.7    Rotation: -69
    addStateBinding(10, StateConstants.CORAL_LEVEL_TWO_HEIGHT,   StateConstants.LOWER_DEPOSIT_ROTATION);          // Medium level coral                     Height: 0.7    Rotation: -69
    addStateBinding(11, StateConstants.ALGAE_LEVEL_ONE_HEIGHT,   StateConstants.LOWER_DEPOSIT_ROTATION);          // Low level Algae [HEIGHT IS WRONG]      Height: 0.4    Rotation: -69
    addStateBinding(12, StateConstants.CORAL_LEVEL_ONE_HEIGHT,   StateConstants.LOWER_DEPOSIT_ROTATION);          // Low level coral                        Height: 0.0    Rotation: -69
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.stationAlign(drive);
  }
}
