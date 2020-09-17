/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.driveTrain;
import frc.robot.subsystems.pneumaticSub;
import frc.robot.subsystems.visionSub;
import frc.robot.subsystems.ClimberSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.SingulatorSub;
import frc.robot.subsystems.TowerSub;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  public final Timer timer = new Timer();
   public static Joystick driverJoystick;
   public static Joystick operatorJoystick;
   public static driveTrain m_driveTrain;
   public static SingulatorSub m_singulatorSub;
   public static ClimberSub m_climberSub;
   public static TowerSub m_towerSub;
   public static IntakeSub m_intakeSub;
   public static ShooterSub m_shooter;
   public static visionSub m_vision;
   public static pneumaticSub m_pneumatics;
   
   Command m_auto;

  @Override
  public void robotInit() {
    m_driveTrain = new driveTrain();
    m_singulatorSub = new SingulatorSub();
    m_climberSub = new ClimberSub();
    m_towerSub = new TowerSub();
    m_intakeSub = new IntakeSub();
    m_shooter = new ShooterSub();
    m_vision = new visionSub();
    m_pneumatics = new pneumaticSub();
    driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK);
    operatorJoystick = new Joystick(RobotMap.OP_JOYSTICK);


    CameraServer.getInstance().startAutomaticCapture(0);

    
  }

  public static double calculateSpeed(double min, double max, double axis) {
    double speed = ((max - min) / 0.9) * axis + (max - ((max - min) / 0.9));
    return speed;
  }

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();  
  }
 
  @Override
  public void autonomousPeriodic() {
    if(timer.get() < 3.0) {//rev up
      Robot.m_shooter.setPoint = 3000;
      Robot.m_shooter.set();
    }
    if(timer.get() > 3.0 && timer.get() < 5.0){//drive backward
      Robot.m_driveTrain.arcadeDrive(-0.35,0.0);
    }
    
    if (timer.get() > 5.0 && timer.get() < 15) {//shoot
      Robot.m_shooter.set();
      Robot.m_towerSub.towerAdvance();
      Robot.m_singulatorSub.singulatorInDiff();
      Robot.m_driveTrain.arcadeDrive(0, 0);
    }

/*
    if (timer.get() > 11 && timer.get() < 13){
      Robot.m_driveTrain.arcadeDrive(-0.35,0.0);
    }

    if (timer.get() > 13 && timer.get() < 15){
      Robot.m_driveTrain.arcadeDrive(0,0.0);
    }*/

    if (timer.get() >=15){
      Robot.m_driveTrain.arcadeDrive(0.0,0.0);
      timer.stop();
      Robot.m_shooter.stop();
      }
    }
  @Override
  public void teleopInit() {
    if (m_auto != null) {
      m_auto.cancel();
    }
  }

  
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();   
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
