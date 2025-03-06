package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClawConstants;
import frc.robot.subsystems.ClawSubsystem;


public class ClawRotationCommand extends Command {
    public enum ClawDirection {
        FORWARDS,
        BACKWARDS,
        STOP
    } 
    
    private ClawDirection clawDirection;
    private ClawSubsystem clawSubsystem;

    public ClawRotationCommand(ClawSubsystem clawSubsystem, ClawDirection clawDirection) {
        this.clawSubsystem = clawSubsystem;
        this.clawDirection = clawDirection;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        switch (this.clawDirection) {
            case FORWARDS:
                this.clawSubsystem.forwards();
                break;
            
            case BACKWARDS:
                this.clawSubsystem.backwards();
                break;

            case STOP:
                this.clawSubsystem.stop();
                break;
        }
    }
}
