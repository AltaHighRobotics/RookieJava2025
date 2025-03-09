package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RimmerConstants;

public class RimmerSubsystem extends SubsystemBase{
    public enum MoveStyle {
        INSERT,
        PULLOUT,
        STOP 
    }

    private SparkMax motor;

    public RimmerSubsystem() {
        super();
        this.motor = new SparkMax(RimmerConstants.SPARK_MAX_ID, MotorType.kBrushless);
    }

    /**
     * Sets the state of the motors
     * @param oralType The direction the motors will go, positive, negitive, or stopped
     */
    public void set(MoveStyle oralType) {
        switch (oralType) {
            case INSERT:
                this.motor.set(-RimmerConstants.MOTOR_INSERT_SPEED);
                break;
            case PULLOUT:
                this.motor.set(RimmerConstants.MOTOR_PULLOUT_SPEED);
                break;
            case STOP:
                this.motor.set(0);
                break;
        }
    }
}

