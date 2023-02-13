package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveBase extends SubsystemBase{
    private static final int kFrontLeftChannel = 20;// 2;
    private static final int kRearLeftChannel = 2;// 3;
    private static final int kFrontRightChannel = 14; //1;
    private static final int kRearRightChannel = 9; // 0;
    private MecanumDrive m_robotDrive;
    private static Joystick m_stick;

    WPI_TalonSRX frontLeft = new WPI_TalonSRX(kFrontLeftChannel);
    WPI_TalonSRX rearLeft = new WPI_TalonSRX(kRearLeftChannel);
    WPI_TalonSRX frontRight = new WPI_TalonSRX(kFrontRightChannel);
    WPI_TalonSRX rearRight = new WPI_TalonSRX(kRearRightChannel);

    private static final int kJoystickChannel = 0;
    

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    public DriveBase(){
        super();

        frontRight.setInverted(false);
        rearRight.setInverted(false);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
        m_stick = new Joystick(RobotMap.DRIVE_JOYSTICK);

        double x, y, z;
        x = m_stick.getX();
        y = m_stick.getRawAxis(RobotMap.DC_LS_HA);//m_stick.getY();
        z = m_stick.getRawAxis(RobotMap.DC_RS_HA);

        m_robotDrive.driveCartesian(x,y,z);
    }

    public void drive(Joystick j) {
		drive(j.getX(),j.getRawAxis(RobotMap.DC_LS_HA), j.getRawAxis(RobotMap.DC_RS_HA)); 
	}

    public static Joystick getDriveJoystick()
    {
        return m_stick;
    }

    public void reset() {
		drive(0.0, 0.0, 0.0);
	}

    public void drive(double x, double y, double z) {
		m_robotDrive.driveCartesian(x,y,z);
	}

    // Need to fix this!!!
    // @Override
	// private void initDefaultCommand() {
	// 	setDefaultCommand(new DriveJoystick());
	// }

}