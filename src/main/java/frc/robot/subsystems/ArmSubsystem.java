// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
	/** Creates a new ArmSubsystem. */
	private CANSparkMax armMotor;
	private SparkMaxPIDController armPIDController;
	private RelativeEncoder armEncoder;
	private double stowPosition;
	private double retractPosition;
	private double pickUpPosition;
	private double scorePosition;
    private double kP = 0.5;
	private double kI = 0;
	private double kD = 0;
    

	public ArmSubsystem() {
		armMotor = new CANSparkMax(Constants.ARM_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
		armEncoder = armMotor.getEncoder();
		armPIDController = armMotor.getPIDController();
		armPIDController.setFeedbackDevice(armEncoder);
        stowPosition = Constants.ARM_MOTOR_POSITION_STOW;
		retractPosition = Constants.ARM_MOTOR_POSITION_RETRACT;
        pickUpPosition = Constants.ARM_MOTOR_POSITION_PICKUP;
        scorePosition = Constants.ARM_MOTOR_POSITION_SCORE;

		armMotor.setSmartCurrentLimit(Constants.ARM_MOTOR_CURRENT_LIMIT_AMPS);

		// TODO: Set PID Coefficients for the arm
        // liftPIDController.setP(1.0); // TODO: test value in Teleop
        // liftPIDController.setI(0.00001);
        // liftPIDController.setD(0.00001);
        // liftPIDController.setIZone(0.0);
        // liftPIDController.setFF(0.0);
        // liftPIDController.setOutputRange(-1.0, 1.0);

        
        // PIDController pid = new PIDController(kP, kI, kD);
        // Calculates the output of the PID algorithm based on the sensor reading
         // and sends it to a motor

        //armMotor.set(pid.calculate(armEncoder.getDistance(), setpoint));
	}

	public void armStowPos() {
		double position = (Math.abs(armEncoder.getPosition()));
        double positionRealS = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in STOW : " + positionRealS);

		// TODO: find out actual positions and change signs as necessary
		if (position > (stowPosition + (stowPosition * 0.05))) { // might need to be larger than 2%
			armMotor.set(-Constants.ARM_STOW_MOTOR_SPEED);
		} else { // TODO: Change to comparing difference between position and stowPosition.
			armMotor.set(0.00);
		}
		armMotor.setIdleMode(IdleMode.kBrake);
	}

	public void armRetractPos() {
		double position = armEncoder.getPosition();
        // System.out.println("***********************  Arm Encoder in armRetractPos : " + retractPosition);

		// TODO: find out actual positions and change signs as necessary
		if (position > (retractPosition + (retractPosition * 0.02))) { // might need to be larger than 2%
			armMotor.set(-Constants.ARM_RETRACT_MOTOR_SPEED);
		} else { // TODO: Change to comparing difference between position and retractPosition.
			armMotor.set(0.00);
		}
		armMotor.setIdleMode(IdleMode.kBrake);
	}


    // TODO: Needs PID!
	public void armPickUpPos() {
		double position = (Math.abs(armEncoder.getPosition()));
        double positionRealP = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in PICKUP Pos : " + positionRealP);

		// TODO: find out actual positions and change signs as necessary
        
        if (position > pickUpPosition + (pickUpPosition * 0.02)) {
			armMotor.set(-Constants.ARM_MOTOR_SPEED);
		} else if (position < pickUpPosition - (pickUpPosition * 0.02)) {
            armMotor.set(Constants.ARM_MOTOR_SPEED);
		} else {
			armMotor.set(0.00);
		}
		armMotor.setIdleMode(IdleMode.kCoast);

	}

     // TODO: Needs PID!
	public void armScorePos() {
		double position = (Math.abs(armEncoder.getPosition()));
        double positionRealS = armEncoder.getPosition();
        System.out.println("***********************  Arm Encoder in SCORE  : " + positionRealS);

		// TODO: find out actual positions and change signs as necessary
		if (position > scorePosition + (scorePosition * 0.04)) {
			armMotor.set(-Constants.ARM_MOTOR_SPEED);
		} else if (position < scorePosition + (scorePosition * 0.04) ) {
			armMotor.set(Constants.ARM_MOTOR_SPEED);
		} else {
			armMotor.set(0.00);
		}
		armMotor.setIdleMode(IdleMode.kCoast);
	}

	public double getArmEncoder() {
		return armEncoder.getPosition();
	}
/* 
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
	} */

	public void stopArm() {
		armMotor.set(0.0);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
