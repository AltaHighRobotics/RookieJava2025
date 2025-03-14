package frc.robot.commands.Swerve;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve.SwerveDriveSubsystem;

/** An example command that uses an example subsystem. */
public class SwerveDriveCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final SwerveDriveSubsystem driveSubsystem;
    private XboxController driverController;

    public boolean snapRotationMode;
    private PIDController snapRotationController;
    public double snapTargetDegrees;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public SwerveDriveCommand(SwerveDriveSubsystem driveSubsystem, XboxController driverController) {
      this.driveSubsystem = driveSubsystem;
      this.driverController = driverController;

      this.snapRotationMode = false;
      this.snapRotationController = new PIDController(1, 0, 0);
      this.snapTargetDegrees = 0;

      addRequirements(driveSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      if (snapRotationMode) {
        final double rotationSpeed = this.snapRotationController.calculate(this.driveSubsystem.getAngleDegrees(), snapTargetDegrees);
        this.driveSubsystem.drive(0, 0, rotationSpeed, 1);

        if (Math.abs(this.driverController.getLeftX()) > 0) { snapRotationMode = false; } 
        if (Math.abs(this.driverController.getLeftY()) > 0) { snapRotationMode = false; } 
        if (Math.abs(this.driverController.getRightX()) > 0) { snapRotationMode = false; } 
      }

      else {
        final double forwardSpeed = this.driverController.getLeftX();
        final double strafeSpeed = -this.driverController.getLeftY();
        final double rotationSpeed = this.driverController.getRightX();
        final double speedScaling = 0.75;//(-(this.driverController.getRawAxis(3)) + 1.0) / 2.0;
    
        this.driveSubsystem.drive(forwardSpeed, strafeSpeed, rotationSpeed, speedScaling);
      }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}