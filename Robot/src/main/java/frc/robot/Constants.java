// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class InputConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final double DEADBAND = 0.2;
    public static final double TURN_DEADBAND = 0.5;
    public static final boolean FIELD_ORIENTED = true;
  }

  public static class PhotonVisionConsants {
    public static final String CAMERA_NAME = "camera1";
  }

  public static class SwerveDriveConstants {
    public static final double SWERVE_TURN_GEAR_RATIO = 1.0/180.0;
    public static final double SWERVE_MOD_CENTER_TO_CENTER = 0.635;
    public static final double MODULE_MAX_ANGULAR_VELOCITY = Math.PI;
    public static final double MODULE_MAX_ANGULAR_ACCELERATION = Math.PI * 2;
    public static final double WHEEL_RADIUS = 0.0508;

    public static final double SWERVE_MIN_SPEED = 0.3;
    public static final double SWERVE_MAX_SPEED = 0.8;
    public static final double SWERVE_MAX_OUTPUT = 0.6;

    public static final int INTAKE_LEFT_ID = 10;
    public static final int INTAKE_RIGHT_ID = 11;

    public static final int FRONT_LEFT_DRIVE_ID = 3;
    public static final int FRONT_LEFT_TURN_ID = 33;

    public static final int FRONT_RIGHT_DRIVE_ID = 4;
    public static final int FRONT_RIGHT_TURN_ID = 44;

    public static final int BACK_LEFT_DRIVE_ID = 2;
    public static final int BACK_LEFT_TURN_ID = 22;

    public static final int BACK_RIGHT_DRIVE_ID = 5;
    public static final int BACK_RIGHT_TURN_ID = 55;

    public static final double P = 10;
    public static final double I = 0;
    public static final double D = 0.3;
  }
  
  public static class ElevatorConstants {
    public static final int TURN_ID = 66;

    public static final double LEVEL1_HEIGHT = 100;
    public static final double LEVEL2_HEIGHT = 200;
    public static final double LEVEL3_HEIGHT = 300;

    public static final double P = 10;
    public static final double I = 0;
    public static final double D = 0.3;
  }

  public static class SuckNBlowConstants {
    public static final int SPARK_MAX_ID = 42;
    public static final double MOTOR_SPEED = 0.8;
    public static final double MOTOR_GAG_REFLEX_REDUCTION = 0.3; // Keeps ball in, percentage of MOTOR_SPEED, 0.5 = 50% of MOTOR_SPEED
    public static final long FULL_POWER_SUCK_MILLISECONDS = 1500; // We need to use more power get the ball then to keep it
    public static boolean invert = true;
  }
}
