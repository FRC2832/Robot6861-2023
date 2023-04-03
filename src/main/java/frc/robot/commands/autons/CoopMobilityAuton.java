// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeTeleop;
import frc.robot.commands.drive.BalanceCrossCmd;
import frc.robot.commands.drive.BalanceMobilityCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.DriveFwdCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;


public class CoopMobilityAuton extends SequentialCommandGroup {
    /**
     * Creates a new CoopMobilityAuton.
     */

    public CoopMobilityAuton(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop, Drivetrain drivetrainObj) {
        addCommands(
                new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                new DriveBackCmd(drivetrainObj, Constants.COOP_MOBILITY_DRIVE_BACK, Constants.AUTON_SPEED + 0.1),
                new BalanceCrossCmd(drivetrainObj, true, false),
                new DriveFwdCmd(drivetrainObj, Constants.COOP_MOBILITY_DRIVE_FWD, Constants.MOBILITY_SPEED),
                new BalanceMobilityCmd(drivetrainObj)
        );
    }
}
