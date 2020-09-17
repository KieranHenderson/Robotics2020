/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class pneumaticSub extends Subsystem {
  Compressor c = new Compressor(RobotMap.PCM);;
  public Solenoid sol = new Solenoid(RobotMap.SOL_IN);
  public Solenoid solretract = new Solenoid(RobotMap.SOL_OUT);


  public pneumaticSub() {
		sol.set(true);
    solretract.set(false);
  }

  @Override
  public void periodic() {

    }

  public void workPneumatics(){
    if (Robot.operatorJoystick.getRawButton(RobotMap.DRIVER_BUTTON_Y)){
      sol.set(true);
      solretract.set(false);
    } else if (Robot.operatorJoystick.getRawButton(RobotMap.DRIVER_BUTTON_B)){
      sol.set(false);
      solretract.set(true);
    }
  }
  @Override
  public void initDefaultCommand() {

  }
}
