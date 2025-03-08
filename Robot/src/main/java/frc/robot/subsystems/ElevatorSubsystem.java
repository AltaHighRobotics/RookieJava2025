package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private TalonFX motorController;
    private PIDController pidController;
    private double targetHeightPercentage = 0.0;

    public ElevatorSubsystem() {
        super();
        this.motorController = new TalonFX(ElevatorConstants.MOTOR_ID, "rio");

        final double P = ElevatorConstants.P;
        final double I = ElevatorConstants.I;
        final double D = ElevatorConstants.D;
        this.pidController = new PIDController(P, I, D);

        this.motorController.setNeutralMode(NeutralModeValue.Brake);
    }

    /**
     * @param height A percentage (0 to 1)
     */
    public void setHeight(double heightPercentage) {
        if (heightPercentage < 0) {
            heightPercentage = 0;
        }
        
        if (heightPercentage > 1 ) {
            heightPercentage = 1;
        } 

        targetHeightPercentage = heightPercentage;
    }

    public void moveToTargetHeight() { // Meant to be called each tick
        final double targetPositionRevolutions = targetHeightPercentage * ElevatorConstants.TOP_MAG;
        final double currentPositionRevolutions = this.getHeight();

        // System.out.printf("Elevator Current Position: %.6f\n", this.getHeight());

        double motorOutput = this.pidController.calculate(currentPositionRevolutions, targetPositionRevolutions);

        // Clamp limits the motor speed, should probably use the max output speed but oh well this works too
        motorOutput = MathUtil.clamp(motorOutput, -ElevatorConstants.MOTOR_MAX_OUTPUT, ElevatorConstants.MOTOR_MAX_OUTPUT);

        // Make it move half as fast when going down (Gravity makes it go down quicker)
        if (motorOutput < 0) {
            motorOutput *= 0.5;
        }

        if (motorOutput > 0 && this.getHeight() > ElevatorConstants.MAX_HEIGHT) {
            this.stop();
        } else {
        motorController.set(motorOutput);
        }
    }

    /**
     * Gets the current height of the elevator
     * @return Height as revolutions
     */
    public double getHeight() {
        final double realMotorAngle = this.motorController.getPosition().getValue().magnitude();
        return realMotorAngle;
    }

    /**
     * Gets the current height of the elevator
     * @return Height as percentage (0.0 to 1.0)
     */
    public double getHeightAsPercentage() {
        return getHeight() / ElevatorConstants.TOP_MAG;
    }

    public void tickUpwards() {
        setHeight(this.targetHeightPercentage + 0.01);
    }

    public void tickBackwards() {
        setHeight(this.targetHeightPercentage - 0.01);
    }

    public void stop() {
        setHeight(this.getHeightAsPercentage());
        motorController.set(0);
    }
}

