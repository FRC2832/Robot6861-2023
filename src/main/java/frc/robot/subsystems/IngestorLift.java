// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorLift extends SubsystemBase {
    /** Creates a new IngestorLift. */

    private CANSparkMax ingestorLiftMotor;
    private SparkMaxPIDController liftPIDController;
    private RelativeEncoder liftEncoder; 
    private double goalPosition;
    private final double topPosition = 0.0; // TODO: determine the top in the rev client
    private final double bottomPosition = 0.0; // TODO: determine the bottom in the rev client
    // TODO: Add sensor

    public IngestorLift() {
        ingestorLiftMotor = new CANSparkMax(Constants.INGESTOR_MOTOR, CANSparkMax.MotorType.kBrushless);
        // TODO: Confirm Constant Name
        liftEncoder = ingestorLiftMotor.getEncoder();
        liftPIDController = ingestorLiftMotor.getPIDController();
        liftPIDController.setFeedbackDevice(liftEncoder);

        // set PID coefficients
        liftPIDController.setP(1.0); // TODO: test values
        liftPIDController.setI(0.0);
        liftPIDController.setD(0.0);
        liftPIDController.setIZone(0.0);
        liftPIDController.setFF(0.0);
        liftPIDController.setOutputRange(-1.0, 1.0);
    }

    public void operatorController() {

    }

    public void raiseLift() {
        goalPosition = topPosition; 
        liftPIDController.setReference(goalPosition, ControlType.kPosition);
    }

    public void lowerLift() {
        goalPosition = bottomPosition;
        double position = liftEncoder.getPosition();
        // follow the pid until the ingestor is 98% of the way there then let it drop
        // this if statement is set up for the case where the bottom position is a lower number than the top position and the bottom position is not zero
        // TODO: check that the if statement is accurate for this encoder
        if (position > goalPosition + (Math.abs(goalPosition) * 0.02)) {
            liftPIDController.setReference(goalPosition, ControlType.kPosition);
        } else {
            ingestorLiftMotor.set(0.0); 
        }
        ingestorLiftMotor.setIdleMode(IdleMode.kCoast);
    }

    // takes in a value between 0 and 1 where 0 is the bottom and 1 is the top
    public void setLift(double percent) {
        if (percent <= 0.02) {
            lowerLift();
        } else if (percent >= 0.98) {
            raiseLift();
        } else {
            goalPosition = percent * (topPosition - bottomPosition) + bottomPosition; 
            // TODO: check not going past limits? aka check 0 ≤ percent ≤ 1
            liftPIDController.setReference(goalPosition, ControlType.kPosition);
            ingestorLiftMotor.setIdleMode(IdleMode.kBrake);
        }
    }

    public void stopLift() {

    }

    public boolean isAtBottom() {
        return false; // TODO: Define sensor behavior here
    }

    public boolean isAtTop() {
        return false; // TODO: Define sensor behavior here
    }

    public CommandBase raiseIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    if (isAtTop()) {
                        // TODO: Send 0 to the ingestorLift motors
                    } else {
                        // TODO: Raise the ingestorLift
                    }
                });
    }

    public CommandBase lowerIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    if (isAtBottom()) {
                        // TODO: Send 0 to the ingestorLift motors
                    } else {
                        // TODO: Lower the ingestorLift
                    }
                });
    }
    
    public CommandBase stopIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    if (isAtBottom()) {
                        // TODO: Send 0 to the ingestorLift motors
                    } else {
                        // TODO: Lower the ingestorLift
                    }
                });
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
