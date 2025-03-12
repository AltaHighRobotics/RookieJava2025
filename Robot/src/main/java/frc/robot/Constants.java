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
    public static final int DRIVER_CONTROLLER_PORT = 1;
    public static final double DEADBAND = 0.2;
    public static final double TURN_DEADBAND = 0.5;
  }

  public static class PhotonVisionConsants {
    public static final String CAMERA_NAME = "camera1";
  }

  public static class SwerveDriveConstants {
    public static final double SWERVE_TURN_GEAR_RATIO = 1.0/37;
    public static final double SWERVE_MOD_CENTER_TO_CENTER = 0.635;
    public static final double MODULE_MAX_ANGULAR_VELOCITY = Math.PI;
    public static final double MODULE_MAX_ANGULAR_ACCELERATION = Math.PI * 2;
    public static final double WHEEL_RADIUS = 1 / 33.94;

    public static final double SWERVE_MIN_SPEED = 0.2;
    public static final double SWERVE_MAX_SPEED = 0.3;
    public static final double SWERVE_MAX_OUTPUT = 0.8;

    public static final int BACK_LEFT_DRIVE_ID = 2;
    public static final int BACK_LEFT_TURN_ID = 22;

    public static final int FRONT_LEFT_DRIVE_ID = 3;
    public static final int FRONT_LEFT_TURN_ID = 33;

    public static final int FRONT_RIGHT_DRIVE_ID = 4;
    public static final int FRONT_RIGHT_TURN_ID = 44;

    public static final int BACK_RIGHT_DRIVE_ID = 5;
    public static final int BACK_RIGHT_TURN_ID = 55;

    public static final double P = 5;
    public static final double I = 0;
    public static final double D = 0;
  }
  
  public static class ElevatorConstants {
    public static final int MOTOR_ID = 6;

    public static final double MOTOR_MAX_OUTPUT = 0.3;

    public static final double TICK_PERCENT_DISTANCE = 0.05;

    public static final double TOP_MAG = 45; // What the fuck does this mean

    public static final double MAX_HEIGHT = 15; // CHANGE THIS

    public static final double P = 0.3;
    public static final double I = 0;
    public static final double D = 0;
  }

  public static class PenetratorConstants {
    public static final int MOTOR_ID = 8;
    public static final double MOTOR_INSERT_SPEED = 0.4;
    public static final double MOTOR_PULLOUT_SPEED = 0.8;
  }

  public static class RimmerConstants {
    public static final int SPARK_MAX_ID = 15; //CHANGE THIS
    public static final double MOTOR_INSERT_SPEED = 0.4;
    public static final double MOTOR_PULLOUT_SPEED = 0.8;
  }

  public static class SuckNBlowConstants {
    public static final int SPARK_MAX_ID = 42;
    public static final double MOTOR_SUCK_SPEED = 0.8;
    public static final double MOTOR_BLOW_SPEED = 0.8;
  }

  public static class ClawConstants { //Pretty much a copy of the elevator constants, along with the subsystem and commands
    public static final int TURN_ID = 7; //This ID can be changed later

    public static final double MOTOR_MAX_OUTPUT = 0.5;
    public static final double MOTOR_REVOLUTIONS_FOR_FULL_ROTATION = 100;
    public static final double TICK_DEGREE_DISTANCE = 1;

    public static final double P = 1;
    public static final double I = 0;
    public static final double D = 0.01;
  }
  
  public static class AutoConstants {
    public static final long milisecondsAlive = 1500;
  }
}
