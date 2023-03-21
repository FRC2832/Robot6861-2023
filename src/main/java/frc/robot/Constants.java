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

    // Drivetrain current limit
    public static final int DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS = 50;
    public static final int INGESTOR_MOTOR_CURRENT_LIMIT_AMPS = 50;

    // Drivetrain controls slew rates
    // public static final double FORWARD_BACK_SLEW_RATE = .2;
    // public static final double LEFT_RIGHT_SLEW_RATE = .2;

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

    // Arm, Claw, and Brake CAN IDs
    public static final int ARM_MOTOR_ID = 17;
    public static final int CLAW_MOTOR_ID = 16;
    public static final int LOWER_BRAKE_MOTOR_ID = 18;

    public static final double CLAW_OPEN_SPEED = 0.2; // TODO: Test and adjust these values
    public static final double CLAW_CLOSE_SPEED = -0.2;
    public static final double LOWER_BRAKE_WHEEL_SPEED = -0.075;
    public static final double RAISE_BRAKE_WHEEL_SPEED = 0.05;
    public static final double BRAKE_ON_FLOOR = -28.0;
    public static final double BRAKE_UP_POSITION = 7.0;

    // Arm Motor Current Limit
    public static final int ARM_MOTOR_CURRENT_LIMIT_AMPS = 50;

    // Arm Motor Speeds
    // TODO: Find actual motor speed 
    public static final double ARM_MOTOR_SPEED = 0.3;

    // Arm Motor Positions
    // TODO: Find actual motor positions for each of these
    public static final double ARM_MOTOR_POSITION_PICKUP = 0;
    public static final double ARM_MOTOR_POSITION_STOW = 0;
    public static final double ARM_MOTOR_POSITION_SCORE = 0;

    // For running LED lights
    public static final int EYE_CANIFIER_ID = 4;

    // Game Piece Scoop Servo IDs
    public static final int GAME_PIECE_SCOOP_SERVO_MOTOR = 6;
    public static final int GAME_PIECE_SCOOP_MOTOR_CURRENT_LIMIT_AMPS = 1;
    public static final int GAME_PIECE_SCOOP_SERVO_R = 1;
    public static final int GAME_PIECE_SCOOP_SERVO_L = 6;

    // Pupil/Eyelid Servo IDs
    // TODO: confirm port with actual number on robot
    public static final int LEFT_EYELID_SERVO = 2; // 2 = pupil
    public static final int LEFT_PUPIL_SERVO = 3; // 3 = right eyelid
    public static final int RIGHT_EYELID_SERVO = 4; // 4 = lid
    public static final int RIGHT_PUPIL_SERVO = 5; // 5 = lid

    // TODO: set this to the actual camera name
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
    public static final double INGESTOR_EXPEL_POSITION = 55.0;

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
    public static final boolean INGESTOR_FAIL_STATUS = false;
}