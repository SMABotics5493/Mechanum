package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 *
 * @author: smabotics 2020
 * @version: 1.2
 * @since: 1/16/2020
 * @implSpec  RW solution for LL to provide better control mech from drive.
 * Objective:
 *  a) The object must be Immutable.
 *  b) The object must be Serializable.
 *  c) The object must be easy to consume by the Client for decision making.
 *  d) The object must be threadsafe.
 *  e) Zero external dependencies
 *
 * @TODO: Ma -> Recommend implementing this resource as Singleton.
 *
 * Client consume the class to get the Table information directly from the Ethernet .
 * use the Table elements for processing. Need to test this on Tuesday on control board.
 *
 *
 *
 * Super simple for Data acquisition. Now the magic happens in the code and what we do with this.
 * getting too many ideas.
 *
 *
 */


public class SMAVisionLL {

    protected NetworkTable networkTable;
    protected NetworkTableEntry tvValidTargetEntry;
    protected NetworkTableEntry txHorizontalOffset ; //-27 to +27deg
    protected NetworkTableEntry tyVerticalOffset ; //-20.7 to +20.7deg
    protected NetworkTableEntry taTargetArea  ;// in % of the target area recognized
    protected NetworkTableEntry tlLatency;// in % on general flow control latency
     // not sure we will have this, but good to establish the value based on how its mounted by the team.
    protected NetworkTableEntry tsRotation; // ts Skew rotation -90 to 0 deg.



    protected static final String LL_KEY = "limelight";
    protected static final String TX_KEY = "tx";
    protected static final String TY_KEY = "ty";
    protected static final String TV_Key = "tv";
    protected static final String TA_KEY = "ta";
    protected static final String TL_KEY = "tl";
    protected static final String TS_KEY = "ts";


    //#region -- Limelight LED mode to turn on and off

    protected static final String LED_MODE = "ledMode";
    protected static final double LED_DEFAULT = 0; // use as set in pipeline.
    protected static final double LED_OFF = 1;
    protected static final double LED_ON = 3;
    protected static final double LED_BLINK = 2;

    //#endregion -- Limelight LED mode

    //#region -- Limelight CAM mode

    private static final String CAM_MODE = "camMode";
    private static final double CAM_VISION = 0;
    private static final double CAM_DRIVER = 1;

    //#endregion

    /**
     * This will automatically initialize the table and make it operational - no need to call initialize again.
     */
    public SMAVisionLL()
    {
        // this is default constructor.
        // this.refresh();
        return ;
    }

    public void initialize()
    {
        this.refresh();
        return;
    }

    public void resetDevicePipeLatency()
    {
        this.networkTable.getEntry(TL_KEY).setValue(0.0);
        return;
    }

    /**
     * checks to see if LimeLight is connected and alive.
     * if the latency is not zero, the ll is connected.
     * @return
     */
    public boolean isActive()
    {
        this.refresh();
        return (this.getLatencyAsDouble() != 0.0f);

    }

    public boolean isTargetFound()
    {
        this.refresh();
        return (this.getTargetAreaAsDouble() != 0.0f);

    }

    //#region -- Limelight LED controls
    public void setLEDOn()
    {
        this.refresh();
        this.networkTable.getEntry(LED_MODE).forceSetValue(LED_ON);
        this.refresh();
    }

    public void setLEDOff()
    {
        this.refresh();
        this.networkTable.getEntry(LED_MODE).forceSetValue(LED_OFF);
        this.refresh();
    }

    public void setLEDBlink()
    {
        this.refresh();
        this.networkTable.getEntry(LED_MODE).forceSetValue(LED_BLINK);
        this.refresh();
    }

    public void SetLEDDefault()
    {
        this.refresh();
        this.networkTable.getEntry(LED_MODE).forceSetValue(LED_DEFAULT);
        this.refresh();
    }
    //#endregion -- Limelight LED controls end

    //#region CAM Mode Controls setting and getting

