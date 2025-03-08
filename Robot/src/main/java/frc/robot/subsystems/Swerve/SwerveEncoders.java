package frc.robot.subsystems.Swerve;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;

// public class SwerveEncoders {
//     private WPI_TalonSRX motor;

//     public void robotInit() {
//         motor = new WPI_TalonSRX(1); // Initialize Talon SRX on CAN ID 1
//         motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10); // Configure the encoder
//     }

//     public void teleopPeriodic() {
//         double position = motor.getSelectedSensorPosition(0); // Get current encoder position
//         double velocity = motor.getSelectedSensorVelocity(0); // Get current encoder velocity

//         System.out.println("Position: " + position + " Velocity: " + velocity);
//     }
// }
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
// import com.revrobotics.spark.SparkMaxAlternateEncoder;

public class SwerveEncoders {
   private SparkMax motor;
   private SparkAbsoluteEncoder absEncoder;

   public SwerveEncoders(int motorID) {
      // Initialize the Spark Max motor controller
      motor = new SparkMax(motorID, MotorType.kBrushless);
      // Get the absolute encoder
      absEncoder = motor.getAbsoluteEncoder();
   }

   public double getAbsolutePosition() {
      // Get the absolute position of the encoder in revolutions
      return absEncoder.getPosition();
   }

   public double getVelocity() {
      // Get the velocity of the encoder in RPM
      return absEncoder.getVelocity();
   }

   public static void main(String[] args) {
      // Create an instance of the example class
      SwerveEncoders example = new SwerveEncoders(1);
      // Read and print the absolute position of the encoder
      double absolutePosition = example.getAbsolutePosition();
      System.out.println("Absolute Position: " + absolutePosition);
      // Read and print the velocity of the encoder
      double velocity = example.getVelocity();
      System.out.println("Velocity: " + velocity);
   }
}
