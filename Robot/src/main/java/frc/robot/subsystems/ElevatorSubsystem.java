package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.MotorOutputStatusValue;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX motorController;
    private PIDController pidController;
    private RelativeEncoder encoder;

    public ElevatorSubsystem() {
        super();
        Double AbsoluteSensorDiscontinuityPoint;
        this.motorController = new TalonFX(ElevatorConstants.TURN_ID, "rio");

        //this.encoder = this.motorController
        this.encoder = this.motorController.blahblahblah

         final double P = ElevatorConstants.P;
         final double I = ElevatorConstants.I;
         final double D = ElevatorConstants.D;
         this.pidController = new PIDController(P, I, D);
    }

    public void setHeight(double height) {
         final double motorOutput = this.pidController.calculate(encoder.getPosition(), height);
         motorController.set(motorOutput);
    }

    public double getHeight() {
        return encoder.getPosition();
    }

    public void goUp() {
        motorController.set(0.8);
    }

    public void goDown() {
        motorController.set(-0.8);
    }
}

