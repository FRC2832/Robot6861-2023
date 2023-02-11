// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EyeballSubsystem extends SubsystemBase {
  /** Creates a new EyeballSubsystem. */
  public EyeballSubsystem() {
    // Make the eyeball start as white
  }

  @Override
  public void periodic() {
    // FOR COLORS:

    // Ask for color of target (inside of ingestor)
    // Change LED color depending on this result
    // For teleop: White if nothing in ingestor, purple if cube in ingestor, yellow if cone in ingestor, red or blue depending on alliance for last few seconds
    // Also, white during auton

    // FOR PUPIL:
    // 
  }
}
