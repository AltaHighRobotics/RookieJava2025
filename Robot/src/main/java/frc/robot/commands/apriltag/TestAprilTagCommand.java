package frc.robot.commands.apriltag;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ApriltagSubsystem;

public class TestAprilTagCommand extends Command {
    ApriltagSubsystem subsystem;

    public TestAprilTagCommand(ApriltagSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        PhotonPipelineResult results = subsystem.camera.getLatestResult();
        PhotonTrackedTarget target = results.getBestTarget();

        // PhotonTrackedTarget target = subsystem.getFirstTarget();
        if (target == null) {
            System.out.println("No tag found"); 
            return; 
        }

        System.out.println(target.getFiducialId());
    }
}