    /**
     * Sets the limelight to operate as a Vision processor.
     * by updating the network table entry camMode to 0
     */
    public void AsVisionProcessor()
    {
        this.setCamMode(CAM_VISION);
        return;
    }

    /**
     * sets the limelight to operate as a driver camera vision.
     */
    public void AsDriverCamProcessor()
    {
        this.setCamMode(CAM_DRIVER);
        return;
    }

    private void setCamMode(double controlValue)
    {
        this.refresh();
        this.networkTable.getEntry(CAM_MODE).forceSetDouble(controlValue);
        this.refresh();
        return;
    }

    //#endregion - CAM Mode controls setting and getting .

    /**
     * Every get calls this method internally to confirm we have a clean value base to work with.
     */
    private void refresh()
    {
        // read the values and initialize the variables.
        // as you are reading , display the values in smartboard
        this.networkTable = NetworkTableInstance.getDefault().getTable(LL_KEY);
        this.tvValidTargetEntry = this.networkTable.getEntry(TY_KEY);
        this.txHorizontalOffset = this.networkTable.getEntry(TX_KEY);
        this.tyVerticalOffset = this.networkTable.getEntry(TY_KEY);
        this.taTargetArea = this.networkTable.getEntry(TA_KEY);
        this.tlLatency = this.networkTable.getEntry(TL_KEY);
        this.tsRotation = this.networkTable.getEntry(TS_KEY);
        this.log();
        return ;

    }

    public Number  getXoffSet()
    {
        this.refresh();
        return (this.txHorizontalOffset.getNumber(0));

    }

    /**
     * offset from crosshair to target (-27 to +27)
     * @return
     */
    public double getXdegToTarget()
    {
        return (this.getXoffSet().doubleValue() );
    }

    public Number getYoffSet()
    {
        this.refresh();
        return (this.tyVerticalOffset.getNumber(0));
    }

    public double getYdegToTarget()
    {
        return (this.getYoffSet().doubleValue());
    }

    public Number getTargetArea()
    {
        this.refresh();
        return (this.taTargetArea.getNumber(0));
    }

    public double getTargetAreaAsDouble()
    {
        return (this.getTargetArea().doubleValue());
    }

    public Number  gettloffSet()
    {
        this.refresh();
        return (this.getLatency());
    }

    public Number getLatency()
    {
        this.refresh();
        return (this.tlLatency.getNumber(0));
    }

    public double getLatencyAsDouble()
    {
        return (this.getLatency().doubleValue());
    }

    public double getDistance(double heightOfLL, double heightOfTargetFromBase, double mountAngle)
    {
        double angleToGoalDegrees = mountAngle + this.getYdegToTarget();
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
        
        //calculate distance
        double distanceFromLimelightToGoalInches = (heightOfTargetFromBase - heightOfLL)/Math.tan(angleToGoalRadians);
        return distanceFromLimelightToGoalInches;
        // double distance = -666;
        // double totalAngleDeg = mountAngle + this.getYdegToTarget();
        // double totalAngleRad = Math.toRadians(totalAngleDeg);   //totalAngleDeg * (Math.PI / 180);
        // double thMinustl = heightOfTargetFromBase - heightOfLL; 
        // double tanOfTotalAngleRad = Math.tan(totalAngleRad);
        // distance =  thMinustl / tanOfTotalAngleRad ; 
        // return distance;
    }

    public void log()
    {
    //     System.out.println("LimelightX"+ this.getXoffSet());
    //     System.out.println("LimelightY"+ this.getYoffSet());
    //     System.out.println("LimelightArea"+ this.getXoffSet());
    //     System.out.println("Limelightlatency" + this.gettloffSet());

        // SmartDashboard.putNumber("LimelightX", this.getXoffSet().floatValue());
        // SmartDashboard.putNumber("LimelightY", this.getYoffSet().floatValue());
        // SmartDashboard.putNumber("LimelightArea", this.getTargetArea().floatValue());
        // SmartDashboard.putNumber("Limelightlatency", this.gettloffSet().floatValue());

        return ;
    }









}