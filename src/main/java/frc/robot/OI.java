package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

// import frc.robot.commands.AutoTurn;



public class OI {

    public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick mechJoystick = new Joystick(RobotMap.JOYSTICK);

    //Joystick (Parke)
    JoystickButton intakeIn = new JoystickButton(mechJoystick, RobotMap.JOYB_B);
    JoystickButton cargoLiftUp = new JoystickButton(mechJoystick, RobotMap.JOYB_X);
    JoystickButton hubAlignVisionLeft = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
    JoystickButton hubAlignVisionRight = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
    JoystickButton caitlinButton = new JoystickButton(mechJoystick, RobotMap.JOYB_LB);
    JoystickButton reverseCargo = new JoystickButton(mechJoystick, RobotMap.JOYB_RJ);



    //driveJoystick (Eva)
    //JoystickButton LLDrive = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton climberUp = new JoystickButton(mechJoystick, RobotMap.JOYB_Y);
    JoystickButton climberDown = new JoystickButton(mechJoystick, RobotMap.JOYB_A);
    POVButton solenoidDumpForward = new POVButton(mechJoystick, RobotMap.POVB_N);
    POVButton solenoidDumpReverse = new POVButton(mechJoystick, RobotMap.POVB_S);

    POVButton autoLeft = new POVButton(driveJoystick, RobotMap.POVB_W);
    POVButton autoRight = new POVButton(driveJoystick, RobotMap.POVB_E);
    POVButton autoForward = new POVButton(driveJoystick, RobotMap.POVB_N);
    POVButton autoBackward = new POVButton(driveJoystick, RobotMap.POVB_S);
    // JoystickButton driveForward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);


    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
       return mechJoystick;
    }

    public OI(){

      //actuatorForward.whileHeld(new ActuatorForward(actuatorForward));
      //actuatorReverse.whileHeld(new ActuatorReverse(actuatorReverse));

      cargoLiftUp.whileTrue(new CargoLiftUp(cargoLiftUp));
      SmartDashboard.putNumber("TestOI ", 100);
      
      
      

      climberDown.whileTrue(new ClimberDown(climberDown));
            // climberUp.whileHeld(new ClimberUp(climberUp));
      
      
      caitlinButton.whileTrue(new CaitlinComand(caitlinButton));
      
      reverseCargo.whileTrue(new CargoLiftDown(reverseCargo));
      
      // LLDrive.whileTrue(new LLDrive());

      //intakeIn.whileHeld(new IntakeIn(intakeIn));
      intakeIn.whileTrue(new IntakeIn(intakeIn));

      //cargoLiftUp.whenReleased(new StopFloor());
      
      // intakeIn.whenPressed(new StartFloor(cargoLiftUp));
      
      // cargoLiftUp.whenPressed(new StartFloor(cargoLiftUp));
      

      solenoidDumpForward.whileTrue(new SolenoidDumpForward());
      solenoidDumpReverse.whileTrue(new SolenoidDumpReverse());
      // pinwheelSpin.whileHeld(new PinwheelSpin(pinwheelSpin));
      // pinwheelCommand.whenPressed(new PinwheelCommand(pinwheelCommand));
      // ballLiftUp.whileHeld(new BallLiftUp(ballLiftUp));
      // ballLiftDown.whileHeld(new BallLiftDown(ballLiftDown));

      autoForward.whileTrue(new UltraDrive(autoForward, -0.3)); // (1) = direction, -1 for backward movement and 1 for forward movement
      autoBackward.whileTrue(new UltraDrive(autoBackward, 0.3));
      // autoLeft.whenPressed(new AutoTurn(90));
      // autoRight.whenPressed(new AutoTurn(-90));
      hubAlignVisionLeft.whileTrue(new HubAlignVisionLeft(hubAlignVisionLeft));
      // hubAlignVisionRight.whileHeld(new HubAlignVisionRight(hubAlignVisionRight));
      hubAlignVisionRight.whileTrue(new HubAlignVisionRight(hubAlignVisionRight));
      //I'm sorry this was the best I could think of for a trigger


      // try{
      //     String gmsg = DriverStation.getGameSpecificMessage();
      //     gmsg  = gmsg.isEmpty() ? "X" : gmsg.trim();
      //     colorWheelRotate.whenPressed( new ColorWheel(gmsg) );
      // }catch(Exception e0)
      // {

      // }

    }

    

}