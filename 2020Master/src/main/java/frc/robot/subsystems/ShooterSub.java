/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.ShooterCmd;

public class ShooterSub extends Subsystem {
  public CANSparkMax followerMotor = new CANSparkMax(9, MotorType.kBrushless);
  public CANSparkMax leaderMotor = new CANSparkMax(10, MotorType.kBrushless);

  public CANPIDController pidController = leaderMotor.getPIDController();

  public CANEncoder leaderEncoder = leaderMotor.getEncoder();

  public static int currentShootingLimit = 30;

  public Relay greenLightRelay;


  public double kP = 0, kI = 0, kD = 0, kIz = 0, kFF = 0.000215, kMaxOutput = 1, kMinOutput = -1;
  public double maxRPM = 3000;
  //public double maxRPM2 = Robot.m_vision.rpm(Robot.m_vision.targetX.getDouble(0.0), Robot.m_vision.targetY.getDouble(0.0));
  public double setPoint = maxRPM;

  int direction;


  public ShooterSub() {
    greenLightRelay = new Relay(0);

    leaderMotor.restoreFactoryDefaults();
    followerMotor.restoreFactoryDefaults();

    leaderMotor.setSmartCurrentLimit(40);
    followerMotor.setSmartCurrentLimit(40);

    System.out.println(leaderMotor.getOutputCurrent());
    leaderMotor.setInverted(true);
    followerMotor.follow(leaderMotor,true);


    // set PID coefficients
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);


  }

  public void set() {
    direction = Robot.driverJoystick.getPOV(0);

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { pidController.setP(p); kP = p; }
    if((i != kI)) { pidController.setI(i); kI = i; }
    if((d != kD)) { pidController.setD(d); kD = d; }
    if((iz != kIz)) { pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { pidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      pidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
    
    pidController.setReference(setPoint, ControlType.kVelocity);
    
    if (direction == 0) { // DPAD UP button is pressed
      setPoint = setPoint+100;
    } else if (direction == 180) { // DPAD DOWN button is pressed
      setPoint = setPoint - 100;
    }
    
    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", leaderEncoder.getVelocity());
  }

  public void stop(){
    followerMotor.set(0);
    leaderMotor.set(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ShooterCmd());
   }
}
