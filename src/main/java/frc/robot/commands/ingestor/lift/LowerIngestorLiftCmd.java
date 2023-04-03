// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ingestor.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.eyes.EyeSubsystem;
import frc.robot.Constants;

public class LowerIngestorLiftCmd extends CommandBase {
    /** Creates a new MoveIngestorLiftCmd. */

    private IngestorLift ingestorLiftObj;
    private static Timer timer = new Timer();
    private boolean done;

    public LowerIngestorLiftCmd(IngestorLift ingestorLift) {
        this.ingestorLiftObj = ingestorLift;
        addRequirements(ingestorLift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        // Zero the encoders
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        EyeSubsystem.setDefaultColor(Constants.PURPLE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_2); // TODO: Change this. Eye closes.
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
        ingestorLiftObj.lowerLiftToIngest();

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Bring the ingestorLift back to its default position
        ingestorLiftObj.stopLift();
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        // System.out.println("LowerIngestorLiftCmd stopped.");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return done || timer.get() >= 3;
    }
}
