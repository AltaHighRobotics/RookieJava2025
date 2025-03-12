package frc.robot.commands.orientation;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

public class FieldOrient extends Command {
    private SwerveDriveSubsystem drive;
    
    public FieldOrient(SwerveDriveSubsystem drive) {
        this.drive = drive;
    }

    @Override
    public void execute() {
        this.drive.fieldOriented = true;
    }
}
