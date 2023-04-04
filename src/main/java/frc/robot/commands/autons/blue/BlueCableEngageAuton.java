// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeTeleop;
import frc.robot.commands.drive.BalancePIDCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.DriveFwdCmd;
import frc.robot.commands.drive.StrafeRightCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;

public class BlueCableEngageAuton extends SequentialCommandGroup {

    public BlueCableEngageAuton(Drivetrain drivetrainObj, IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
        addCommands(
                new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                new DriveBackCmd(drivetrainObj, Constants.CABLE_AUTON_DRIVE_BACK, Constants.AUTON_SPEED),
                new StrafeRightCmd(drivetrainObj, Constants.AUTON_BALANCING_STRAFE, Constants.AUTON_SPEED),
                new DriveFwdCmd(drivetrainObj, Constants.AUTON_BALANCING_DRIVE_FORWARD, Constants.AUTON_SPEED + 0.1),
                new BalancePIDCmd(drivetrainObj, false)
        );
    }
}
