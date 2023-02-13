/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
// import frc.robot.RobotMap;
// import frc.robot.commands.HubChase;
// import edu.wpi.first.math.controller.PIDController;

/**
 * Add your docs here.
 */
public class VisionMotion extends SubsystemBase {

  public boolean aligned = false;
  private PIDController pid;
  private double kp = 1.;
  private double ki = 1.0;
  private double kd = 1.0;
  private double setPoint = 0.0;
  private double deSpeed = 0.5;

  private double leftturnSpeed;
  private double rightturnSpeed;
  private double trigger;

  public VisionMotion() {
    super();
    pid = new PIDController(kp, ki, kd);

  }

  public void AutoLifeLight() 
  {
    trigger = 0.4;
    if ((Math.abs(Robot.smaVisionLimeLight.getXdegToTarget()) >= 3.0)) {
      
      rightturnSpeed = (trigger/2)+((pid.calculate(Robot.smaVisionLimeLight.getXdegToTarget(), setPoint))/50.0);
      leftturnSpeed = trigger/2;
      Robot.driveBase.drive(0, 0,rightturnSpeed);
      // Robot.led.rainbow();
  }
  else if ((Robot.smaVisionLimeLight.getTargetAreaAsDouble() <= 0.0001)) {
       Robot.driveBase.reset();
      //  Robot.led.confetti();
  }
  else
  {
      Robot.driveBase.drive(0,0,trigger);
      // Robot.led.twinkles_ocean();
  }
  }
  //HOW TO FIX?
  // @Override
  // public void setDefaultCommand() {
  //   setDefaultCommand(new HubChase());
  // }
}


