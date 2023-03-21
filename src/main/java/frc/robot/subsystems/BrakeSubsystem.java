// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BrakeSubsystem extends SubsystemBase {
    private CANSparkMax brakeWheelMotor;
    private RelativeEncoder brakeWheelMotorEncoder;

    public BrakeSubsystem() {
        brakeWheelMotor = new CANSparkMax(Constants.LOWER_BRAKE_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        brakeWheelMotorEncoder = brakeWheelMotor.getEncoder();
        brakeWheelMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void lowerBrakes() {
        brakeWheelMotor.set(Constants.LOWER_BRAKE_WHEEL_SPEED);
    }

    public void raiseBrakes() {
        brakeWheelMotor.set(Constants.RAISE_BRAKE_WHEEL_SPEED);
    }

    public void stopBrakes() {
        brakeWheelMotor.set(0.0);
    }

    public double getBrakeEncoder() {
        return brakeWheelMotorEncoder.getPosition();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
