package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;


// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix6.hardware.TalonFX;

public class ClawSubsystem extends SubsystemBase{
    private TalonFX ClawMotorController;
    private PIDController pidController;
    private DutyCycleEncoder encoder;

    public ClawSubsystem() {
        super();

        this.ClawMotorController = new TalonFX(ClawConstants.TURN_ID);


        this.encoder = new DutyCycleEncoder(9, 360, 0);

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    public void setRotation(double targetDegrees) {
        final double currentDegrees = encoder.get();
        final double motorOutput = this.pidController.calculate(currentDegrees, targetDegrees);        
        ClawMotorController.set(motorOutput); 
    }

    public double getRotation() {
        return encoder.get();
    }
}
