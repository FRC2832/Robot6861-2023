// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;


public class IntakeCubeCmd extends CommandBase {
    /** Creates a new ScoreCubeCmd. */
    private IngestorIntake ingestorIntakeObj;
    private GamePieceScoop gamePieceScoopObj;

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
        gamePieceScoopObj.servoOff();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(ingestorIntakeObj.getIngestorBeamBreak().get()) {
            ingestorIntakeObj.revIn();
        } else {
            ingestorIntakeObj.stop();
        }
    }
    // TODO: Create a new command for ingestorLift
    // TODO: If ingestorLift is at upper limit switch then zero the encoders

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // TODO: Use beam break sensors to determine if we shot or not
        
        return false;
    }
}
