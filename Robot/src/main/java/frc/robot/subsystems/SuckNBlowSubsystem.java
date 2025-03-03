package frc.robot.subsystems;

import com.ctre.phoenix6.jni.OrchestraJNI;
import com.revrobotics.spark.SparkMax;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private ScheduledExecutorService scheduler;
    private boolean queueLock;

    public SuckNBlowSubsystem() {
        super();
        this.motor = new SparkMax(SuckNBlowConstants.SPARK_MAX_ID, MotorType.kBrushless);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.queueLock = false;
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
                System.out.println("SUCK");
                break;
            case BLOW:
                this.motor.set(-speed);
                System.out.println("BLOW");
                break;
            case KEEP:
                this.motor.set(speed * SuckNBlowConstants.MOTOR_GAG_REFLEX_REDUCTION);
                System.out.println("KEEP");
                break;
            case STOP:
                this.motor.set(0);
                System.out.println("STOP");
                break;
        }
    }

    public void queueSet(OralType initalType, OralType oralType, long milliseconds) {
        if (this.queueLock) { return; }

        System.out.println("INITTEST");

        this.set(initalType);

        Runnable queue = () -> {
            this.set(oralType);
            this.queueLock = false;
        };

        this.queueLock = true;
        scheduler.schedule(queue, milliseconds, TimeUnit.MILLISECONDS);
        // scheduler.shutdown();
    }
}
