/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class visionSub extends Subsystem {
  NetworkTableEntry targetX;
  NetworkTableEntry targetY;

  double distance;

  double rotationError;
  double distanceError;

  double KpRot=0.005;
  double KpDist=0.005;

  double angleTolerance=5;//Deadzone for the angle control loop
  double distanceTolerance=5;//Deadzone for the distance control loop

  double constantForce=0.1;

  double rotationAjust;
  double distanceAjust;

  public visionSub() {
    NetworkTableInstance table = NetworkTableInstance.getDefault();
    NetworkTable cameraTable = table.getTable("chameleon-vision").getSubTable("HD Pro Webcam C920");

    targetX=cameraTable.getEntry("targetYaw");
    targetY=cameraTable.getEntry("targetPitch");

  }

  public double rpm(double targetX, double targetY){
    double distance = 0;
    double rpm = 0;
    double velocity = 0;
    double angle = 0;
    double height = 0;
    double constant = 394.7368421052632;

    //find the square root of (xpos^2 - ypos^2) to find distance 
    distance = Math.sqrt(Math.pow(targetX, 2) - Math.pow(targetY, 2));

    velocity = Math.sqrt((4.9*Math.pow(distance, 2)) / (((distance*Math.tan(angle)) + height) * ((Math.cos(angle))*(Math.cos(angle)))));

    rpm = velocity*constant*Math.PI;
    return rpm;
  }

  //in grahams equation x = distance 
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void autoAdjust(){
    rotationAjust=0;
    distanceAjust=0;
          /*
              Fetches the rotation and distance values from the vision co processor
              sets the value to 0.0 if the value doesnt exist in the database
          */
          rotationError=targetX.getDouble(0.0);
          distanceError=targetY.getDouble(0.0);

          /*
              Proportional (to targetX) control loop for rotation
              Deadzone of angleTolerance
              Constant power is added to the direction the control loop wants to turn (to overcome friction)
          */
          if(rotationError>angleTolerance)
              rotationAjust=KpRot*rotationError+constantForce;
          else if(rotationError<angleTolerance)
                  rotationAjust=KpRot*rotationError-constantForce;
          else 
          rotationAjust =0;
          /*
              Proportional (to targetY) control loop for distance
              Deadzone of distanceTolerance
              Constant power is added to the direction the control loop wants to turn (to overcome friction)
          */
        /* if(distanceError>distanceTolerance)
              distanceAjust=KpDist*distanceError+constantForce;
          else
              if(distanceError<distanceTolerance)
                  distanceAjust=KpDist*distanceError-constantForce;
*/

          //Output the power signals to a arcade drivetrain
          Robot.m_driveTrain.arcadeDrive(0,-rotationAjust);
      }

  @Override
  protected void initDefaultCommand() {

  }
}
