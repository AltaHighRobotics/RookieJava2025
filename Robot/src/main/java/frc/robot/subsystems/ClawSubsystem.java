package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ClawSubsystem extends SubsystemBase{
    private TalonFX ClawMotorController;

    public ClawSubsystem() {
        super();

        this.ClawMotorController = new TalonFX(ClawConstants.TURN_ID);
        this.ClawMotorController.setNeutralMode(NeutralModeValue.Brake);
    }

    public void forwards() {
        this.ClawMotorController.set(0.4);
    }

    public void backwards() {
        this.ClawMotorController.set(-0.4);
    }

    public void stop() {
        this.ClawMotorController.set(0);
    }
}
