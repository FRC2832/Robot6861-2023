// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GamePieceScoop extends SubsystemBase {
    /** Creates a new GamePieceScoop. */

    //private Servo gamePieceScoopServoL;
    //private Servo gamePieceScoopServoR;
    private static Timer timer = new Timer();
    private CANSparkMax gamePieceScoopServoLR;


    public GamePieceScoop() {
        gamePieceScoopServoLR = new CANSparkMax(Constants.GAME_PIECE_SCOOP_SERVO_MOTOR, CANSparkMax.MotorType.kBrushed);
        gamePieceScoopServoLR.setSmartCurrentLimit(Constants.GAME_PIECE_SCOOP_MOTOR_CURRENT_LIMIT_AMPS);
       
        /*gamePieceScoopServoL = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_L);
        gamePieceScoopServoR = new Servo(Constants.GAME_PIECE_SCOOP_SERVO_R);
        gamePieceScoopServoL.setBounds(1.4, 1.3, 1.2, 1.1, 1.0);
        gamePieceScoopServoR.setBounds(1.4, 1.3, 1.2, 1.1, 1.0);*/
    }

    /*public void setServoBoundsAuton() {
        gamePieceScoopServoL.setBounds(1.4, 1.3, 1.2, 1.1, 1.0);
        gamePieceScoopServoR.setBounds(1.4, 1.3, 1.2, 1.1, 1.0);
    }

    public void setServoBoundsTeleop() {
        gamePieceScoopServoL.setBounds(1.8, 1.6, 1.4, 1.2, 1.0);
        gamePieceScoopServoR.setBounds(1.8, 1.6, 1.4, 1.2, 1.0);
    }*/


    public void servoOnTeleop() {
        //setServoBoundsTeleop();
        //System.out.println("Servo on");

        gamePieceScoopServoLR.setVoltage(12);
       
        //gamePieceScoopServoL.setSpeed(1.0);
        //gamePieceScoopServoR.setSpeed(1.0);
    }

    public void servoOnAuton() {
        //System.out.println("Servo on Auton voltage commanded");
        gamePieceScoopServoLR.setVoltage(12);
        
    }

    public void servoOff() {
        //System.out.println("Servo off");
        gamePieceScoopServoLR.setVoltage(-12);
        // gamePieceScoopServoLR.set(0.33);

        //gamePieceScoopServoL.setSpeed(-1.0);
        //gamePieceScoopServoR.setSpeed(-1.0);
    }
    /*public void servoOnAuton() {
        setServoBoundsAuton();
        gamePieceScoopServoL.setSpeed(1.0);
        gamePieceScoopServoR.setSpeed(1.0);
    }
    */

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

    public CommandBase servoOnAutonCmd() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    servoOnAuton();
                });
    }


    public CommandBase servoOnCmd() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    servoOnTeleop();

                    }

                    /* timer.start();
                    if (timer.get() < 6.0) {
                        servoOnTeleop();
                    }
                    else {
                        timer.stop();
                        timer.reset();
                        gamePieceScoopServoLR.setVoltage(0);*/

              );
    }
}