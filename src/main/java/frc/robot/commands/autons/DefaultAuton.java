// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.StrafeLeftCmd;
import frc.robot.subsystems.Drivetrain;

public class DefaultAuton extends SequentialCommandGroup {

  public DefaultAuton(Drivetrain drivetrainObj) {
    // TODO: driveBack is driving forwards... MUST FIX
    addCommands(new DriveBackCmd(drivetrainObj, Constants.DEFAULT_AUTON_DRIVE_BACK, Constants.AUTON_SPEED));
    addCommands(new StrafeLeftCmd(drivetrainObj, Constants.DEFAULT_AUTON_STRAFE, Constants.AUTON_SPEED));
  }

}
