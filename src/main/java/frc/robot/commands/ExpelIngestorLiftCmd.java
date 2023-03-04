// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IngestorLift;

public class ExpelIngestorLiftCmd extends CommandBase {
    /** Creates a new MoveIngestorLiftCmd. */

    private IngestorLift ingestorLiftObj;
    private static Timer timer = new Timer();
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
        // Move the lift to the shooting position
        
        //System.out.println("Are we homed? " + ingestorLiftObj.getIsHomed());

        //RaiseIngestorLiftCmd execute code
        /*if (ingestorLiftObj.getIsHomed()) {
            if (ingestorLiftObj.getIsAtScoring()) {
                //System.out.println("We are at the scoring position!"); // TODO: The robot thinks we're here when we
                                                                       // release the button
                ingestorLiftObj.stopLift();
                System.out.println("Current encoder count: " + ingestorLiftObj.getEncoderCount());
                // Move the lift to the home position
            } else {
                ingestorLiftObj.lowerLiftToScore();
                System.out.println("We are moving to the scoring position!");
                // Move the lift to the shooting position
            }
            // Move the lift to the shooting position
        } else {
            System.out.println("We moving to the home position!");
            ingestorLiftObj.raiseLift();
            if (ingestorLiftObj.isAtTop()) {
                System.out.println("We are homed!");
                ingestorLiftObj.setIsHomed(true);
            }
            // Move the lift to the home position
        }*/

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
