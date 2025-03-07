package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;

/** An example command that uses an example subsystem. */
public class ClawGoToTarget extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private final ClawSubsystem subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ClawGoToTarget(ClawSubsystem subsystem) {
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
      this.subsystem.moveToTarget();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}