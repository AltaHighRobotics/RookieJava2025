package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX motorController;
    private PIDController pidController;
    private RelativeEncoder encoder;

    public ElevatorSubsystem() {
        super();
        this.motorController = new TalonFX(ElevatorConstants.TURN_ID, "rio");

        //this.encoder = this.motorController

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

