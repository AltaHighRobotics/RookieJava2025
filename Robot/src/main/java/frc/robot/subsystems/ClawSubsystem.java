package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;


// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix6.hardware.TalonFX;

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
        // ClawMotorController.set(motorOutput); // Previously worked for sparkmax
        ClawMotorController.set(TalonFX.PercentOutput, motorOutput);
    }

    public double getRotation() {
        return encoder.getPosition();
    }
}

/* MOST LIKELY DOESNT WORK, ONLY FOR SWERVE MODULES */

        // public void setRotation(double rotation) {
    //     final double tau = Math.PI * 2;
    //     final double gearRatio = SwerveDriveConstants.SWERVE_TURN_GEAR_RATIO;

    //     // Gets the current angle of the module
    //     final Rotation2d encoderRotation = this.getEncoder();

    //     SwerveModuleState state = desiredState;

    //     state.angle = desiredState.angle.times(-1); // Inverts the angle
    //     state.optimize(encoderRotation); // Optimizes out unnessary rotatation when there's a faster way
    //     state.speedMetersPerSecond *= state.angle.minus(encoderRotation).getCos();

    //     final double driveOuput = state.speedMetersPerSecond;
        
    //     // Uses PID to tell the SparkMax's how much to rotate
    //     final double turnOutput = this.turningPIDController.calculate(
    //     this.turnEncoder.getPosition() * tau * gearRatio, state.angle.getRadians()
    //     );

    //     // Actually sets the speed of the motors and how much they need to rotate
    //     this.drive.set(Math.max(-this.maxOut, Math.min(driveOuput, this.maxOut)));
    //     this.turn.setVoltage(turnOutput);
    // }

    // public Rotation2d getEncoder() {
    //     final double tau = Math.PI * 2;
    //     // final double gearRatio = SwerveDriveConstants.SWERVE_TURN_GEAR_RATIO;
    //     return new Rotation2d(this.turnEncoder.getPosition() * tau);
    // }

    // public void setDesiredState(final SwerveModuleState desiredState) {


    // }
