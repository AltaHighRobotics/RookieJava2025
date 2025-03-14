package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

public class PauseArm extends Command {
    private final ElevatorSubsystem elevatorSubsystem;
    private final ClawSubsystem clawSubsystem;

    public PauseArm(ElevatorSubsystem elevatorSubsystem, ClawSubsystem clawSubsystem) {
      this.elevatorSubsystem = elevatorSubsystem;
      this.clawSubsystem = clawSubsystem;
      addRequirements(elevatorSubsystem, clawSubsystem);
    }

    @Override
    public void execute() {
        this.elevatorSubsystem.stop();
        this.clawSubsystem.stop();
    }
}
