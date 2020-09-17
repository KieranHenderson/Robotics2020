/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.arcadeDrive;

public class driveTrain extends Subsystem {
  /**
   * Creates a new driveTrain.
   */

  WPI_TalonFX frontLeftTalon, backLeftTalon, frontRightTalon, backRightTalon;
  DifferentialDrive differentialDrive;

  //public CANCoder driveBaseEncoder = new CANCoder(1);

  public driveTrain() {
    //master motors
    backLeftTalon = new WPI_TalonFX(RobotMap.BACK_LEFT_TALON);
    backRightTalon = new WPI_TalonFX(RobotMap.BACK_RIGHT_TALON);
    //slave motors
    frontLeftTalon = new WPI_TalonFX(RobotMap.FRONT_LEFT_TALON);
    frontRightTalon = new WPI_TalonFX(RobotMap.FRONT_RIGHT_TALON);

    //set slaves to follow masters
    frontLeftTalon.follow(backLeftTalon);
    frontRightTalon.follow(backRightTalon);

    // grouping motor controllers
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftTalon, backLeftTalon);
  SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightTalon, backRightTalon);


    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

    
  }

  public void arcadeDrive(double leftSpeed, double rightSpeed){
    differentialDrive.arcadeDrive(leftSpeed, rightSpeed, true);
  }

  public void initDefaultCommand() {
    setDefaultCommand(new arcadeDrive());
   }

  public void drive(){
    double moveSpeed = Robot.driverJoystick.getRawAxis(RobotMap.DRIVE_CONTROLLER_LEFT_STICK)*0.95;
    double rotateSpeed = -Robot.driverJoystick.getRawAxis(RobotMap.DRIVE_CONTROLLER_RIGHT_STICK)*0.95;

    if (rotateSpeed < 0.1 && rotateSpeed > -0.1) { rotateSpeed = 0;}
    if (moveSpeed < 0.1 && moveSpeed > -0.1) { moveSpeed = 0;}

    //rotateSpeed=Math.pow(rotateSpeed, 3);
    //moveSpeed=Math.pow(moveSpeed, 3);


    arcadeDrive(moveSpeed, rotateSpeed);
  }
/*
  public void turn(double targetAngle){
    double error = targetAngle - Robot.gyro.getAngle();
    int threshold = 2;
    if (error > threshold){
      arcadeDrive(0, 0.3);
    }
  }
    */

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
