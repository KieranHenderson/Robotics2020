/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberCmd;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class ClimberSub extends Subsystem {
  /**
   * Creates a new ClimberSub.
   */
  public CANSparkMax sparkMax;
  public DigitalInput digitalInputTop, digitalInputBottom;

  public void initDefaultCommand() {
    setDefaultCommand(new ClimberCmd());
}

  public ClimberSub() {
    sparkMax = new CANSparkMax(RobotMap.CLIMB_SPARKMAX, MotorType.kBrushless);
    digitalInputBottom = new DigitalInput(RobotMap.BOTTOM_SWITCH);
    digitalInputTop = new DigitalInput(RobotMap.TOP_SWITCH);
  }

  public void climbUp (){
    sparkMax.set(0.4);
  }
  
  public void climbDown (){
    sparkMax.set(-0.4);
  }

  public void climbOff(){
    sparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
