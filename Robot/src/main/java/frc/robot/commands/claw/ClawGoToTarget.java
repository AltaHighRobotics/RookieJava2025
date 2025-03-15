package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClawSubsystem;
import edu.wpi.first.wpilibj.Joystick;

/** An example command that uses an example subsystem. */
public class ClawGoToTarget extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private final ClawSubsystem subsystem;
    private final Joystick armController;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ClawGoToTarget(ClawSubsystem subsystem, Joystick armController) {
      this.subsystem = subsystem;
      this.armController = armController;
      addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      // System.out.println("out");
      this.subsystem.moveToTarget();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}