/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimberCmd extends Command {
  /**
   * Creates a new ClimberCmd.
   */
  double triggerDeadZone = 0.5;

  public ClimberCmd() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.m_climberSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //System.out.println(Robot.m_climberSub.digitalInputBottom.get());
/*
    if (Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTORLLER_RT)>0.8){
        Robot.m_climberSub.climbUp();
      } else if (Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTORLLER_LT) > 0.8){
      Robot.m_climberSub.climbDown();
    } else {
      Robot.m_climberSub.climbOff();
    }
*/

    if (Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTORLLER_RT) > 0.8 && Robot.m_climberSub.digitalInputTop.get() == false) {
      Robot.m_climberSub.climbUp();
    } else if (Robot.operatorJoystick.getRawAxis(RobotMap.OP_CONTORLLER_LT) > 0.8) {
      Robot.m_climberSub.climbDown();
    } else {
      Robot.m_climberSub.climbOff();
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
