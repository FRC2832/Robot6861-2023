// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
	/** Creates a new ArmSubsystem. */
	private CANSparkMax armMotor;
	private SparkMaxPIDController armPIDController;
	private RelativeEncoder armEncoder;
	private double stowPosition;
	private double pickUpPosition;
	private double scorePosition;
	private boolean isAtStow;
	private boolean isAtPickup;
	private boolean isAtScore;

	public ArmSubsystem() {
		armMotor = new CANSparkMax(Constants.ARM_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
		armEncoder = armMotor.getEncoder();
		armPIDController = armMotor.getPIDController();
		armPIDController.setFeedbackDevice(armEncoder);
        stowPosition = Constants.ARM_MOTOR_POSITION_SCORE;
        pickUpPosition = Constants.ARM_MOTOR_POSITION_PICKUP;
        scorePosition = Constants.ARM_MOTOR_POSITION_SCORE;

		armMotor.setSmartCurrentLimit(Constants.ARM_MOTOR_CURRENT_LIMIT_AMPS);

		// TODO: Set PID Coefficients for the arm
	}

	public void armStowPos() {
		double position = armEncoder.getPosition();
		// TODO: find out actual positions and change signs as necessary
		if (position > stowPosition + (Math.abs(stowPosition) * 0.02) && !isAtStow) { // might need to be larger than 2%
			armMotor.set(-Constants.ARM_MOTOR_SPEED);
		} else { // TODO: Change to comparing difference between position and stowPosition.
			armMotor.set(0.00);
			isAtStow = true;
		}
		armMotor.setIdleMode(IdleMode.kBrake);
	}

	public void armPickUpPos() {
		double position = armEncoder.getPosition();
		// TODO: find out actual positions and change signs as necessary
		if (position > pickUpPosition + (Math.abs(pickUpPosition) * 0.02) && !isAtPickup) {
			armMotor.set(-Constants.ARM_MOTOR_SPEED);
		} else if (position < pickUpPosition - (Math.abs(pickUpPosition) * 0.02) && !isAtPickup) {
            armMotor.set(Constants.ARM_MOTOR_SPEED);
		} else {
			armMotor.set(0.05);
			isAtPickup = true;
		}
		armMotor.setIdleMode(IdleMode.kCoast);

	}

	public void armScorePos() {
		double position = armEncoder.getPosition();
		// TODO: find out actual positions and change signs as necessary
		if (position > scorePosition + (Math.abs(scorePosition) * 0.02)) {
			armMotor.set(-Constants.ARM_MOTOR_SPEED);
		} else if (position < scorePosition + (Math.abs(scorePosition) * 0.02) ) {
			armMotor.set(Constants.ARM_MOTOR_SPEED);
		} else {
			armMotor.set(0.05);
			isAtScore = true;
		}
		armMotor.setIdleMode(IdleMode.kCoast);
	}

	public double getArmEncoder() {
		return armEncoder.getPosition();
	}

	public void setIsAtStow(boolean isAtStow) {
		this.isAtStow = isAtStow;
	}

	public boolean getIsAtStow() {
		return isAtStow;
	}

	public void setIsAtPickup(boolean isAtPickup) {
		this.isAtPickup = isAtPickup;
	}

	public boolean getIsAtPickup() {
		return isAtPickup;
	}

	public void setIsAtScore(boolean isAtScore) {
		this.isAtScore = isAtScore;
	}
	
	public boolean getIsAtScore() {
		return isAtScore;
	}

	public void stopArm() {
		armMotor.set(0.0);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
