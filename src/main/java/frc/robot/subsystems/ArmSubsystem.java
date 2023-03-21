// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    /** Creates a new ArmSubsystem. */
    private CANSparkMax armMotor;
    private SparkMaxPIDController armPIDController;
    private RelativeEncoder armEncoder;
    private double stowPosition;
    private double pickUpPosition;
    private double scorePosition;

    public ArmSubsystem() {
        armMotor = new CANSparkMax(Constants.ARM_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        armEncoder = armMotor.getEncoder();
        armPIDController = armMotor.getPIDController();
        armPIDController.setFeedbackDevice(armEncoder);

        armMotor.setSmartCurrentLimit(Constants.ARM_MOTOR_CURRENT_LIMIT_AMPS);

        // TODO: Set PID Coefficients for the arm
    }

    public void ArmStowPos() {
        double position = armEncoder.getPosition();
        if (position > stowPosition) {
            armMotor.set(-Constants.ARM_MOTOR_SPEED);
        }
        if (position == stowPosition) {
            armMotor.set(0.0);
        }
        armMotor.setIdleMode(IdleMode.kCoast);
    }

    public void ArmPickUpPos() {
        double position = armEncoder.getPosition();
        if (position > pickUpPosition) {
            armMotor.set(-Constants.ARM_MOTOR_SPEED);
        }
        if (position == pickUpPosition) {
            armMotor.set(0.0);
        }
        if (position < pickUpPosition) {
            armMotor.set(Constants.ARM_MOTOR_SPEED);
        }
        armMotor.setIdleMode(IdleMode.kCoast);

    }

    public void ArmScorePos() {
        double position = armEncoder.getPosition();
        if (position > scorePosition) {
            armMotor.set(-Constants.ARM_MOTOR_SPEED);
        }
        if (position == scorePosition) {
            armMotor.set(0.0);
        }
        if (position < scorePosition) {
            armMotor.set(Constants.ARM_MOTOR_SPEED);
        }
        armMotor.setIdleMode(IdleMode.kCoast);
    }
    public double getArmEncoder() {
        return armEncoder.getPosition();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
