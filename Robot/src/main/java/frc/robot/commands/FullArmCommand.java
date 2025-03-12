package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.ElevatorSubsystem;

/** An example command that uses an example subsystem. */
public class FullArmCommand extends Command {
    public enum ArmState {
      STOWED, // Default state, also is the CORAL_CARRY
      BALL_PICKUP_1,
      BALL_PICKUP_2,
      BALL_SCORE_1,
      BALL_SCORE_2,
      BALL_CARRY,
      CORAL_PICKUP,
      CORAL_SCORE_1,
      CORAL_SCORE_2,
      CORAL_SCORE_3,
      CORAL_SCORE_4,
      BLOW,
    }  

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ElevatorSubsystem elevatorSubsystem;
    private final ClawSubsystem clawSubsystem;
    private final ArmState armState;
    private final ArmState armState2;
    private final Joystick driverController;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public FullArmCommand(ElevatorSubsystem elevatorSubsystem, ClawSubsystem clawSubsystem, ArmState armState, ArmState armState2, Joystick driverController) {
      this.elevatorSubsystem = elevatorSubsystem;
      this.clawSubsystem = clawSubsystem;
      this.armState = armState;
      this.armState2 = armState2;
      this.driverController = driverController;
      addRequirements(elevatorSubsystem, clawSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void execute() {
    if (this.driverController.getRawAxis(3) > 0) { 
      switch (this.armState) {
        case STOWED: // Default state, also is the CORAL_CARRY
          this.elevatorSubsystem.setHeight(0.1);
          this.clawSubsystem.setDegrees(90);
          break;
        case BALL_PICKUP_1:
          this.elevatorSubsystem.setHeight(0.2);
          this.clawSubsystem.setDegrees(45);
          break;
        case BALL_PICKUP_2:
          this.elevatorSubsystem.setHeight(0.3);
          this.clawSubsystem.setDegrees(10);
          break;
        case BALL_SCORE_1:
          this.elevatorSubsystem.setHeight(0.4);
          this.clawSubsystem.setDegrees(70);
          break;
        case BALL_SCORE_2:
          this.elevatorSubsystem.setHeight(0.5);
          this.clawSubsystem.setDegrees(30);
          break;
        case BALL_CARRY:
          this.elevatorSubsystem.setHeight(0.6);
          this.clawSubsystem.setDegrees(20);
          break;
      }
    } else {
      switch (this.armState2) {
        case CORAL_PICKUP:
          this.elevatorSubsystem.setHeight(0.7);
          this.clawSubsystem.setDegrees(10);
          break;
        case CORAL_SCORE_1:
          this.elevatorSubsystem.setHeight(0.8);
          this.clawSubsystem.setDegrees(90);
          break;
        case CORAL_SCORE_2:
          this.elevatorSubsystem.setHeight(0.9);
          this.clawSubsystem.setDegrees(45);
          break;
        case CORAL_SCORE_3:
          this.elevatorSubsystem.setHeight(0.1);
          this.clawSubsystem.setDegrees(50);
          break;
        case CORAL_SCORE_4:
          this.elevatorSubsystem.setHeight(0.2);
          this.clawSubsystem.setDegrees(90);
          break;
        case BLOW:
          this.elevatorSubsystem.setHeight(0.3);
          this.clawSubsystem.setDegrees(90);
          break;
      }
    }
  }
}