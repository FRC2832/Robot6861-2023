// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.StrafeRightCmd;
import frc.robot.subsystems.Drivetrain;

public class BlueDefaultSubstationAuton extends SequentialCommandGroup {
    public BlueDefaultSubstationAuton(Drivetrain drivetrainObj) {
        addCommands(
                new DriveBackCmd(drivetrainObj, Constants.SUBSTATION_AUTON_DRIVE_BACK, Constants.AUTON_SPEED),
                new StrafeRightCmd(drivetrainObj, Constants.SUBSTATION_AUTON_STRAFE, Constants.AUTON_SPEED)
        );
    }
}
