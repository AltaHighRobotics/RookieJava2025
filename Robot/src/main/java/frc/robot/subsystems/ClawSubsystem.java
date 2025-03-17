package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ClawSubsystem extends SubsystemBase{
    private TalonFX motor;
    private PIDController pidController;
    
    private double targetDegrees = 0;

    public ClawSubsystem() {
        super();

        this.motor = new TalonFX(ClawConstants.TURN_ID);

        final double P = ClawConstants.P;
        final double I = ClawConstants.I;
        final double D = ClawConstants.D;
        this.pidController = new PIDController(P, I, D);

        SmartDashboard.putNumber("Claw P", P);
        SmartDashboard.putNumber("Claw I", I);
        SmartDashboard.putNumber("Claw D", D);

        this.motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void moveToTarget() {
        final double newP = SmartDashboard.getEntry("Claw P").getDouble(0);
        final double newI = SmartDashboard.getEntry("Claw I").getDouble(0);
        final double newD = SmartDashboard.getEntry("Claw D").getDouble(0);
        this.pidController.setPID(newP, newI, newD);

        while (this.targetDegrees > 360) {
            this.targetDegrees -= 360;
        }

        final double currentDegrees = this.getDegrees();

        final double targetRad = Math.toRadians(this.targetDegrees);
        final double currentRad = Math.toRadians(currentDegrees);

        final double motorRawOutput = this.pidController.calculate(currentRad, targetRad);
        final double limitedMotorOutput = MathUtil.clamp(motorRawOutput, -ClawConstants.MOTOR_MAX_OUTPUT, ClawConstants.MOTOR_MAX_OUTPUT);
        motor.set(limitedMotorOutput);
        System.out.printf("Claw Current Position: %.6f\n", this.getDegrees());
    }

    public void setDegrees(double targetDegrees) {
        this.targetDegrees = targetDegrees;
    }

    public double getDegrees() {
        final double currentMotorRevolutions = this.motor.getPosition().getValue().magnitude();
        double currentMotorPercentage = currentMotorRevolutions / ClawConstants.MOTOR_REVOLUTIONS_FOR_FULL_ROTATION;

        // Make sure motor resets after 360 deg
        while (currentMotorPercentage > 1) {
            currentMotorPercentage --;
        }

        return currentMotorPercentage * 360;
    }

    public void forward(double throttleSpeed) {
        setDegrees(this.targetDegrees + 1 * ClawConstants.TICK_DEGREE_DISTANCE * throttleSpeed);
        System.out.printf("Claw speed: %.6f\n", throttleSpeed);
    }

    public void backward(double throttleSpeed) {
        setDegrees(this.targetDegrees - 1 * ClawConstants.TICK_DEGREE_DISTANCE * throttleSpeed);
        System.out.printf("Claw speed: %.6f\n", throttleSpeed);
    }

    public void stop() {
        System.out.println("Claw Rotation Stopped");
        setDegrees(this.getDegrees());
        this.motor.set(0);
    }
}
