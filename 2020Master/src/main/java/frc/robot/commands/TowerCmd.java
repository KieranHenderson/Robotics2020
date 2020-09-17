/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TowerCmd extends Command {
  /**
   * Creates a new TowerCMD.
   */
  boolean state;
  boolean speed = false;
  int setPointRange = 100;

  public TowerCmd() {
    requires(Robot.m_towerSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = false;
    speed = false;
  }

  // Called every time the scheduler runs while the command is sch eduled.
  @Override
  public void execute() {
    
      if (Robot.m_shooter.leaderEncoder.getVelocity()<= Robot.m_shooter.setPoint+setPointRange && Robot.m_shooter.leaderEncoder.getVelocity()>= Robot.m_shooter.setPoint-setPointRange){ //change to a range within the setpoint (use variable for setpoint)
        speed = true;
      }
      else if((Robot.m_shooter.leaderEncoder.getVelocity()<=2000)){
        speed = false;
      }

      if (speed == true){
        state = true;
      }
      else if (Robot.m_towerSub.sensorHigh.get() == false) { //top sensor triggered
        state = false;
      }else if (Robot.m_towerSub.sensorSingulator.get() == false){
        state = true;        
      }else if (Robot.m_towerSub.sensorLow.get() == false && Robot.m_towerSub.sensorSingulator.get() == false) { //if theres a ball bottom of tower and in the singulator
        state = true;
      } else if (Robot.m_towerSub.sensorLow.get() == true){
        state = false;

    /*} else if(Robot.m_singulatorSub.toggleAuto == false){
      if (Robot.m_towerSub.sensorHigh.get() == false) { //top sensor triggered
        state = false;
      } else if(Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTROLLER_RIGHT_STICK)>0.5){
        state = true;
      } else if(Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTROLLER_RIGHT_STICK)<0.5){
        Robot.m_towerSub.towerReverse();
      } else{
        state = false;
    }*/
  }
    

    if (state == true) {
      Robot.m_towerSub.towerAdvance();
    } else {
      Robot.m_towerSub.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
