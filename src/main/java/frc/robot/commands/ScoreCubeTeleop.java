// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.eyes.EyeMovement;
import frc.robot.subsystems.eyes.EyeSubsystem;


public class ScoreCubeTeleop extends CommandBase {
    /** Creates a new ScoreCubeCmd. */
    private IngestorIntake ingestorIntakeObj;
    private GamePieceScoop gamePieceScoopObj;
    private static Timer timer = new Timer(); // Static because we only need one timer. It's shared btwn all instances.

    public ScoreCubeTeleop(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
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
        gamePieceScoopObj.servoOnTeleop();
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
       
        EyeSubsystem.setDefaultColor(Constants.PURPLE); // doesn't look purple
        //EyeSubsystem.getDefaultColor()
        EyeSubsystem.setDefaultMovementLeft(new EyeMovement(0.5, 0.5)); // Both eyes were EyeMovement 2
        EyeSubsystem.setDefaultMovementRight(new EyeMovement(0.5, 0.5));
        
        ingestorIntakeObj.revOut(Constants.INGESTOR_EXPEL_SPEED_MID, Constants.INGESTOR_EXPEL_SPEED_MID);

        // changed to mid speed to help score cube in Auton 
        // cube was going too high and bouncing off the wall
        if (timer.get() >= 1.5) { 
            gamePieceScoopObj.servoOnTeleop();
        }
    }
    // TODO: Create a new command for ingestorLift
    // TODO: If ingestorLift is at upper limit switch then zero the encoders

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        timer.stop();
        if (!ingestorIntakeObj.isCubeInIngestor()) {
            EyeSubsystem.setDefaultColor(Constants.WHITE);
        } 
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // TODO: Use beam break sensors to determine if we shot or not
        // use timer for now
        // keep timer in as OR so if servos fail to eject cube, 

        return timer.get() >= 2.5;
    }
}
