package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

/** An example command that uses an example subsystem. */
public class StopElevatorCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public enum ElevatorHeightChangeDirection {
      UP,
      DOWN
    }

    private final ElevatorSubsystem subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public StopElevatorCommand(ElevatorSubsystem subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }

    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      this.subsystem.stop();  

      final double height = this.subsystem.getHeightAsPercentage() * 100;
      System.out.printf("Elevator stoped at position: %.6f (Goes from 0 to 100, 0.0 to 1.0 internally)\n", height);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}