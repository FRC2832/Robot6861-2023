// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorIntake extends SubsystemBase {
    /** Creates a new IngestorIntake. */

    private TalonSRX ingestorIntakeTopTalon;
    private TalonSRX ingestorIntakeBottomTalon;
    private DigitalInput ingestorBeamBreak;

    public IngestorIntake(TalonSRX ingestorIntakeTopTalon) {
        this.ingestorIntakeTopTalon = ingestorIntakeTopTalon;
        ingestorIntakeTopTalon = new TalonSRX(Constants.INGESTOR_INTAKE_UPPER_TALON);
        ingestorIntakeBottomTalon = new TalonSRX(Constants.INGESTOR_INTAKE_LOWER_TALON);
        ingestorBeamBreak = new DigitalInput(Constants.DIGITAL_INPUT_BEAM);

        /* Code for Talon SRX for intake current limiting
        ingestorIntakeTopTalon.configPeakCurrentLimit(Constants.INGESTOR_MOTOR_CURRENT_LIMIT_AMPS, 0);
        ingestorIntakeBottomTalon.configPeakCurrentLimit(Constants.INGESTOR_MOTOR_CURRENT_LIMIT_AMPS, 0);
        ingestorIntakeTopTalon.configContinuousCurrentLimit(Constants.INGESTOR_MOTOR_CURRENT_LIMIT_AMPS);
        ingestorIntakeBottomTalon.configContinuousCurrentLimit(Constants.INGESTOR_MOTOR_CURRENT_LIMIT_AMPS);
        ingestorIntakeTopTalon.enableCurrentLimit(true);
        ingestorIntakeBottomTalon.enableCurrentLimit(true);
        */
    }

    public void revIn() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
    }
/*
    public void revOut(double speed) {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, speed);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, speed);
    }
*/
    public void revOut(double speedTop, double speedBottom) {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, speedTop);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, speedBottom);
    }
    
    public CommandBase revOutIngestorIntake(double speed) {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    revOut(speed, speed);
                });
    }

    public CommandBase revOutIngestorIntakeNew(double speedTop, double speedBottom) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return run(
            () -> {
                revOut(speedTop, speedBottom);
                System.out.println("***********************  revOut: " + speedTop + " " + speedBottom + "  ***********************");

            });
    }
    public CommandBase revInIngestorIntake() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    revIn();
                    System.out.println("***********************  revIn: ");
                });
    }

    public boolean getIngestorBeamBreakValue() {
        System.out.println(ingestorBeamBreak.get());
        // True is NO gamepieces, False is gamepieces
        return ingestorBeamBreak.get();
    }

    public CommandBase ingestorBeamBreakCmd() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    getIngestorBeamBreakValue();
                });
    }

    public void stop() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, 0.0);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean isInScoop() {
        return false;
        // TODO: Confirm sensor location and type
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
