package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

/** An example command that uses an example subsystem. */
public class ElevatorGoBrr extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ElevatorSubsystem subsystem;
    private boolean goUp;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ElevatorGoBrr(ElevatorSubsystem subsystem, boolean goUp) {
      this.subsystem = subsystem;
      this.goUp = goUp;
      addRequirements(subsystem);
    }

    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      if (goUp) {
        this.subsystem.goUp();  
      } else {
        this.subsystem.goDown();
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