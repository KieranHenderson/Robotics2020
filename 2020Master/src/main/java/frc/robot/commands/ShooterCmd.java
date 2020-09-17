/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.Relay;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShooterCmd extends Command {
  /**
   * Creates a new ShooterCmd.
   */

  public int direction = Robot.driverJoystick.getPOV(0);

  boolean green = false;
  public ShooterCmd() { 
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.m_shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*if (Robot.operatorJoystick.getRawButton(5)){
      System.out.println(Robot.m_vision.targetX);
    }*/


    // if (Robot.operatorJoystick.getRawButton(RobotMap.OP_BUTTON_Y)){ 
    //   green = true;
    // } else if (Robot.operatorJoystick.getRawButton(RobotMap.OP_BUTTON_B)) {
    //   green = false;
    // }

    if (Robot.operatorJoystick.getRawButtonReleased(RobotMap.OP_BUTTON_Y)){ 
      green = !green;
    }

    if (green == true){
      Robot.m_shooter.greenLightRelay.set(Value.kForward);
    } else {
      Robot.m_shooter.greenLightRelay.set(Value.kOff);
    }


    if (Robot.operatorJoystick.getRawButton(RobotMap.OP_BUTTON_X)) {
      Robot.m_shooter.set();
    }else{
      Robot.m_shooter.stop();
    }



    if (direction == 0 && Robot.m_shooter.setPoint <= 3300) { // DPAD UP button is pressed
      Robot.m_shooter.setPoint = Robot.m_shooter.setPoint + 100;
    } else if (direction == 180 && Robot.m_shooter.setPoint >= 2700) { // DPAD DOWN button is pressed
      // do something else
    }

    /*if (green == true){
      Robot.m_vision.greenLightRelay.set(Relay.Value.kForward);
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.m_shooter.stop();
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
