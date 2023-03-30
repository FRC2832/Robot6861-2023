// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.SparkMaxRelativeEncoder.Type;

import frc.robot.subsystems.eyes.EyeColor;
import frc.robot.subsystems.eyes.EyeMovement;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /*
     * public static class OperatorConstants {
     * public static final int kDriverControllerPort = 0;
     * }
     */

    // Drivetrain motor IDs
    public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 12;
    public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 10;
    public static final int DRIVETRAIN_LEFT_BACK_SPARK = 7;
    public static final int DRIVETRAIN_RIGHT_BACK_SPARK = 8;

    // Drivetrain current limit
    public static final int DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS = 50;

    // SparkMax ramp rate to full acceleration
    public static final double DRIVETRAIN_MOTOR_RAMP_RATE = 0.25;

    // Ingestor Current limit
    public static final int INGESTOR_MOTOR_CURRENT_LIMIT_AMPS = 50;

    // Drivetrain controls slew rates - didn't work.  Joysticks didn't move the robot at all
    // public static final double FORWARD_BACK_SLEW_RATE = .2;
    // public static final double LEFT_RIGHT_SLEW_RATE = .2;

    // Drive distance factors
    public static final int DRIVETRAIN_WHEEL_DIAMETER = 6; // units = 6 inches
    public static final double DRIVETRAIN_STRAFE_RATIO = 1.26;
    // using ratio because encoders aren't accurate when going sideways
    // ratio obtained becuase it went 3.75" when commanded to go 48"

    // Controller USB IDs
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 0;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 0;

    // Subsystem Motor IDs
    public static final int INGESTOR_INTAKE_UPPER_TALON = 13;
    public static final int INGESTOR_INTAKE_LOWER_TALON = 14;
    public static final int INGESTOR_MOTOR = 11;
    public static final int CONE_FLIPPER_CANSPARKMAX = 9;
    public static final int DIGITAL_INPUT_BEAM = 0;
    public static final int DIGITAL_INPUT_FLIPPER = 6;

    // Arm, Claw, and Brake CAN IDs
    public static final int ARM_MOTOR_ID = 17;
    public static final int CLAW_MOTOR_ID = 16;
    public static final int LOWER_BRAKE_MOTOR_ID = 18;
    public static final int DRIVE_BRAKE_MOTOR_ID = 15;  

    // Claw and Brake Motor Speeds
    public static final double CLAW_OPEN_SPEED = 0.50; 
    public static final double CLAW_CLOSE_SPEED = -0.95;
    public static final double LOWER_BRAKE_WHEEL_SPEED = -0.093;
    public static final double RAISE_BRAKE_WHEEL_SPEED = 0.088;
    public static final double DRIVE_BRAKE_MOTOR_SPEED = 0.55;

    // Brake up and down positions 
    public static final double BRAKE_ON_FLOOR = -40.0;
    public static final double BRAKE_UP_POSITION = -1.0;

    // Arm Motor Current Limit
    public static final int ARM_MOTOR_CURRENT_LIMIT_AMPS = 9;

    //Claw motor current limit
    public static final int CLAW_MOTOR_CURRENT_LIMIT_AMPS = 6;

    // Drop Wheel motor current limit
    public static final int BRAKE_WHEEL_MOTOR_CURRENT_LIMIT_AMPS = 50;
    public static final int DRIVE_BRAKE_MOTOR_CURRENT_LIMIT_AMPS = 50;


    // Arm Motor Speeds
    // TODO: Find actual motor speed
    public static final double ARM_MOTOR_SPEED = 0.4;
    public static final double ARM_STOW_MOTOR_SPEED = -0.6;
    public static final double ARM_RETRACT_MOTOR_SPEED = -0.1;

    // Arm Motor Positions
    public static final double ARM_MOTOR_POSITION_PICKUP = 140;
    public static final double ARM_MOTOR_POSITION_STOW = 0;
    public static final double ARM_MOTOR_POSITION_SCORE = 250;
    public static final double ARM_MOTOR_POSITION_RETRACT = -1000;

    // For running LED lights
    public static final int EYE_CANIFIER_ID = 4;

    // Game Piece Scoop Servo IDs
    public static final int GAME_PIECE_SCOOP_SERVO_MOTOR = 3;
    public static final int GAME_PIECE_SCOOP_MOTOR_CURRENT_LIMIT_AMPS = 1;
    //public static final int GAME_PIECE_SCOOP_SERVO_R = 1;
    //public static final int GAME_PIECE_SCOOP_SERVO_L = 6;

    // Pupil/Eyelid Servo IDs
    public static final int LEFT_EYELID_SERVO = 5; // Was 04
    public static final int LEFT_PUPIL_SERVO = 4; // Was 3
    public static final int RIGHT_EYELID_SERVO = 3; 
    public static final int RIGHT_PUPIL_SERVO = 2; 

    //Cameras
    public static final String DRIVER_CAM_NAME = "JeVois-A33_Video_Camera";
    public static final String ARM_CAM_NAME = "Microsoft_LifeCam_HD-3000";

    // Drivetrain Encoder IDs
    public static final Type LEFT_BACK_SPARK_ENCODER_TYPE = Type.kHallSensor;
    public static final Type LEFT_FRONT_SPARK_ENCODER_TYPE = Type.kHallSensor;
    public static final Type RIGHT_FRONT_SPARK_ENCODER = Type.kHallSensor;
    public static final Type RIGHT_BACK_SPARK_ENCODER = Type.kHallSensor;

    public static final int PIGEON_TALON = 13;

    // IngestorIntake Subsystem Roller Motor Speeds
    public static final double TOP_ROLLER_EXPEL_SPEED_HIGH = -0.95;
    public static final double LOWER_ROLLER_EXPEL_SPEED_HIGH = -0.85;
    public static final double INGESTOR_EXPEL_SPEED_HIGH = -0.95;
    public static final double INGESTOR_EXPEL_SPEED_MID = -0.75;
    public static final double INGESTOR_EXPEL_SPEED_LOW = -0.42;
    public static final double INGESTOR_INTAKE_SPEED = 0.8;

    // Ingestor Encoder positions
    public static final double INGESTOR_BOTTOM_POSITION = 75.0;
    public static final double INGESTOR_EXPEL_POSITION = 75.0;
    public static final double INGESTOR_SCORE_POSITION = 5.0;
    public static final double INGESTOR_TOP_POSITION = -5.0;
    public static final boolean INGESTOR_FAIL_STATUS = false;

    // Auton speed and drive distances (in inches)
    public static final double AUTON_SPEED = 0.4;
    public static final double SUBSTATION_AUTON_DRIVE_BACK = 89.0;
    public static final double SUBSTATION_AUTON_STRAFE = 30.0;
    public static final double CABLE_AUTON_DRIVE_BACK = 89.0;
    public static final double COOP_AUTON_DRIVE_BACK = 72.0;
    public static final double AUTON_BALANCING_STRAFE = 50.0;
    public static final double AUTON_BALANCING_DRIVE_FORWARD = 40.0;
    public static final double COOP_MOBILITY_DRIVE_FWD = 40;
    public static final double COOP_MOBILITY_DRIVE_BACK = 120;
    public static final double MOBILITY_SPEED = 0.5;
   
    
    // RGB VALUES FOR LED
    public static final EyeColor PURPLE = new EyeColor(130, 26, 90); //130. 26, 116 //0 is temporary for testing
    
    public static final EyeColor YELLOW = new EyeColor(255, 95, 10);

    public static final EyeColor WHITE = new EyeColor(255, 255, 195);

    public static final EyeColor RED = new EyeColor(255, 0, 0);

    public static final EyeColor BLUE = new EyeColor(30,105,255);

    public static final EyeColor LED_OFF = new EyeColor(0, 0, 0);

    // TODO: Verify that our comments are correct for both left and right eyes.
    public static final EyeMovement EYE_MOVEMENT_1 = new EyeMovement(0.0, 0.0); // opens the right eyelid, closes the left eyelid, and moves the pupils from the front of robot to the back
    public static final EyeMovement EYE_MOVEMENT_2 = new EyeMovement(0.0, 1.0); // opens the eyelids and moves the pupils from the back of robot to the front
    public static final EyeMovement EYE_MOVEMENT_3 = new EyeMovement(1.0, 0.0); // closes the eyelids and moves the pupils from the front of robot to the back
    public static final EyeMovement EYE_MOVEMENT_4 = new EyeMovement(1.0, 1.0); // closes the eyelids and moves the pupils from the back of robot to the front
    
   

    
}