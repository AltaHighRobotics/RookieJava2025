package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RimmerSubsystem;
import frc.robot.subsystems.RimmerSubsystem.MoveStyle;

/** An example command that uses an example subsystem. */
public class ClockwiseRim extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final RimmerSubsystem subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ClockwiseRim(RimmerSubsystem subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }

    /**
     * We need to use full power to get the ball in but no:t to keep it
     * So after a few seconds we lower the motor power
     */
    @Override
    public void execute() {
      this.subsystem.set(MoveStyle.INSERT);
    }
    
    @Override
    public void end(boolean interrupted) {
      this.subsystem.set(MoveStyle.STOP);
    }
}
