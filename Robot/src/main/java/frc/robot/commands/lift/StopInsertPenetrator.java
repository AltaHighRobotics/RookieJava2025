package frc.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PenetratorSubsystem;
import frc.robot.subsystems.PenetratorSubsystem.MoveStyle;

/** An example command that uses an example subsystem. */
public class StopInsertPenetrator extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final PenetratorSubsystem subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public StopInsertPenetrator(PenetratorSubsystem subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }

    /**
     * Blows for a few seconds to shoot the ball and then stops the motor
     */
    @Override
    public void execute() {
      this.subsystem.set(MoveStyle.STOP);
    }
}