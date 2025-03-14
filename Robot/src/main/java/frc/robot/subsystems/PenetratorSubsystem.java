package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PenetratorConstants;

public class PenetratorSubsystem extends SubsystemBase{
    public enum MoveStyle {
        INSERT,
        PULLOUT,
        STOP 
    }

    private TalonFX motor;

    public PenetratorSubsystem() {
        super();
        this.motor = new TalonFX(PenetratorConstants.MOTOR_ID);

        // final double extension = this.motor.getPosition().getValue().magnitude();
        // SmartDashboard.putNumber("Penetrator Extension", extension);
    }

    public void set(MoveStyle pegginType) {
        switch (pegginType) {
            case INSERT:
                this.motor.set(-PenetratorConstants.MOTOR_INSERT_SPEED);
                break;
            case PULLOUT:
                this.motor.set(PenetratorConstants.MOTOR_PULLOUT_SPEED);
                break;
            case STOP:
                this.motor.set(0);
                break;
        }
    }
}

