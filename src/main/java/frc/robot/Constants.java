// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
    public static final int DRIVETRAIN_LEFT_FRONT_TALON = 0;
    public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 0;
    public static final int DRIVETRAIN_LEFT_BACK_TALON = 0;
    public static final int DRIVETRAIN_RIGHT_BACK_TALON = 0;

    // Controller USB IDs
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 0;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 0;
    
    // Subsystem Motor IDs
    // TODO change motor IDs when get DT from electrical
    public static final int INGESTOR_INTAKE_TOP_TALON = 0;
    public static final int INGESTOR_INTAKE_BOTTOM_TALON = 0;
    public static final int INGESTOR_MOTOR = 0;
    public static final int CONE_FLIPPER_CANSPARKMAX = 0;
    public static final MotorType FLIPPER_MOTOR_TYPE = null;
    

    // TODO: set this to something else than 0
    public static final int LED_PWM_ID = 0;
    public static final int GAME_PIECE_SCOOP_SERVO_2 = 0;
    public static final int GAME_PIECE_SCOOP_SERVO_1 = 0;
    
    // TODO: set this to the actual camera name
    public static final String CAMERANAME = "photonvision";
    public static final String CAMERA2NAME = "photonvision";

}
