package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;

public class ClawTickBackwardCommand extends Command {
    
    private ClawSubsystem clawSubsystem;
    private Joystick armController;

    public ClawTickBackwardCommand(ClawSubsystem clawSubsystem, Joystick armController) {
        this.clawSubsystem = clawSubsystem;
        this.armController = armController;
        addRequirements(clawSubsystem);
    }

    @Override
    public void execute() {
        this.clawSubsystem.backward(this.armController.getThrottle()+1);
        this.clawSubsystem.moveToTarget();
    }
}
