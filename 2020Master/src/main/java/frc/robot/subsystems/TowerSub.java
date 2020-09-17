/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TowerCmd;


public class TowerSub extends Subsystem {
  
  public WPI_TalonSRX talon;
  public DigitalInput sensorHigh, sensorLow, sensorSingulator;
  
  public TowerSub() {
    talon = new WPI_TalonSRX(RobotMap.TOWER_TALON);
    sensorHigh = new DigitalInput(RobotMap.TOWER_SENSOR_TOP);
    sensorLow = new DigitalInput(RobotMap.TOWER_SENSOR_LOW);
    sensorSingulator = new DigitalInput(RobotMap.SINGULATOR_SENSOR);

    //talon.setFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);

  }

  public void towerAdvance(){
    talon.set(-.6);

  }

  /*public void towerReverse(){
    talon.set(-.4);
  }*/

  public void stop(){
    talon.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void initDefaultCommand() {
    setDefaultCommand(new TowerCmd());
   } 
}
