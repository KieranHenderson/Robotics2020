/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;

public class IntakeSub extends Subsystem {

  //Define Motors here 
  CANSparkMax sparkMax;
  
  
  public IntakeSub() {
    sparkMax = new CANSparkMax(5, MotorType.kBrushless);
    sparkMax.restoreFactoryDefaults();

  }

  public void intakeIn(){
    sparkMax.set(0.6);
  }

  public void intakeOut(){
    sparkMax.set(-0.6);
  }

  public void stop(){
    sparkMax.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeCmd());

  }
}
