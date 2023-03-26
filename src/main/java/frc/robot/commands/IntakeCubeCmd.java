// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class IntakeCubeCmd extends CommandBase {
    /** Creates a new ScoreCubeCmd. */
    private IngestorIntake ingestorIntakeObj;
    private GamePieceScoop gamePieceScoopObj;
    //private static final Timer intakeTimer = new Timer();

    public IntakeCubeCmd(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
        this.ingestorIntakeObj = ingestorIntake;
        this.gamePieceScoopObj = gamePieceScoop;
        addRequirements(ingestorIntake, gamePieceScoop);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // check that servo is out. If servo is in, then move it out.
        // elseIf servo is out, start wheels turning backwards
        // System.out.println("IntakeCubeCmd: initialize");
        gamePieceScoopObj.servoOnTeleop();
        EyeSubsystem.setDefaultColor(Constants.PURPLE);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        ingestorIntakeObj.revIn();
        /*if (ingestorIntakeObj.getIngestorBeamBreakValue()) {
            if (intakeTimer.get() > 0.0) {
                // Do nothing
            } else {
                intakeTimer.reset();
                intakeTimer.start();
            }
        } else {
            intakeTimer.stop();
            intakeTimer.reset();
        }*/
        //Beam break not currently working, only way to determine current ingestion is through a non button press
       /* if (!ingestorIntakeObj.getIngestorBeamBreak().get()) {
            ingestorIntakeObj.revIn();
        } else {
            ingestorIntakeObj.stop();
        }*/
    }
   

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        if (!ingestorIntakeObj.isCubeInIngestor()) {
            EyeSubsystem.setDefaultColor(Constants.WHITE);
        }
        //intakeTimer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return ingestorIntakeObj.isCubeInIngestor(); 
        // TRUE when cube in scoop
        //&& intakeTimer.get() > 2.0;

    }
}
