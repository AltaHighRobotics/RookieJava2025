package frc.robot.commands.orientation;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

public class RobotOrient extends Command {
    private SwerveDriveSubsystem drive;
    
    public RobotOrient(SwerveDriveSubsystem drive) {
        this.drive = drive;
    }

    @Override
    public void execute() {
        this.drive.fieldOriented = false;
    }
}
