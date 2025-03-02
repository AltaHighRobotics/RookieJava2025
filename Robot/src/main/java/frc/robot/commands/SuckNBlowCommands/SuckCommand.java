package frc.robot.commands.SuckNBlowCommands;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.SuckNBlowConstants;
import frc.robot.subsystems.SuckNBlowSubsystem;
import frc.robot.subsystems.SuckNBlowSubsystem.OralType;

/** An example command that uses an example subsystem. */
public class SuckCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final SuckNBlowSubsystem subsystem;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public SuckCommand(SuckNBlowSubsystem subsystem) {
      this.subsystem = subsystem;
      addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      this.subsystem.set(OralType.SUCK);
      Runnable setToBallInSpeed = () -> this.subsystem.set(OralType.KEEP);
      scheduler.schedule(setToBallInSpeed, SuckNBlowConstants.FULL_POWER_SUCK_MILLISECONDS, 
                         TimeUnit.MILLISECONDS);
      scheduler.shutdown();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { return true; }
}