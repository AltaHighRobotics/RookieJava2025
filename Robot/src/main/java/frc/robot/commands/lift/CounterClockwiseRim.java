package frc.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RimmerSubsystem;
import frc.robot.subsystems.RimmerSubsystem.MoveStyle;

/** An example command that uses an example subsystem. */
public class CounterClockwiseRim extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final RimmerSubsystem subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public CounterClockwiseRim(RimmerSubsystem subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }

    /**
     * Blows for a few seconds to shoot the ball and then stops the motor
     */
    @Override
    public void execute() {
      this.subsystem.set(MoveStyle.PULLOUT);
    }

    @Override
    public void end(boolean interrupted) {
      this.subsystem.set(MoveStyle.STOP);
    }
}