package frc.robot.commands.Swerve;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

public class SnapHexRotateForward extends Command {
    private SwerveDriveSubsystem subsystem; 
    private SwerveDriveCommand defaultCommand;

    public SnapHexRotateForward(SwerveDriveSubsystem subsystem) {
        this.subsystem = subsystem;
        this.defaultCommand = (SwerveDriveCommand) subsystem.getDefaultCommand();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        if (this.subsystem.snapTargetDegrees % 60 != 0) {
            final double currentDegrees = this.subsystem.getAngleDegrees();
            final double newTargetDegrees = Math.ceil(currentDegrees / 60.0) * 60;
            this.subsystem.snapTargetDegrees = newTargetDegrees;
        } else {
            this.subsystem.snapTargetDegrees += 60;
        }

        this.subsystem.snapRotationMode = true;
    }
}
