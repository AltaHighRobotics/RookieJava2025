package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;
import com.ctre.phoenix6.hardware.TalonFX;

public class ClawSubsystem extends SubsystemBase{
    private TalonFX ClawMotorController;
    private PIDController pidController;
    private Encoder encoder;

    public ClawSubsystem() {
        super();

        this.ClawMotorController = new TalonFX(ClawConstants.TURN_ID);

        /* ENCODER OBJECT EXAMPLE
        this.encoder = new DutyCycleEncoder(9, 360, 0);
        this.encoder = new Encoder(  int channelA, // DIO port for channel A (blue wire)
                                     int channelB, // DIO port for channel A (yellow wire)
                                     int indexChannel, // DIO port for channel A (green wire)
                                     boolean reverseDirection) //Should be true so that we can detect if the arm is going backwards as well, otherwise we only detect forwards motion
        */

        this.encoder = new Encoder(9, 8, 7, true);

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    public void setRotation(double targetDegrees) {
        final double currentDegrees = encoder.get();
        final double rawDegrees = encoder.getRaw(); //could use instead
        final double motorOutput = this.pidController.calculate(currentDegrees, targetDegrees);        
        ClawMotorController.set(motorOutput); 
    }

    public double getRotation() {
        return encoder.get();
    }

    public void resetRotation() {
        encoder.reset();        // Reset the Encoder distance to zero
    }
}
