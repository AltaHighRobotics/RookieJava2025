package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase{
    private SparkMax motorController;
    private PIDController pidController;
    private RelativeEncoder encoder;

    public ClawSubsystem() {
        super();

        this.motorController = new SparkMax(ClawConstants.TURN_ID, MotorType.kBrushless);

        this.encoder = this.motorController.getEncoder();

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    public void setHeight(double height) {
        final double motorOutput = this.pidController.calculate(encoder.getPosition(), height);
        motorController.set(motorOutput);
    }

    public double getHeight() {
        return encoder.getPosition();
    }
}
