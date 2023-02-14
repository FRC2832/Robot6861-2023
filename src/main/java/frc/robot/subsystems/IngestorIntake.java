// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorIntake extends SubsystemBase {
    /** Creates a new IngestorIntake. */

    private TalonSRX ingestorIntakeTopTalon;
    private TalonSRX ingestorIntakeBottomTalon;

    // TODO: Confirm the motor controller type

    public IngestorIntake(TalonSRX ingestorIntakeTopTalon) {
        this.ingestorIntakeTopTalon = ingestorIntakeTopTalon;
        ingestorIntakeBottomTalon = new TalonSRX(Constants.INGESTOR_INTAKE_LOWER_TALON);
        // Create a motorcontroller group?
    }

    public void operatorController() {

    }

    public void revIn() {

    }

    public void revOut() {

    }

    public void stop() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, 0.0);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean isInArm() {
        return false;
        // TODO: Confirm sensor location and type
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
