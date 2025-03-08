package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTickForwardCommand extends Command {
    private ClawSubsystem clawSubsystem;

    public ClawTickForwardCommand(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.forward();
        this.clawSubsystem.moveToTarget();
    }
}
