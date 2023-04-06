// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.red;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeTeleop;
import frc.robot.commands.claw.OpenClawCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.StrafeLeftCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.ClawSubsystem;

public class RedSubstationCrossAuton extends SequentialCommandGroup {

    public RedSubstationCrossAuton(Drivetrain drivetrainObj, IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop,  ClawSubsystem clawSubsystemObj) {
        addCommands(
            new ParallelCommandGroup(
                        new OpenClawCmd(clawSubsystemObj),
                    new SequentialCommandGroup(
                        new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                        new DriveBackCmd(drivetrainObj, Constants.SUBSTATION_AUTON_DRIVE_BACK, Constants.AUTON_SPEED),
                        new StrafeLeftCmd(drivetrainObj, Constants.SUBSTATION_AUTON_STRAFE, Constants.AUTON_SPEED)
        )));
    }
}
