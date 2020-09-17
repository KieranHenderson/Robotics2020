/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SingulatorCmd extends Command {
  /**
   * Creates a new SingulatorCmd.
   */
  boolean state;

  public SingulatorCmd() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.m_singulatorSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = false;
    Robot.m_singulatorSub.toggleAuto = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (Robot.m_towerSub.talon.get()<0){
        state = true;
      }else if (Robot.m_towerSub.sensorHigh.get() == false) { //top of tower sensor
        state = false;
      }else if (Robot.m_towerSub.sensorSingulator.get() == false) { //singulator sensor
        state = true;
      } else {
        state = false;
      }
        
    
    if (state == true){
      Robot.m_singulatorSub.singulatorInDiff();
    } else if (state == false){
      Robot.m_singulatorSub.stop();
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
