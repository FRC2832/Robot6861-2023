// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.red;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeAuton;
import frc.robot.commands.drive.BalancePIDCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.DriveFwdCmd;
import frc.robot.commands.drive.StrafeLeftCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;

public class RedCableEngageAuton extends SequentialCommandGroup {

    public RedCableEngageAuton(Drivetrain drivetrainObj, IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
        addCommands(
            new ScoreCubeAuton(ingestorIntake, gamePieceScoop), 
            new DriveBackCmd(drivetrainObj, Constants.CABLE_AUTON_DRIVE_BACK, Constants.AUTON_SPEED),
            new StrafeLeftCmd(drivetrainObj, Constants.AUTON_BALANCING_STRAFE, Constants.AUTON_SPEED),
            new DriveFwdCmd(drivetrainObj, Constants.AUTON_BALANCING_DRIVE_FORWARD, Constants.AUTON_SPEED + 0.1),
            new BalancePIDCmd(drivetrainObj, false)
        );
    }
}
