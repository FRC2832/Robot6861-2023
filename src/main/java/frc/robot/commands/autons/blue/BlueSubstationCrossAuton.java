// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.blue;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeTeleop;
import frc.robot.commands.claw.OpenClawCmd;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.commands.drive.StrafeRightCmd;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;

public class BlueSubstationCrossAuton extends SequentialCommandGroup {

    public BlueSubstationCrossAuton(Drivetrain drivetrainObj, IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop, ClawSubsystem clawSubsystemObj) {
        //IngestorLift ingestorLiftObj;
        /*addCommands(

                new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                //new ScoreCubeAuton(ingestorIntake, gamePieceScoop),
                new DriveBackCmd(drivetrainObj, Constants.SUBSTATION_AUTON_DRIVE_BACK, Constants.AUTON_SPEED),
                new StrafeRightCmd(drivetrainObj, Constants.SUBSTATION_AUTON_STRAFE, Constants.AUTON_SPEED)
        );
    }*/


        addCommands(
        new ParallelCommandGroup(
            new OpenClawCmd(clawSubsystemObj), 
            new SequentialCommandGroup(
                new ScoreCubeTeleop(ingestorIntake, gamePieceScoop),
                //new ScoreCubeAuton(ingestorIntake, gamePieceScoop),
                new DriveBackCmd(drivetrainObj, Constants.SUBSTATION_AUTON_DRIVE_BACK, Constants.AUTON_SPEED), 
                new StrafeRightCmd(drivetrainObj, Constants.SUBSTATION_AUTON_STRAFE, Constants.AUTON_SPEED)
                )
            )
        );  //*/;
    }
}
