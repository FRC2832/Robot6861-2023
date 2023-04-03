// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeTeleop;
import frc.robot.commands.claw.CloseClawCmd;
import frc.robot.commands.claw.OpenClawCmd;
import frc.robot.commands.drive.BalancePIDCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.subsystems.*;

// Same for red and blue
public class CoopBalanceAuton extends SequentialCommandGroup {
    // private EyeSubsystem eyeballObj = new EyeSubsystem();

    public CoopBalanceAuton(IngestorIntake ingestorIntake, IngestorLift ingestorLiftObj, GamePieceScoop gamePieceScoop, Drivetrain drivetrainObj, ClawSubsystem clawObj) {
        addCommands(
                //new OpenClawCmd(clawObj),
                //new ScoreIngestorLiftCmd(ingestorLiftObj),
                new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                new DriveBackCmd(drivetrainObj, Constants.COOP_AUTON_DRIVE_BACK, Constants.AUTON_SPEED + 0.1),
                new BalancePIDCmd(drivetrainObj, false),
                new OpenClawCmd(clawObj),
                new CloseClawCmd(clawObj),
                new OpenClawCmd(clawObj),
                new CloseClawCmd(clawObj)
                // eyeballObj.setDefaultCommand(
                // eyeballObj.setEyes(new EyeMovement(1, 1), new EyeMovement(1, 0), new
                // EyeColor(120, 120, 120)));
        );
    }
}
