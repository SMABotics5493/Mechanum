package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.SMAVisionLL;

public class AutoVisionLL extends CommandBase { //THE LIMELIGHT IS NAMED MILK. WHOEVER DISGREES SHALL BE CURDLED -Julia 

    public SMAVisionLL LimeLight = new SMAVisionLL();
    public boolean chaseComplete = false;
    public double m_driveSpeed;
    public double elapsedTime;
    public boolean urMommi = false; //isTimerDone
    public double timme;//m_startTime
    public boolean runOnce;
    private boolean runOnceAgain;
    private double m_targetEncoderUnits; // target distance in encoder units
    private long m_startTime;
    private double m_driveVelocity;
    private double m_lastElapsedTime;
    private double m_lastEncoderPosition;
    private boolean isDistanceLessThanThreshold;

    public AutoVisionLL(double speed) {
        m_driveSpeed = speed;
    }

    // public void timedDriving() {
    //     execute();
    // }

    @Override
    public void initialize() {
    }


    @Override
    public void execute() {

        if (!chaseComplete)
        {
            Robot.visionMotion.AutoLifeLight(); //executes if light is in effect.
            if (LimeLight.getTargetAreaAsDouble()>20)
            {
                chaseComplete = true;
                Robot.driveBase.reset();
                SmartDashboard.putBoolean("IS THE CHASE COMPLETE??????????", chaseComplete);
            }
        }
        
    }

    @Override
    public boolean isFinished() {
        return chaseComplete;
    }

    @Override
    public void end(boolean interrupted) {
        //Set drivespeed to zero here
    }
}
