// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autons.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ScoreCubeAuton;
import frc.robot.commands.drive.DriveBackCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BlueCableCrossAuton extends SequentialCommandGroup {

    public BlueCableCrossAuton(Drivetrain drivetrainObj, IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
        addCommands(
            new ScoreCubeAuton(ingestorIntake, gamePieceScoop), 
            new DriveBackCmd(drivetrainObj, Constants.CABLE_AUTON_DRIVE_BACK, Constants.AUTON_SPEED)
        );
    }
}
