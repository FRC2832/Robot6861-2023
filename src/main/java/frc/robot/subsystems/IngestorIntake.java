// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorIntake extends SubsystemBase {
  /** Creates a new IngestorIntake. */

  private Talon ingestorIntakeTop;
  private Talon ingestorIntakeBottom;
  // TODO: Confirm the motor controller type

  public IngestorIntake() {
      ingestorIntakeTop = new Talon(Constants.INGESTOR_INTAKE_TOP_TALON);
      ingestorIntakeBottom = new Talon(Constants.INGESTOR_INTAKE_BOTTOM_TALON);
  }

  public void operatorController() {

  }

  public void ingest() {

  }

  public void expel() {

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