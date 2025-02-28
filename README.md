# BetterVetSwerve

## Progress
- [ ] Swerve Drive - Ethan
  - [ ] Fix Motors to work with Phoneix 5
  - [ ] Measure Center to Center and other constants like gear ratio
  - [ ] Fully tested and working
        
- [ ] Elevator - Danial
  - [ ] Subsystem
    - [ ] Method to set the height of the elevator (Hopefully taking in meters and not just a number) should use PID
  - [ ] Testing Command,
    - [ ] Can set to a height
    - [ ] Takes in an enum with three different setpoints to set to different heights
  - [ ] Button Binded to command for each setpoint
  - [ ] Fully tested and working
        
- [ ] Claw - Pizza
  - [ ] Subsystem
    - [ ] Method to set the angle of the subsystem (Just like SweveDrive) should use PID
  - [ ] Testing Command
    - [ ] Can set to any angle in degrees
    - [ ] Takes in an enum with three different setpoints
  - [ ] Button Binded to command for each setpoint
  - [ ] Fully tested and working
        
- [ ] Suck and Blow - Chang
  - [ ] Subsystem
    - [ ] Method to activate
    - [ ] Method to deactivate
  - [ ] Testing Commands
    - [ ] Command to activate through subsystem
    - [ ] Command to deactivate through subsystem
  - [ ] Button Binded to command
  - [ ] Fully tested and working
        
- [ ] Fagimizer - Not yet built
  - [ ] Subsystem
    - [ ] Method to go in
    - [ ] Method to pull out 
  - [ ] Testing Command
    - [ ] Command to go in through subsystem
    - [ ] Command to pull out through subsystem
  - [ ] Button Binded to commands
  - [ ] Fully tested and working

## Git
* The main branch is only for 100% tested, working code
* Merging dev to main should require at least one other programmer to agree
* Don't make commits directly to dev either, make a branch (ex: eh_cleaning_up_subsystems) and then PULL FROM DEV and deal with CONFLICTS ON YOUR END
* After merge delete your branch
```
*---*---*---*---*---*---*---* main
                     \
                      *---*---*---* dev
                       \       \
                         \       *---*---* eh_feature-1
                           \          
                             *---*---* ny_feature-2
```

## Documentation Example
```
  /**
  * Used to limit the speed of the robot
  *
  * @param value 0 - 1, max speed of the motor, 0.5 would be half speed
  * @return nothing for this function
  */
  public void setMaxOut(double value) {
    this.maxOut = value;
  }
```
