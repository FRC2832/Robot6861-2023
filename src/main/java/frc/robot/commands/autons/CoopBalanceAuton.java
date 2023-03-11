// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeAuton;
import frc.robot.commands.drive.BalancePIDCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;

// Same for red and blue
public class CoopBalanceAuton extends SequentialCommandGroup {

    public CoopBalanceAuton(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop, Drivetrain drivetrainObj) {
        addCommands(
            new ScoreCubeAuton(ingestorIntake, gamePieceScoop),
            new DriveBackCmd(drivetrainObj, Constants.COOP_AUTON_DRIVE_BACK, Constants.AUTON_SPEED + 0.1), 
            new BalancePIDCmd(drivetrainObj, false)
        );
    }
}
