package frc.robot;

public class RobotMap{
    /* joysticks */
    public static final int DRIVER_JOYSTICK = 0;
    public static final int OP_JOYSTICK = 1;

        //axis
        public static final int DRIVE_CONTROLLER_LEFT_STICK = 1;
        public static final int DRIVE_CONTROLLER_RIGHT_STICK = 4;

        public static final int OP_CONTROLLER_LEFT_STICK = 1;
        public static final int OP_CONTROLLER_RIGHT_STICK = 4;

        //triggers *****************check numbers for everything 
        public static final int DRIVER_CONTORLLER_RT = 3;
        public static final int DRIVER_CONTORLLER_LT = 2;
        public static final int OP_CONTORLLER_RT = 3;
        public static final int OP_CONTORLLER_LT = 2;




        //buttons
        //driver buttons
            public static final int DRIVER_BUTTON_A = 1;
            public static final int DRIVER_BUTTON_B = 2;
            public static final int DRIVER_BUTTON_X = 3;
            public static final int DRIVER_BUTTON_Y = 4;

            //right bumper for intake in
            //bumper
            public static final int INTAKE_IN_BUMPER = 6;
            //left bumper for intake out 
            public static final int INTAKE_OUT_BUMPER = 5;

        //op buttons 
            public static final int OP_BUTTON_A = 1;
            public static final int OP_BUTTON_B = 2;
            public static final int OP_BUTTON_X = 3;
            public static final int OP_BUTTON_Y = 4;
            //bumpers
            public static final int MANUAL_SINGULATOR_TOWER_TOGGLE = 5;

            //start button
            public static final int GREEN_LIGHT_TOGGLE = 8;



    //motors
    /* driveTrain motors*/
	public static final int BACK_LEFT_TALON = 3;
	public static final int BACK_RIGHT_TALON = 1;
	public static final int FRONT_LEFT_TALON = 2;
    public static final int FRONT_RIGHT_TALON = 4;
    
    /* singulator motors*/
    public static final int SINGULATOR_LEFT_VICTOR = 6;
    public static final int SINGULATOR_RIGHT_VICTOR = 7;

    /* tower/singulator sensors */
    public static final int TOWER_SENSOR_TOP = 3;
    public static final int TOWER_SENSOR_LOW = 2;
    public static final int SINGULATOR_SENSOR = 4;

    /* climber motor*/ 
    public static final int CLIMB_SPARKMAX = 8;

    /* tower motor */
    public static final int TOWER_TALON = 11;

    /* climber limit swtiches */ 
    public static final int TOP_SWITCH = 0;
    public static final int BOTTOM_SWITCH = 1;

    /* pneumatics */
    public static final int PCM = 0;
    public static final int SOL_IN = 0;
    public static final int SOL_OUT = 1;

    /* vision */
    public static final int GREEN_RELAY = 9;
}