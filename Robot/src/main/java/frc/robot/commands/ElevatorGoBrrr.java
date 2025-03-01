package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

/** An example command that uses an example subsystem. */
public class ElevatorGoBrrr extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ElevatorSubsystem subsystem;
    private boolean goDown;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ElevatorGoBrrr(ElevatorSubsystem subsystem, boolean goUp) {
      this.subsystem = subsystem;
      this.goDown = goDown;
      addRequirements(subsystem);
    }

    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      if (goDown) {
        this.subsystem.goDown();  
      } else {
        this.subsystem.goUp();
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