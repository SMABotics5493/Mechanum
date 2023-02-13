// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.VisionMotion;

public class Robot extends TimedRobot {
  public static SMAVisionLL smaVisionLimeLight;
  public static VisionMotion visionMotion;
  public static DriveBase driveBase;


  public void robotPeriodic() { // continually runs -br
    CommandScheduler.getInstance().run();
  }

  @Override 
  public void robotInit() {
    visionMotion = new VisionMotion();
    smaVisionLimeLight = new SMAVisionLL();
    driveBase = new DriveBase();
    

    CameraServer.startAutomaticCapture(0);
    CameraServer.startAutomaticCapture(1);

  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }
}