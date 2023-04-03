// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ingestor.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class ExpelIngestorLiftCmd extends CommandBase {
    private static final Timer timer = new Timer();
    /**
     * Creates a new MoveIngestorLiftCmd.
     */

    private final IngestorLift ingestorLiftObj;
    private boolean done;

    public ExpelIngestorLiftCmd(IngestorLift ingestorLift) {
        this.ingestorLiftObj = ingestorLift;
        addRequirements(ingestorLift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        //System.out.println("ScoreIngestorLiftCmd started.");
        //isHomed = ingestorLiftObj.isAtTop();
        // Zero the encoders
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        done = ingestorLiftObj.lowerLiftToExpel();

        EyeSubsystem.setDefaultColor(Constants.PURPLE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_1);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        // Move the lift to the shooting position


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
        return done || timer.get() >= 3;
    }
}
