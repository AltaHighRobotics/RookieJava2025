# BetterVetSwerve

## Progress
- [x] Swerve Drive - Ethan
  - [x] Fix Motors to work with Phoneix 5
  - [x] Measure Center to Center and other constants like gear ratio
  - [x] Fully tested and working
        
- [x] Elevator - Danial
  - [x] Subsystem
    - [x] Method to set the height of the elevator (Hopefully taking in meters and not just a number) should use PID
  - [x] Testing Command,
    - [x] Can set to a height
    - [x] Takes in an enum with three different setpoints to set to different heights
  - [x] Button Binded to command for each setpoint
  - [x] Fully tested and working
        
- [ ] Claw - Pizza
  - [x] Subsystem
    - [x] Method to set the angle of the subsystem (Just like SweveDrive) should use PID
  - [x] Testing Command
    - [x] Can set to any angle in degrees
    - [ ] Takes in an enum with three different setpoints
  - [ ] Button Binded to command for each setpoint
  - [ ] Fully tested and working
        
- [x] Suck and Blow - Chang
  - [x] Subsystem
    - [x] Method to activate
    - [x] Method to deactivate
  - [x] Testing Commands
    - [x] Command to activate through subsystem
    - [x] Command to deactivate through subsystem
  - [x] Button Binded to command
  - [x] Fully tested and working
        
- [x] Fagimizer
  - [x] Subsystem
    - [x] Method to go in
    - [x] Method to pull out 
  - [x] Testing Command
    - [x] Command to go in through subsystem
    - [x] Command to pull out through subsystem
  - [x] Button Binded to commands
  - [x] Fully tested and working

- [x] Rimmer
  - [x] Subsystem
    - [x] Method of insertion
    - [x] Method to pull out
  - [x] Testing Command
    - [X] Command to insert through subsystem
    - [x] Command to pull out through subsystem
  - [x] Button binded to commands
  - [x] Fully tested and working

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
