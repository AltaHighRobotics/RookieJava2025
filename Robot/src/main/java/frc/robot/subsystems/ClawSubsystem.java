package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class ClawSubsystem extends SubsystemBase{
    private TalonFX ClawMotorController;
    private SparkMax motorController;
    private PIDController pidController;
    private RelativeEncoder encoder;

    /* Code that I took from some other team, shouldn't work */
    // public double getEncoder() {
    //     return elevatorMotor.getSelectedSensorPosition();
    // }

    public ClawSubsystem() {
        super();

        // this.motorController = new SparkMax(ClawConstants.TURN_ID, MotorType.kBrushless);
        this.ClawMotorController = new TalonFX(ClawConstants.TURN_ID);


        this.encoder = this.ClawMotorController.getEncoder(); //Shouldn't work with Talon, but we will see, this was previously sparkmax code

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    public void setRotation(double rotation) {
        final double motorOutput = this.pidController.calculate(encoder.getPosition() * 1, rotation);  //Added 0.1 modifier for testing so it doesn't go out of control or go too fast
        // ClawMotorController.set(motorOutput);
        ClawMotorController.set(TalonFX.PercentOutput, motorOutput);
    }

    public double getRotation() {
        return encoder.getPosition();
    }
}
