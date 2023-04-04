// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BrakeSubsystem extends SubsystemBase {
    private final CANSparkMax brakeWheelMotor;
    private final RelativeEncoder brakeWheelMotorEncoder;
    private final TalonSRX brakeDriveMotor;


    public BrakeSubsystem() {
        // up and down blue wheel under robot
        brakeWheelMotor = new CANSparkMax(Constants.LOWER_BRAKE_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        brakeWheelMotorEncoder = brakeWheelMotor.getEncoder();
        brakeWheelMotor.setSmartCurrentLimit(Constants.BRAKE_WHEEL_MOTOR_CURRENT_LIMIT_AMPS);

        // driven wheel in brake wheel - helps steady us on charge station in endgame
        brakeDriveMotor = new TalonSRX(Constants.DRIVE_BRAKE_MOTOR_ID);


        brakeWheelBrakeMode();

    }


    public void brakeWheelBrakeMode() {
        brakeWheelMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void driveBrakeMotor() {
        brakeDriveMotor.set(ControlMode.PercentOutput, Constants.DRIVE_BRAKE_MOTOR_SPEED);
    }

    public void driveBrakeMotorBack() {
        brakeDriveMotor.set(ControlMode.PercentOutput, -Constants.DRIVE_BRAKE_MOTOR_SPEED);
    }

    public void stopDriveBrakeMotor() {
        brakeDriveMotor.set(ControlMode.PercentOutput, 0.0);
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
