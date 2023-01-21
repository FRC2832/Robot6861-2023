// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorLift extends SubsystemBase {
  /** Creates a new IngestorLift. */

  Talon ingestorLift = null;
  // TODO: Add sensor

  public IngestorLift() {
    ingestorLift = new Talon(Constants.IngestorMotor);
    //TODO: Confirm Constant Name
  }

  public void operatorController() {

  }

  public void raiseLift() {

  }

  public void lowerLift() {

  }

  public void stopLift() {

  }

  public boolean isAtBottom() {
    return false;
  }

  public boolean isAtTop() {
    return false;
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
