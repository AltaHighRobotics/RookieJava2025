package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.MathUtil;
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
        this.encoder = new Encoder(  int channelA, // DIO port for channel A (blue wire)
                                     int channelB, // DIO port for channel A (yellow wire)
                                     int indexChannel, // DIO port for channel A (green wire)
                                     boolean reverseDirection) //Should be true so that we can detect if the arm is going backwards as well, otherwise we only detect forwards motion
        */

        this.encoder = new Encoder(9, 8, 7, true); // Plug into DIO ports 9, 8, and 7 (preferabally in colored order above)
        encoder.setDistancePerPulse(1.0/5.7); // 360 divided by the 2048 pulses per rotation should mean every 5.7 pulses is one rotational degree

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    public void setRotation(double targetDegrees) {
        final double currentDegrees = encoder.get();
        final double rawDegrees = encoder.getRaw(); //could use this instead
       
        System.out.println("--- Encoder outputs for .get and .raw below");
        System.out.printf("     Curret deg: %f.6\n", currentDegrees);
        System.out.printf("     raw deg: %f.6\n", rawDegrees);
        System.out.println("---");

        final double motorOutput = this.pidController.calculate(currentDegrees, targetDegrees);       

        final double speedLimitedOutput = MathUtil.clamp(motorOutput, -0.1, 0.1);
        
        ClawMotorController.set(speedLimitedOutput); 
    }

    public double getRotation() {
        return encoder.get();
    }

    public double getDistanceTraveled() {
        return encoder.getDistance(); // Will return how much the encoder is turnned
    }

    public void resetRotation() {
        encoder.reset();        // Reset the Encoder distance to zero (a good idea to trigger when the claw is initialized)
    }

    public boolean checkIfStopped() {
        return encoder.getStopped(); //Checks if encoder is currently stopped
    }

    public void stop() {
        this.ClawMotorController.set(0);
    }
}
