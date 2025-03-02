package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

/** An example command that uses an example subsystem. */
public class ElevatorHeightChangeTestCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public enum ElevatorHeightChangeDirection {
      UP,
      DOWN
    }

    private final ElevatorSubsystem subsystem;
    private final ElevatorHeightChangeDirection direction;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ElevatorHeightChangeTestCommand(ElevatorSubsystem subsystem,  
                                           ElevatorHeightChangeDirection direction) {
      this.subsystem = subsystem;
      this.direction = direction;
      addRequirements(subsystem);
    }

    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      switch (this.direction) {
        case UP:
          this.subsystem.goUp();  
          break;

        case DOWN:
          this.subsystem.goDown();
          break;
      }
      
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}