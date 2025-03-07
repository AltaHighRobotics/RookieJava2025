# BetterVetSwerve

## Progress
Done :3

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
