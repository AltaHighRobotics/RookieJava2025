// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.StateConstants;

public class StateSubsystem extends SubsystemBase {

  SwerveDriveSubsystem drive;

  String objective;
  double bucket;
  double intake;
  double endstop;
  double intaking;
  boolean endstopOverride;

  ShuffleboardTab tab;
  GenericEntry objectiveWidget;
  SendableChooser<Boolean> endstopOverrideWidget;
    
  public StateSubsystem(SwerveDriveSubsystem drive) {
    /**
     * A virtural machine for controlling command-based FRC robots
     * Created by Shayden Jennings in 2024, team 4598. Version 1.0
     * 
     * Usage:
     *     All buttons are mapped to virtual buttons. By doing this, we can
     * perform persistent logic on button presses. This means that a button
     * can trigger logic that can have effect even after the button is
     * released, for example putting the robot into shoot mode by pressing 
     * button 3, which will change the trigger on the controller from running
     * the intake to running the shooter

     *     All mappings are contained in the handleButton function. This function
     * can also handle endstops or other abitrary events. When the button is 
     * pressed, feed into this function that button's name (can be anything) and
     * True ( e.g. handleButton("Trigger", True) ). You can also do this for when 
     * the button is released (e.g. stopping the intake when you release the 
     * trigger)

     *      You must put bindings into robotcontainer to run the handleButton
     * function when buttons are pressed. Use a Trigger object and .onTrue to
     * run a lambda (because a command can't run a function with arguments) 
     * containing the handleButton function for the desired button. To detect
     * when the button is released use a similar method but with .onFalse
     * Below is a function to map a button (for both press and release):

     * def mapButton(self, button, stateTrigger): # Maps a physical button to trigger a state change
     *  commands2.button.JoystickButton(self.driverController, button).onTrue(commands2.cmd.runOnce(lambda: self.state.handleButton(stateTrigger, True))).onFalse(commands2.cmd.runOnce(lambda: self.state.handleButton(stateTrigger, False)))

     *     You can put this fuction in robotcontainer and use it given the
     * following are defined:

     *     self.driverController = <controller used for drving>
     *     self.state = State()

     *         Now the the buttons have been set up, you need to construct
     *     functions to control the robot based off of it's state. These MUST
     *     return a boolean: if you have a state with more than 2 possible
     *     values, you will need multiple functions for that state. Example:

     *     in constants:
     *     
     *     kDefaultIntakeState = 0

     *     in __init__:
     *     self.intakeState = constants.kDefaultIntakeState # can be 1 (intake), 0 (idle), -1 (outtake)

     *     in the class:
     *     def isIntaking(self):
     *         return self.intakeState == 1

     *     def isOuttaking(self):
     *         return self.intakeState == -1

     *     in robotcontainer.configureBindings:
     *     commands2.button.Trigger(self.state.isIntaking).whileTrue(<intake command>)
     *     commands2.button.Trigger(self.state.isOutaking).whileTrue(<outtake command>)

     *         NOTE that there must be a defined default state. This is because the bindings
     *     only trigger things when their values CHANGE (e.g. a button is pressed or a state changes),
     *     so you need to set a default command for things you want to run when the robot is 
     *     started in teleop (e.g. drivetrain)

     *         Also, it is highly recommended that you set a subsystem's idle state as it's default
     *     command (e.g. set the intake's default command to the motors not moving). This
     *     avoids needing an extra function in state or binding in robotcontainer and also 
     *     theoretically serves as a failsafe is you mess something up in state.

     *         Finally, if you want to display certain states, create an updateWidgets
     *     function that refreshes the widgets and put it in the handleButton function
     */

    super();

    this.drive = drive;

    this.objective = StateConstants.DEFAULT_OBJECTIVE;
    this.bucket = StateConstants.DEFAULT_BUCKET;
    this.intake = StateConstants.DEFAULT_INTAKE;
    this.endstop = StateConstants.DEFAULT_END_STOP;
    this.intaking = this.intake;
    this.endstopOverride = StateConstants.DEFAULT_END_STOP_OVERRIDE;
    
    // shuffleboard widgets
    this.tab = Shuffleboard.getTab("State");
    this.objectiveWidget = this.tab.add("Objective", "Default").getEntry();
    this.endstopOverrideWidget = new SendableChooser<Boolean>();
    this.endstopOverrideWidget.setDefaultOption("OFF", false);
    this.endstopOverrideWidget.addOption("ON", true);
    this.tab.add("Endstop Override", this.endstopOverrideWidget);
  }

  public boolean isEndstopOverride() {
    this.endstopOverride = this.endstopOverrideWidget.getSelected();
    return this.endstopOverride;
  }

  public boolean isExtending() {
    return this.bucket == 1;
  }

  public boolean isRetracking() {
    return this.intaking == -1;
  }

  public boolean isIntaking() {
    return this.intaking == 1;
  }

  public boolean isOutaking() {
    return this.bucket == -1;
  }

  public void updateWidgets() {
    switch (this.objective) {
    case "I":
        this.objectiveWidget.setString("Intake");
        break;
    case "P":
        this.objectiveWidget.setString("Plow");
        break;
    case "B":
        this.objectiveWidget.setString("Bucket");
        break;
    }
  }

  void handleButton(String button, boolean pressed) {
    // If pressed
    if (pressed) {
      switch (button) {
        case "Forward":
          if (this.objective == "B") {
            if (this.endstop != 1 || this.endstopOverride) {
              this.bucket = 1;
            } else {
              this.bucket = 0;
            }
          } else {
            this.intake = 1;
            this.bucket = -1;

            if (this.endstop == -1 || this.endstopOverride) {
              this.bucket = 0;
              this.intaking = 1;
            }
          }

          break;

        case "Bucket":
          this.objective = "B";
          this.drive.unlockTurn();
          break;

        case "Plow":
          this.objective = "P";
          this.drive.lockTurn();
          break;

        case "Intake":
          this.objective = "I";
          this.drive.unlockTurn();
          break;

        case "Back":
          if (this.objective == "B") {
            if (this.endstop != 1 || this.endstopOverride) {
              this.bucket = -1;
            } else {
              this.bucket = 0;
            }
          } else {
            this.intake = -1;
            this.intaking = -1;
          }

          break;

        case "Out":
          this.bucket = 0;
          this.endstop = 1;
          break;

        case "In":
          this.bucket = 0;
          this.endstop = -1;

          if (this.intake == 1) {
            this.intaking = 1;
          }

          break;
      }
    } 
    
    // If not pressed
    else {
      switch (button) {
        case "Forward":
          this.bucket = 0;
          this.intake = 0;
          this.intaking = 0;
          break;

        case "Back":
          this.bucket = 0;
          this.intake = 0;
          this.intaking = 0;
          break;

        case "Out":
          this.endstop = 0;
          break;

        case "In":
          this.endstop = 0;
          break;
      }
    }

    this.updateWidgets();
  }
}
