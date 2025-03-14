package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTickBackwardCommand extends Command {
    
    private ClawSubsystem clawSubsystem;

    public ClawTickBackwardCommand(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.backward();
        this.clawSubsystem.moveToTarget();
    }
}
