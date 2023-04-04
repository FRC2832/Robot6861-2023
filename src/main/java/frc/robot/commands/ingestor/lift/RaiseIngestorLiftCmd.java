// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ingestor.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IngestorLift;

public class RaiseIngestorLiftCmd extends CommandBase {
    /**
     * Creates a new MoveIngestorLiftCmd.
     */

    private final IngestorLift ingestorLiftObj;

    public RaiseIngestorLiftCmd(IngestorLift ingestorLift) {
        this.ingestorLiftObj = ingestorLift;
        addRequirements(ingestorLift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //System.out.println("RaiseIngestorLiftCmd started.");
        //isHomed = ingestorLiftObj.isAtTop();
        // Zero the encoders
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (ingestorLiftObj.getLiftEncoderCountReal() > (Constants.INGESTOR_TOP_POSITION)) {
            // System.out.println("***********************  Ingestor Encoder: " + ingestorLiftObj.getLiftEncoderCountReal());
            ingestorLiftObj.stopLift();
        } else {
            ingestorLiftObj.raiseLift();
            ingestorLiftObj.getLiftEncoderCountReal();
            //System.out.println("***********************  Ingestor Encoder: " + ingestorObj.getLiftEncoderCountReal());
        }
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Bring the ingestorLift back to its default position
        ingestorLiftObj.stopLift();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false; //ingestorLiftObj.isAtTop();
    }
}
