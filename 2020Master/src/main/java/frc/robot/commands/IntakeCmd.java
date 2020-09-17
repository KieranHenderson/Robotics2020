/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class IntakeCmd extends Command {
  /**
   * Creates a new IntakeCmd.
   */
  public IntakeCmd() {
    requires(Robot.m_intakeSub);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_pneumatics.workPneumatics();
    //Forward Intake 
    if (Robot.driverJoystick.getRawButton(RobotMap.INTAKE_IN_BUMPER)) {
      Robot.m_intakeSub.intakeIn();
    }

    //Reverse Intake 
    else if (Robot.driverJoystick.getRawButton(RobotMap.INTAKE_OUT_BUMPER)) {
      Robot.m_intakeSub.intakeOut();

    }else{
      Robot.m_intakeSub.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
