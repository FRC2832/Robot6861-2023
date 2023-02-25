// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IngestorLift;

public class RaiseIngestorLiftCmd extends CommandBase {
    /** Creates a new MoveIngestorLiftCmd. */

    private IngestorLift ingestorLiftObj;
    private boolean isHomed;
    private boolean isAtShootingPosition;


    public RaiseIngestorLiftCmd(IngestorLift ingestorLift) {
        this.ingestorLiftObj = ingestorLift;
        addRequirements(ingestorLift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Zero the encoders
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (isHomed) {
            if (ingestorLiftObj.isAtScoring()) {
                ingestorLiftObj.stopLift();
                // Move the lift to the home position
            } else {
                ingestorLiftObj.lowerLiftToScore();

                // Move the lift to the shooting position
            }
            // Move the lift to the shooting position
        } else {
            ingestorLiftObj.raiseLift();
            if (ingestorLiftObj.isAtTop()) {
                isHomed = true;
            }
            // Move the lift to the home position
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
        return false; // DO NOT CHANGE THIS. This is a default command and should never end.
    }
}
