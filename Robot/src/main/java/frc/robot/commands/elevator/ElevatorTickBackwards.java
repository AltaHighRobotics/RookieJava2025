package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorTickBackwards extends Command {
    private ElevatorSubsystem elevatorSubsystem;

    public ElevatorTickBackwards(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        this.elevatorSubsystem.tickBackwards();
        this.elevatorSubsystem.moveToTargetHeight();
    }
}
