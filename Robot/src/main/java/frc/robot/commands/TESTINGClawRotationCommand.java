package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClawConstants;
import frc.robot.subsystems.ClawSubsystem;

import frc.robot.subsystems.ClawConstants.POSITION1_ROTATION;
import frc.robot.subsystems.ClawConstants.POSITION2_ROTATION;
import frc.robot.subsystems.ClawConstants.POSITION3_ROTATION;
import frc.robot.subsystems.ClawConstants.POSITION4_ROTATION;


public class ClawRotationCommand extends Command {
    public enum ClawLevel {
        POSITION1,
        POSITION2,
        POSITION3,
        POSITION4
    } 
    
    private ClawLevel clawLevel;
    private ClawSubsystem clawSubsystem;

    public ClawHeightCommand(ClawSubsystem clawSubsystem, ClawLevel clawPosition) {
        this.clawSubsystem = clawSubsystem;
        this.clawPosition = clawPosition;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        switch (this.clawPosition) {
            case POSITION1:
                this.clawSubsystem.setHeight(POSITION1_ROTATION); // 0 degrees
                break;
            case POSITION2:
                this.clawSubsystem.setHeight(POSITION2_ROTATION); // 90 degrees
                break;
            case POSITION3:
                this.clawSubsystem.setHeight(POSITION3_ROTATION); // 180 degrees
                break;
            case POSITION4:
                this.clawSubsystem.setHeight(POSITION3_ROTATION); // 270 degrees
        }
    }
}
