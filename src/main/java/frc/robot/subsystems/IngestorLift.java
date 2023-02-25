// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorLift extends SubsystemBase {
    /** Creates a new IngestorLift. */

    private CANSparkMax ingestorLiftMotor;
    private SparkMaxPIDController liftPIDController;
    private RelativeEncoder liftEncoder; 
    private double goalPosition;
    private final double topPosition = 0.0; 
    private final double bottomPosition = -65;
    private final double shootingPosition = -20;
    private DigitalInput ingestorLimitInput;
    private double ingestorMotorSpeed = 0.6;
    

    public IngestorLift() {
        ingestorLiftMotor = new CANSparkMax(Constants.INGESTOR_MOTOR, CANSparkMax.MotorType.kBrushless);
        liftEncoder = ingestorLiftMotor.getEncoder();
        liftPIDController = ingestorLiftMotor.getPIDController();
        liftPIDController.setFeedbackDevice(liftEncoder);
        ingestorLimitInput = new DigitalInput(1);

        // set PID coefficients
        liftPIDController.setP(1.0); // TODO: test value in Teleop
        liftPIDController.setI(0.00001);
        liftPIDController.setD(0.00001);
        liftPIDController.setIZone(0.0);
        liftPIDController.setFF(0.0);
        liftPIDController.setOutputRange(-1, 1);
        // have to burn flash to use this PID controller??  investigate another type of PID controller?
    }

    public void operatorController() {

    }

    public boolean ingestorLiftLimit() {
        return ingestorLimitInput.get();
    }

    public void raiseLift() {
        goalPosition = topPosition; 
        //liftPIDController.setReference(goalPosition, ControlType.kPosition);
        ingestorLiftMotor.set(ingestorMotorSpeed);


    }

    public void lowerLift() {
        // Need some if statement to check if the limit switch is pressed
        // then zero the encoder and
        // set the goal position to the shooting position
        
        goalPosition = bottomPosition;
        double position = liftEncoder.getPosition();
        // follow the pid until the ingestor is 98% of the way there then let it drop
        // this if statement is set up for the case where the bottom position is a lower number than the top position and the bottom position is not zero
        // TODO: check that the if statement is accurate for this encoder
        if (position > goalPosition + (Math.abs(goalPosition) * 0.02)) {
            ingestorLiftMotor.set(-0.5);
        } else {
            ingestorLiftMotor.set(0.0); 
        }
        ingestorLiftMotor.setIdleMode(IdleMode.kCoast);
    }

    // takes in a value between 0 and 1 where 0 is the bottom and 1 is the top
    // sets a softer stop for raising and lowering the lift
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
        if (ingestorLiftLimit()) {
            return true;
        } else{
            return false;
        }
    }

    public CommandBase raiseIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        // runOnce is truly run 1 processing loop.  Might need run here.  See ServoOn & ServoOff in GamePieceSCoop
        return run(
                () -> {
                    if (isAtTop()) {
                        ingestorLiftMotor.set(0.0);
                        liftEncoder.setPosition(0.0);

                    } else {
                        raiseLift();
                    }
                });
    }

    public CommandBase lowerIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    if (isAtBottom()) {
                        ingestorLiftMotor.set(0.0); 

                    } else {
                        lowerLift();
                        // Lower the ingestorLift
                    }
                });
    }
    
    public CommandBase stopIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    if (isAtBottom()) {
                        // TODO: change to isAtShootingPosition, 
                        // Set brake on ingestorLift motors
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
