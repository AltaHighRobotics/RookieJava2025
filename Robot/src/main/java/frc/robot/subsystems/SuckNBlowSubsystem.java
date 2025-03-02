package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SuckNBlowConstants;

public class SuckNBlowSubsystem extends SubsystemBase{
    public enum OralType {
        SUCK,
        BLOW,
        KEEP,
        STOP 
    }

    private SparkMax motor;

    public SuckNBlowSubsystem() {
        super();
        this.motor = new SparkMax(SuckNBlowConstants.SPARK_MAX_ID, MotorType.kBrushless);
    }

    /**
     * Sets the state of the motors
     * @param oralType The direction the motors will go, positive, negitive, or stopped
     */
    public void set(OralType oralType) {
        double speed = SuckNBlowConstants.MOTOR_SPEED;

        if (speed < 0 || speed > 1) {
            throw new Error("SuckNBlowConstants.MOTOR_SPEED should be between 0 and 1, use invert to make negitive");
        }

        if (SuckNBlowConstants.invert) {
            speed *= -1;
        }

        switch (oralType) {
            case SUCK:
                this.motor.set(speed);
            case BLOW:
                this.motor.set(-speed);
            case KEEP:
                this.motor.set(speed * SuckNBlowConstants.MOTOR_GAG_REFLEX_REDUCTION);
            case STOP:
                this.motor.set(0);
        }
    }
}
