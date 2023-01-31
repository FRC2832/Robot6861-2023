// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GamePieceLift extends SubsystemBase {
    /** Creates a new GamePieceLift. */

    private CANSparkMax gamePieceLiftMotor;

    // TODO: Confirm the types of motors to be used
    public GamePieceLift() {
        gamePieceLiftMotor = new CANSparkMax(Constants.GAME_PIECE_MOTOR, Constants.GAME_PIECE_LIFT_MOTOR_TYPE);
        // TODO: Figure out motor type and CAN ID
    }

    public void liftArm() {

    }

    public void lowerArm() {
        
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
