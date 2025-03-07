package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

/** An example command that uses an example subsystem. */
public class ResetOrientationCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final SwerveDriveSubsystem drive;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ResetOrientationCommand(SwerveDriveSubsystem drive) {
      this.drive = drive;
      addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      this.drive.resetOrientation();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}