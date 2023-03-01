// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.StrafeLeftCmd;
import frc.robot.commands.drive.StrafeRightCmd;
import frc.robot.subsystems.Drivetrain;

public class DefaultAuton extends SequentialCommandGroup {
    // all distances in inches
    private double driveBackDist = 96;
    private double strafeDist = 48;

    public DefaultAuton(Drivetrain drivetrainObj) {
        Alliance alliance = DriverStation.getAlliance();

        if (alliance == Alliance.Red) {
            addCommands(new DriveBackCmd(drivetrainObj, driveBackDist, Constants.AUTON_SPEED));
            addCommands(new StrafeLeftCmd(drivetrainObj, strafeDist, Constants.AUTON_SPEED));
        } 

        else if (alliance == Alliance.Blue) {
            addCommands(new DriveBackCmd(drivetrainObj, driveBackDist, Constants.AUTON_SPEED));
            addCommands(new StrafeRightCmd(drivetrainObj, strafeDist, Constants.AUTON_SPEED));
        }
    }

}
