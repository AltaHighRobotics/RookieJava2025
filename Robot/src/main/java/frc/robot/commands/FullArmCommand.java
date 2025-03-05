package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

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
    private final ElevatorSubsystem clawSubsystem;
    private final ArmState armState;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public FullArmCommand(ElevatorSubsystem elevatorSubsystem, ElevatorSubsystem clawSubsystem, ArmState armState) {
      this.elevatorSubsystem = elevatorSubsystem;
      this.clawSubsystem = clawSubsystem;
      this.armState = armState;
      addRequirements(elevatorSubsystem, clawSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      switch (this.armState) {
        case STOWED: // Default state, also is the CORAL_CARRY
          break;
        case BALL_PICKUP_1:
          break;
        case BALL_PICKUP_2:
          break;
        case BALL_SCORE_1:
          break;
        case BALL_SCORE_2:
          break;
        case BALL_CARRY:
          break;
        case CORAL_PICKUP:
          break;
        case CORAL_SCORE_1:
          break;
        case CORAL_SCORE_2:
          break;
        case CORAL_SCORE_3:
          break;
        case CORAL_SCORE_4:
          break;
        case BLOW:
          break;
      }
    }
}