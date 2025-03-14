package frc.robot.commands.apriltag;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

public class FollowApriltagCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private SwerveDriveSubsystem drive;
    private ApriltagSubsystem apriltagSubsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public FollowApriltagCommand(SwerveDriveSubsystem drive, ApriltagSubsystem apriltagSubsystem) {
      this.drive = drive;
      this.apriltagSubsystem = apriltagSubsystem;
      addRequirements(drive, apriltagSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (apriltagSubsystem.getTargetYaw(0) != 0) {
            drive.drive(1, 0, 0, 0.5);
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
