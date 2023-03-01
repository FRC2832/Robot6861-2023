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

    // TODO: Confirm the motor controller type

    public boolean getIngestorBeamBreakValue() {
        return ingestorBeamBreak.get();
    }

    public IngestorIntake(TalonSRX ingestorIntakeTopTalon) {
        this.ingestorIntakeTopTalon = ingestorIntakeTopTalon;
        ingestorIntakeTopTalon = new TalonSRX(Constants.INGESTOR_INTAKE_UPPER_TALON);
        ingestorIntakeBottomTalon = new TalonSRX(Constants.INGESTOR_INTAKE_LOWER_TALON);
        ingestorBeamBreak = new DigitalInput(Constants.DIGITAL_INPUT_BEAM);
        // Create a motorcontroller group?
    }

    public void revIn() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
    }

    public void revOut() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_EXPEL_SPEED);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_EXPEL_SPEED);
    }

    public CommandBase revOutIngestorIntake() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    revOut();
                });
    }

    public CommandBase revInIngestorIntake() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return run(
                () -> {
                    revIn();
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
