// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ingestor.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class ScoreIngestorLiftCmd extends CommandBase {
    /**
     * Creates a new MoveIngestorLiftCmd.
     */

    // private int victoryCounter = 0;
    private final IngestorLift ingestorLiftObj;

    public ScoreIngestorLiftCmd(IngestorLift ingestorLift) {
        this.ingestorLiftObj = ingestorLift;
        addRequirements(ingestorLift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // System.out.println("ScoreIngestorLiftCmd started.");
        // isHomed = ingestorLiftObj.isAtTop();
        // Zero the encoders
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        ingestorLiftObj.lowerLiftToScore();
        //EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
        //EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
        // Move the lift to the shooting position
        /*
         * if (victoryCounter % 50 < 25) {
         * EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
         * EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
         * } else {
         * EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_3);
         * EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
         * }
         * 
         * victoryCounter++;
         * victoryCounter %= 50;
         */
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Bring the ingestorLift back to its default position
        ingestorLiftObj.stopLift();
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false; // DO NOT CHANGE THIS. This is a default command and should never end.
    }
}
