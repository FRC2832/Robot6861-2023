// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GamePieceScoop extends SubsystemBase {
    /** Creates a new GamePieceScoop. */

    private Servo gamePieceScoopServoL;
    private Servo gamePieceScoopServoR;
    // TODO: Figure out whether to change the number naming as is or to change to
    // something else

    public GamePieceScoop() {
        gamePieceScoopServoL = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_L);
        gamePieceScoopServoR = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_R);
        gamePieceScoopServoL.setBounds(1.8, 1.7, 1.5, 1.2, 1);
        gamePieceScoopServoR.setBounds(1.8, 1.7, 1.5, 1.2, 1);
    }

    public void servoOn() {
        gamePieceScoopServoL.setSpeed(1.0);
        gamePieceScoopServoR.setSpeed(1.0);
    }

    public void servoOff() {
        gamePieceScoopServoL.setSpeed(-1.0);
        gamePieceScoopServoR.setSpeed(-1.0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public CommandBase servoOffCmd() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    servoOff();
                });
    }

    public CommandBase servoOnCmd() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    servoOn();
                });
    }
}
