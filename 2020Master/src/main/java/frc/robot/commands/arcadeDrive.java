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

public class arcadeDrive extends Command {

  double triggerDeadZone = 0.8;

  public arcadeDrive() {
    requires(Robot.m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //prevSpeed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.driverJoystick.getRawAxis(RobotMap.DRIVER_CONTORLLER_LT) > triggerDeadZone)
    { 
      System.out.println("k");
      //Robot.m_vision.greenLightRelay.set(Relay.Value.kForward);
      Robot.m_vision.autoAdjust();
      
    }else{
      Robot.m_driveTrain.drive();
    }
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.m_driveTrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  protected void interrupted() {
    end();
  }
}
