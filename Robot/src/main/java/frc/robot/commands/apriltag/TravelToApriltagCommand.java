package frc.robot.commands.apriltag;

import static edu.wpi.first.units.Units.Milliseconds;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

public class TravelToApriltagCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private SwerveDriveSubsystem drive;
    private ApriltagSubsystem apriltagSubsystem;
    private long lastSeenTime = 0;
    private PIDController pidForward;
    private PIDController pidAdjust;
    private PIDController pidRotation;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TravelToApriltagCommand(SwerveDriveSubsystem drive, ApriltagSubsystem apriltagSubsystem) {
      this.drive = drive;
      this.apriltagSubsystem = apriltagSubsystem;

      this.pidForward = new PIDController(2, 0, 0);
      this.pidAdjust = new PIDController(3, 0, 0);
      this.pidRotation = new PIDController(0.01, 0, 0);

      addRequirements(drive, apriltagSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final PhotonPipelineResult results = apriltagSubsystem.camera.getLatestResult();
        final PhotonTrackedTarget target = results.getBestTarget();   
        
        if (System.currentTimeMillis() - lastSeenTime > 100) {
          drive.drive(0,0,0,0);
        }
        
        if (target == null) { return; }

        lastSeenTime = System.currentTimeMillis();

        final Transform3d pose = target.getBestCameraToTarget();

        double forward = this.pidForward.calculate(pose.getX(), 0.7);
        double adjust = this.pidAdjust.calculate(pose.getY(), 0); 

        double driveRotation = 0;
        if (Math.abs(forward) < 0.01) {
          driveRotation = this.pidRotation.calculate(Math.abs(pose.getZ()), 180);

          if (pose.getZ() > 0)
          {
            // driveRotation *= -1;
          }
        }

        final double driveSpeed = 0.2;

        drive.fieldOriented = false;
        drive.drive(-adjust, forward, driveRotation, driveSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}
