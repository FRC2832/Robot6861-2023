// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
    /**
     * Creates a new ArmSubsystem.
     */
    private final CANSparkMax armMotor;
    private final SparkMaxPIDController armPIDController;
    private final RelativeEncoder armEncoder;
    private final double stowPosition;
    private final double retractPosition;
    private final double pickUpPosition;
    private final double scorePosition;
    private final double kP = 0.5;
    private final double kI = 0;
    private final double kD = 0;


    public ArmSubsystem() {
        armMotor = new CANSparkMax(Constants.ARM_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        armEncoder = armMotor.getEncoder();
        armPIDController = armMotor.getPIDController();
        armPIDController.setFeedbackDevice(armEncoder);
        stowPosition = Constants.ARM_MOTOR_POSITION_STOW;
        retractPosition = Constants.ARM_MOTOR_POSITION_RETRACT;
        pickUpPosition = Constants.ARM_MOTOR_POSITION_PICKUP;
        scorePosition = Constants.ARM_MOTOR_POSITION_SCORE;

        armMotor.setSmartCurrentLimit(Constants.ARM_MOTOR_CURRENT_LIMIT_AMPS);
    }


    public void armStowPos() {
        double position = armEncoder.getPosition();
        //double positionRealS = armEncoder.getPosition();
        //System.out.println("***********************  Arm Encoder in STOW : " + position);
        if (position < 10 && position > -10) {
            armMotor.set(0.00);
            armMotor.setIdleMode(IdleMode.kBrake);
        } else       //position > (stowPosition + (stowPosition * 0.05))) { // might need to be larger than 2%
            armMotor.set(Constants.ARM_STOW_MOTOR_SPEED);   // moving arm in
        //} //else if (position < -10) {
        //armMotor.set(-Constants.ARM_STOW_MOTOR_SPEED);  // move arm out
    }

    public void armRetractPos() {
        double position = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in armRetractPos : " + retractPosition);
        if (position > (retractPosition + (retractPosition * 0.05))) { // might need to be larger than 2%
            armMotor.set(Constants.ARM_RETRACT_MOTOR_SPEED);
        } else {         //if (position < (retractPosition - (retractPosition * 0.05))) {
            armMotor.set(0.00);
        }
        armMotor.setIdleMode(IdleMode.kBrake);
    }


    public void armPickUpPos() {
        double position = armEncoder.getPosition();
        //double positionRealP = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in PICKUP Pos : " + position);
        if (position < pickUpPosition - (pickUpPosition * 0.02)) {  // arm moving out to cone
            armMotor.set(Constants.ARM_MOTOR_SPEED);
        } else if (position > pickUpPosition + (pickUpPosition * 0.02)) {  //arm went past cone
            armMotor.set(-Constants.ARM_MOTOR_SPEED);
        } else {
            armMotor.set(0.00);
        }
        armMotor.setIdleMode(IdleMode.kCoast);
    }

    public void armScorePos() {
        double position = armEncoder.getPosition();
        //double positionRealS = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in SCORE  : " + position);
        if (position < scorePosition + (scorePosition * 0.04)) {
            armMotor.set(Constants.ARM_MOTOR_SPEED);
        } else if (position > scorePosition + (scorePosition * 0.04)) {
            armMotor.set(-Constants.ARM_MOTOR_SPEED);
        } else {
            armMotor.set(0.00);
        }
        armMotor.setIdleMode(IdleMode.kCoast);
    }

    public double getArmEncoder() {
        return armEncoder.getPosition();
    }

    public void stopArm() {
        armMotor.set(0.0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
