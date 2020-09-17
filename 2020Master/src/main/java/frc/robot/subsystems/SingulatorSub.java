/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;
import frc.robot.commands.SingulatorCmd;

public class SingulatorSub extends Subsystem {
  /**
   * Creates a new SingulatorSub.
   */
  WPI_VictorSPX victor1;
  WPI_VictorSPX victor2;

  public boolean toggleAuto;

  @Override
  public void initDefaultCommand() {
      setDefaultCommand(new SingulatorCmd());
  }

  public SingulatorSub() {
    victor1 = new WPI_VictorSPX(RobotMap.SINGULATOR_LEFT_VICTOR);
    victor2 = new WPI_VictorSPX(RobotMap.SINGULATOR_RIGHT_VICTOR);
  }

  public void singulatorInDiff(){
    victor1.set(0.50);
    victor2.set(-0.30);
  }

  public void out(){
    victor1.set(-0.50);
    victor2.set(0.30);
  }
  
  public void stop(){
    victor1.set(0);
    victor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
