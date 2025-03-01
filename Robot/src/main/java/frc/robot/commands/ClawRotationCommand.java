package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClawConstants;
import frc.robot.subsystems.ClawSubsystem;

public class ClawHeightCommand extends Command {
    public enum ClawLevel {
        POSITION1,
        POSITION2,
        POSITION3,
        POSITION4,
    } 
    
    private clawLevel clawLevel;
    private clawSubsystem clawSubsystem;

    public clawHeightCommand(ClawSubsystem clawSubsystem, 
                                 ClawLevel clawLevel) {
        this.clawSubsystem = clawSubsystem;
        this.clawLevel = clawLevel;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        switch (this.clawLevel) {
            case POSITION1:
                this.clawSubsystem.setRotation(ClawConstants.POSITION1_ROT);
                break;
            case POSITION2:
                this.clawSubsystem.setRotation(ClawConstants.POSITION2_ROT);
                break;
            case POSITION3:
                this.clawSubsystem.setRotation(ClawConstants.POSITION3_ROT);
                break;
            case POSITION4:
                this.clawSubsystem.setRotation(ClawConstants.POSITION4_ROT);
                break;
        }
    }
}
