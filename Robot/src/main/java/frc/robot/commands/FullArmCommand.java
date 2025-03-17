package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

/** An example command that uses an example subsystem. */
public class FullArmCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ElevatorSubsystem elevatorSubsystem;
    private final ClawSubsystem clawSubsystem;
    private final double heightTargetPercentage;
    private final double clawTargetDegrees;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public FullArmCommand(ElevatorSubsystem elevatorSubsystem, ClawSubsystem clawSubsystem, 
                          final double heightTargetPercentage,
                          final double clawTargetDegrees
    ) {
      this.elevatorSubsystem = elevatorSubsystem;
      this.clawSubsystem = clawSubsystem;
      this.heightTargetPercentage = heightTargetPercentage;
      this.clawTargetDegrees = clawTargetDegrees;
      addRequirements(elevatorSubsystem, clawSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void execute() {
          this.elevatorSubsystem.setHeight(this.heightTargetPercentage);
          this.clawSubsystem.setDegrees(this.clawTargetDegrees);
    }
}