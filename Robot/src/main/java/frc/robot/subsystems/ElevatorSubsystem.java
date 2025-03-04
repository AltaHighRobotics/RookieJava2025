package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX motorController;
    private PIDController pidController;
    private double targetHeight = 0.0;

    public ElevatorSubsystem() {
        super();
        this.motorController = new TalonFX(ElevatorConstants.MOTOR_ID, "rio");

        final double P = ElevatorConstants.P;
        final double I = ElevatorConstants.I;
        final double D = ElevatorConstants.D;
        this.pidController = new PIDController(P, I, D);
    }

    /**
     * @param height A percentage (0 to 1)
     */
    public void setHeight(final double height) {
        if (height < 0 || height > 1 ) {
            throw new Error("Height should be between 0 and 1");
        } 

        targetHeight = height;
    }

    public void moveToTargetHeight() { // Meant to be called each tick
        final double elevatorTargetHeightRadians = targetHeight * ElevatorConstants.TOP_MAG;
        final double elevatorCurrentHeightRadians = this.getHeight() * ElevatorConstants.TOP_MAG;

        double motorOutput = this.pidController.calculate(elevatorCurrentHeightRadians, 
                                                                elevatorTargetHeightRadians);

        motorOutput = Math.abs(motorOutput) / motorOutput * 0.3;

        motorController.set(motorOutput);
    }

    /**
     * @return Height as a percentage (0 to 1)
     */
    public double getHeight() {
        final double realMotorAngle = this.motorController.getPosition().getValue().magnitude();
        System.out.println(realMotorAngle);
        return realMotorAngle;
    }

    public void goUp() {
        motorController.set(ElevatorConstants.MOTOR_SPEED);
        getHeight();
    }

    public void goDown() {
        motorController.set(-ElevatorConstants.MOTOR_SPEED);
    }
}

