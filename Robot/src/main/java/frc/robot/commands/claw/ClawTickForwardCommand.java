package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTickForwardCommand extends Command {
    private ClawSubsystem clawSubsystem;
    private Joystick armController;

    public ClawTickForwardCommand(ClawSubsystem clawSubsystem, Joystick armController) {
        this.armController = armController;
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.forward(this.armController.getThrottle());
        this.clawSubsystem.moveToTarget();
    }
}
