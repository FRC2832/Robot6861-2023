// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

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

    // Controller USB IDs
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 0;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 0;

    // Subsystem Motor IDs
    // TODO change motor IDs when get DT from electrical
    public static final int INGESTOR_INTAKE_UPPER_TALON = 13;
    public static final int INGESTOR_INTAKE_LOWER_TALON = 14;
    public static final int INGESTOR_MOTOR = 11;
    public static final int CONE_FLIPPER_CANSPARKMAX = 9;
    
    //TODO: must have a value other than null
    //public static final MotorType FLIPPER_MOTOR_TYPE = null;

    // TODO: set this to something else than 0
    public static final int LED_PWM_ID = 2; // confirm this number and whether we need this
    public static final int GAME_PIECE_SCOOP_SERVO_R = 1;
    public static final int GAME_PIECE_SCOOP_SERVO_L = 0;

    // Pupil/Eyelid Servo IDs
    public static final int LEFT_EYELID_SERVO = 3;
    public static final int LEFT_PUPIL_SERVO = 4;
    public static final int RIGHT_EYELID_SERVO = 5;
    public static final int RIGHT_PUPIL_SERVO = 6;

    // TODO: set this to the actual camera name
    public static final String CAMERANAME = "photonvision";
    public static final String CAMERA2NAME = "photonvision";

    // Encoder IDs
    //TODO: set these to the actual encoder IDs!!!!! Fix names. Must do this on Saturday
	// public static final Type LEFT_BACK_SPARK_ENCODER_TYPE = null;
	// public static final Type LEFT_FRONT_SPARK_ENCODER_TYPE = null;
	// public static final Type RIGHT_FRONT_SPARK_ENCODER = null;
	// public static final Type RIGHT_BACK_SPARK_ENCODER = null;

    public static final int PIGEON_TALON = 0; // TODO: Assign a Pigeon ID

    // IngestorIntake Subsystem Roller Motor Speeds
    public static final double INGESTOR_EXPEL_SPEED = -0.75;
    public static final double INGESTOR_INTAKE_SPEED = 0.75;
}