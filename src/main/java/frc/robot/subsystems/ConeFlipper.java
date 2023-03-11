// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConeFlipper extends SubsystemBase {
    /** Creates a new ConeFlipper. */
    // TODO: Add encoder after confirming motor controller type
    private CANSparkMax coneFlipperMotor;
    private RelativeEncoder coneFlipperEncoder;
    private DigitalInput flipperLimitSwitch;

    public ConeFlipper() {
        coneFlipperMotor = new CANSparkMax(Constants.CONE_FLIPPER_CANSPARKMAX, MotorType.kBrushless);
        coneFlipperEncoder = coneFlipperMotor.getEncoder();
        flipperLimitSwitch = new DigitalInput(Constants.DIGITAL_INPUT_FLIPPER);
        // coneFlipperMotor = new CANSparkMax(Constants.CONE_FLIPPER_CANSPARKMAX,
        // Constants.FLIPPER_MOTOR_TYPE);
        // TODO: Figure out motor type, code movement of motor, code buttons
    }

    public void setFlipperSpeed(double speed) {
        coneFlipperMotor.set(speed);
        // TODO: Confirm motor controller type
        // TODO: find speed needed for the motor
        // TODO: Code the motor speed of the cone flipper
    }

    public void setFlipperIdleMode(IdleMode mode) {
        coneFlipperMotor.setIdleMode(mode);
    }

    public double getRotationCount() {
        return coneFlipperEncoder.getPosition();
    }

    public boolean getLimitSwitch() {
        return flipperLimitSwitch.get();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
