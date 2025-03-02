package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClawConstants;
import frc.robot.subsystems.ClawSubsystem;

public class ClawRotationCommand extends Command {
    public enum ClawLevel {
        LEVEL1,
        LEVEL2,
        LEVEL3,
    } 
    
    private ClawLevel clawLevel;
    private ClawSubsystem clawSubsystem;

    public ClawHeightCommand(ClawSubsystem elevatorSubsystem, 
                                 ClawLevel clawLevel) {
        this.clawSubsystem = elevatorSubsystem;
        this.clawLevel = elevatorLevel;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        switch (this.clawLevel) {
            case LEVEL1:
                this.clawSubsystem.setHeight(ClawConstants.LEVEL1_HEIGHT);
                break;
            case LEVEL2:
                this.clawSubsystem.setHeight(ClawConstants.LEVEL2_HEIGHT);
                break;
            case LEVEL3:
                this.clawSubsystem.setHeight(ClawConstants.LEVEL3_HEIGHT);
                break;
        }
    }
}
