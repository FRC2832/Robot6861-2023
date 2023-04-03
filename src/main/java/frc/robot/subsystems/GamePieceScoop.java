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
    }
       

    public void servoOnTeleop() {
        

        gamePieceScoopServoLR.setVoltage(12);
       

    }

    public void servoOnAuton() {
       
        gamePieceScoopServoLR.setVoltage(12);
        
    }

    public void servoOff() {
        //System.out.println("Servo off");
        gamePieceScoopServoLR.setVoltage(-12);

    }
        

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public CommandBase servoOffCmd() {
        
        return run(
                () -> {
                    servoOff();
                });
    }

    public CommandBase servoOnAutonCmd() {
       
        return run(
                () -> {
                    servoOnAuton();
                });
    }


    public CommandBase servoOnCmd() {
      
        return run(
                () -> {
                    servoOnTeleop();

                    }

    

              );
    }
}