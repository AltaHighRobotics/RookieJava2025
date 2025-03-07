package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorTickUpwards extends Command {
    private ElevatorSubsystem elevatorSubsystem;

    public ElevatorTickUpwards(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        this.elevatorSubsystem.tickUpwards();
    }
}
