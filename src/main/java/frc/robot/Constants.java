// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.SparkMaxRelativeEncoder.Type;

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

    //Drivetrain current limit
    public static final int DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS = 60;

    // Drivetrain controls slew rates
    //public static final double FORWARD_BACK_SLEW_RATE = .2;
    //public static final double LEFT_RIGHT_SLEW_RATE = .2;


    // Drive distance factors
    public static final int DRIVETRAIN_WHEEL_DIAMETER = 6;
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

    // For running LED lights
    public static final int EYE_CANIFIER_ID = 4;

    // Game Piece Scoop Servo IDs
    public static final int GAME_PIECE_SCOOP_SERVO_R = 1;
    public static final int GAME_PIECE_SCOOP_SERVO_L = 6;

    // Pupil/Eyelid Servo IDs
    // TODO: confirm port with actual number on robot
    public static final int LEFT_EYELID_SERVO = 2; // 2 = pupil
    public static final int LEFT_PUPIL_SERVO = 3; // 3 = right eyelid
    public static final int RIGHT_EYELID_SERVO = 4; // 4 = lid
    public static final int RIGHT_PUPIL_SERVO = 5; // 5 = lid

    // TODO: set this to the actual camera name
    public static final String CAMERANAME = "photonvision";
    public static final String CAMERA2NAME = "photonvision";

    // Encoder IDs
    public static final Type LEFT_BACK_SPARK_ENCODER_TYPE = Type.kHallSensor;
    public static final Type LEFT_FRONT_SPARK_ENCODER_TYPE = Type.kHallSensor;
    public static final Type RIGHT_FRONT_SPARK_ENCODER = Type.kHallSensor;
    public static final Type RIGHT_BACK_SPARK_ENCODER = Type.kHallSensor;

    public static final int PIGEON_TALON = 13;

    // IngestorIntake Subsystem Roller Motor Speeds
    public static final double INGESTOR_EXPEL_SPEED_HIGH = -0.95;
    public static final double INGESTOR_EXPEL_SPEED_MID = -0.75;
    public static final double INGESTOR_EXPEL_SPEED_LOW = -0.42;
    public static final double INGESTOR_INTAKE_SPEED = 0.75;
    

    // Auton speed and drive distances (in inches)
    public static final double AUTON_SPEED = 0.3;
    public static final double SUBSTATION_AUTON_DRIVE_BACK = 96.0;
    public static final double SUBSTATION_AUTON_STRAFE = 48.0;
    public static final double CABLE_AUTON_DRIVE_BACK = 156.0;
    public static final double COOP_AUTON_DRIVE_BACK = 72.0;
    public static final double AUTON_BALANCING_STRAFE = 58.0;
    public static final double AUTON_BALANCING_DRIVE_FORWARD = 40.0;

    public static final double INGESTOR_BOTTOM_POSITION = 75.0;
    public static final double INGESTOR_EXPEL_POSITION = 55.0;
}