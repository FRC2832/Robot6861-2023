// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GamePieceScoop extends SubsystemBase {
    /** Creates a new GamePieceScoop. */

    private Servo gamePieceScoopServo1;
    private Servo gamePieceScoopServo2;
    // TODO: Figure out whether to change the number naming as is or to change to
    // something else

    public GamePieceScoop() {
        gamePieceScoopServo1 = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_1);
        gamePieceScoopServo2 = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_2);
    }

    public void servoOn() {

    }

    public void servoOff() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
