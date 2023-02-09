// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IngestorLift;

public class MoveIngestorLiftCmd extends CommandBase {
    /** Creates a new MoveIngestorLiftCmd. */

    private IngestorLift ingestorLiftObj;

    public MoveIngestorLiftCmd(IngestorLift ingestorLift) {
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
        ingestorLiftObj.lowerLift(0);
        // TODO: Figure out how to make the ingestorLift move up and down
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
        // Determine when the arm has fully moved
        return false;
    }
}
