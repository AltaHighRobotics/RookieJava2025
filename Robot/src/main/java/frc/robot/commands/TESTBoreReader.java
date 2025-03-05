package frc.robot;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    private static final String kDefaultAuto = “Default”;
    private static final String kCustomAuto = “My Auto”;
    private String m_autoSelected;
    private final SendableChooser m_chooser = new SendableChooser<>();
    private Encoder throughBore;
    private int ticks;
    private int rawTicks;

    @Override
    public void robotInit() {
        m_chooser.setDefaultOption(“Default Auto”, kDefaultAuto);
        m_chooser.addOption(“My Auto”, kCustomAuto);
        SmartDashboard.putData(“Auto choices”, m_chooser);
        DutyCycleEncoder ClawEncoder = new DutyCycleEncoder(0);
        throughBore.setDistancePerPulse(4.0/256.0); //Set sensetivity of the encoder to 4/256
    }

    @Override
    public void robotPeriodic() {
        ticks = throughBore.get();
        rawTicks = throughBore.getRaw();
        SmartDashboard.putNumber(“Encoder value”, ticks);
        SmartDashboard.putNumber(“Raw encoder value”, rawTicks);
    }
}