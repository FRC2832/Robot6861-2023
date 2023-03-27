// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

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
    private static final double topPosition = 0.0;
    private static final double shootingPosition = 5.0;
    private DigitalInput ingestorLimitInput;
    private static double ingestorMotorSpeed = 0.9;
    private boolean isAtScoring;
    private boolean isHomed;

    public IngestorLift() {
        ingestorLiftMotor = new CANSparkMax(Constants.INGESTOR_MOTOR, CANSparkMax.MotorType.kBrushless);
        liftEncoder = ingestorLiftMotor.getEncoder();
        liftPIDController = ingestorLiftMotor.getPIDController();
        liftPIDController.setFeedbackDevice(liftEncoder);
        ingestorLimitInput = new DigitalInput(1);

        ingestorLiftMotor.setSmartCurrentLimit(Constants.INGESTOR_MOTOR_CURRENT_LIMIT_AMPS);

        // set PID coefficients
        liftPIDController.setP(1.0); // TODO: test value in Teleop
        liftPIDController.setI(0.00001);
        liftPIDController.setD(0.00001);
        liftPIDController.setIZone(0.0);
        liftPIDController.setFF(0.0);
        liftPIDController.setOutputRange(-1.0, 1.0);
        // have to burn flash to use this PID controller?? investigate another type of
        // PID controller?
    }

    public void raiseLift() {
        // TODO: Change to shooting position
        isAtScoring = false;
        goalPosition = topPosition;
        double positionReal = liftEncoder.getPosition();
        // liftPIDController.setReference(goalPosition, ControlType.kPosition);
        if (positionReal > 11 || positionReal < -160) {
            ingestorLiftMotor.set(0.0);
            System.out.println("********** ingestor position is " + positionReal);
            System.out.println("**********  WARNING!!!  Ingestor needs resetting. Power off and reset ingestor lift to top");
        } else {
            ingestorLiftMotor.set(ingestorMotorSpeed);
            System.out.println("********** ingestor position is raising " + positionReal);
        }
        

    }

    public void lowerLiftToIngest() {
        // Need some if statement to check if the limit switch is pressed
        // then zero the encoder and
        // set the goal position to the shooting position
        goalPosition = Constants.INGESTOR_BOTTOM_POSITION;
        double position = Math.abs(liftEncoder.getPosition());
        // follow the pid until the ingestor is 98% of the way there then let it drop
        // this if statement is set up for the case where the bottom position is a lower
        // number than the top position and the bottom position is not zero
        // TODO: check that the if statement is accurate for this encoder
        if (position < goalPosition + (Math.abs(goalPosition) * 0.02)) {
            System.out.println("********** ingestor is lowering to " + position);
            ingestorLiftMotor.set(-0.9); //change back to 0.9
            // isHomed = false;
        } else {
            ingestorLiftMotor.set(0.0);
            // System.out.println("Lowering to ingest. Current position is " + position);
        }
        ingestorLiftMotor.setIdleMode(IdleMode.kCoast);
        isHomed = false;
        isAtScoring = false;
        // System.out.println("isAtBottom after lowerLiftToIngest - " + isAtBottom);
        // System.out.println("isHomed after lowerLiftToIngest - " + isHomed);

    }

    public boolean lowerLiftToExpel() {
        // Need some if statement to check if the limit switch is pressed
        // then zero the encoder and
        // set the goal position to the shooting position
        goalPosition = Constants.INGESTOR_EXPEL_POSITION;
        double position = Math.abs(liftEncoder.getPosition());
        boolean flag;
        // follow the pid until the ingestor is 98% of the way there then let it drop
        // this if statement is set up for the case where the bottom position is a lower
        // number than the top position and the bottom position is not zero
        // TODO: check that the if statement is accurate for this encoder
        if (position < goalPosition + (Math.abs(goalPosition) * 0.02)) {
            ingestorLiftMotor.set(-0.9); //change back to -.9
            System.out.println("********** ingestor is lowering to expel" + position);
            flag = false;
            // isHomed = false;
        } else {
            ingestorLiftMotor.set(0.0);
            flag = true;
            // System.out.println("Lowering to ingest. Current position is " + position);
        }
        ingestorLiftMotor.setIdleMode(IdleMode.kCoast);
        isHomed = false;
        isAtScoring = false;
        // System.out.println("isAtBottom after lowerLiftToIngest - " + isAtBottom);
        // System.out.println("isHomed after lowerLiftToIngest - " + isHomed);
        return flag;
    }

    public void lowerLiftToScore() {
        // Need some if statement to check if the limit switch is pressed
        // then zero the encoder and
        // set the goal position to the shooting position

        goalPosition = shootingPosition;
        double position = Math.abs(liftEncoder.getPosition());
        // follow the pid until the ingestor is 98% of the way there then let it drop
        // this if statement is set up for the case where the bottom position is a lower
        // number than the top position and the bottom position is not zero
        // TODO: check that the if statement is accurate for this encoder
        // if (position > goalPosition + (Math.abs(goalPosition) * 0.02)
        // || position < goalPosition - (Math.abs(goalPosition) * 0.02)) {
        if (position < goalPosition) {
            ingestorLiftMotor.set(-0.1);
            // System.out.println(position);
            isAtScoring = false;
        } else if (position > 20.0) { // position changed to 20 from 30 to keep ingestor lift tucked in our frame a bit more
            ingestorLiftMotor.set(0.3); //change back 0.7,  increase speed to raise lift for faster operation
            System.out.println("We are moving to the scoring position. Position: " + position);
        } else {
            ingestorLiftMotor.set(0.0);
            // System.out.println("" + position);
            ingestorLiftMotor.setIdleMode(IdleMode.kBrake);
            isAtScoring = true;
        }
        // ingestorLiftMotor.setIdleMode(IdleMode.kCoast);
    }

    // takes in a value between 0 and 1 where 0 is the bottom and 1 is the top
    // sets a softer stop for raising and lowering the lift
    public void setLift(double percent) {
        if (percent <= 0.02) {
            lowerLiftToIngest();
        } else if (percent >= 0.98) {
            raiseLift();
        } else {
            goalPosition = percent * (topPosition - Constants.INGESTOR_BOTTOM_POSITION)
                    + Constants.INGESTOR_BOTTOM_POSITION;
            // TODO: check not going past limits? aka check 0 ≤ percent ≤ 1
            liftPIDController.setReference(goalPosition, ControlType.kPosition);
            ingestorLiftMotor.setIdleMode(IdleMode.kBrake);
        }
    }

    public void stopLift() {
        ingestorLiftMotor.set(0.0);
    }

    public boolean isAtBottom() {
        return false;
    }

    public boolean isAtTop() {
        System.out.println("isAtTop - limit switch is " + ingestorLimitInput.get());
        ingestorLiftMotor.set(0.0);
        return ingestorLimitInput.get() || Math.abs(liftEncoder.getPosition()) < 3.0;
        // during Jackson comp, limit switch broke so we aded this OR for added
        // robustness
    }

    public boolean getIsAtScoring() {
        return isAtScoring;
    }

    public boolean getIsHomed() {
        return isHomed;
    }

    public void setIsHomed(boolean isHomed) {
        this.isHomed = isHomed;
    }

    public double getEncoderCount() {
        return Math.abs(liftEncoder.getPosition());
    }

    public double getLiftEncoderCountReal() {
        return liftEncoder.getPosition();
    }

    public CommandBase raiseIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        // runOnce is truly run 1 processing loop. Might need run here. See ServoOn &
        // ServoOff in GamePieceSCoop
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
        // Subsystem::Run implicitly requires `this` subsystem.
        return run(
                () -> {
                    if (isAtBottom()) {
                        ingestorLiftMotor.set(0.0);

                    } else {
                        lowerLiftToIngest();
                        // Lower the ingestorLift
                    }
                });
    }

    public CommandBase stopIngestorLift() {
        // Inline construction of command goes here.
        // Subsystem::Run implicitly requires `this` subsystem.
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
