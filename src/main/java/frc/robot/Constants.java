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
    // make sure to set the ports when electrical gives us the info
    public static final int INGESTOR_MOTOR = 0;
    public static final int DRIVETRAIN_LEFT_FRONT_TALON = 0;
    public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 0;
    public static final int DRIVETRAIN_LEFT_BACK_TALON = 0;
    public static final int DRIVETRAIN_RIGHT_BACK_TALON = 0;
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;
    public static final int DRIVER_CONTROLLER_MOVE_AXIS = 0;
    public static final int DRIVER_CONTROLLER_ROTATE_AXIS = 0;
    public static final int INGESTOR_INTAKE_TOP_TALON = 0;
    public static final int INGESTOR_INTAKE_BOTTOM_TALON = 0;
    public static final int CONE_UPRIGHTER_CANSPARKMAX = 0;
    public static final MotorType UPRIGHT_MOTOR_TYPE = null;
    // TODO: set this to something else than 0
    public static final int LED_PWM_ID = 0;

}
