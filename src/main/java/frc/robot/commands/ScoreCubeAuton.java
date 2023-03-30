// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.eyes.EyeSubsystem;


public class ScoreCubeAuton extends CommandBase {
    /** Creates a new ScoreCubeCmd. */
    private IngestorIntake ingestorIntakeObj;
    private GamePieceScoop gamePieceScoopObj;
    private static Timer timer = new Timer(); // Static because we only need one timer. It's shared btwn all instances.

    public ScoreCubeAuton(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
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
        //gamePieceScoopObj.servoOnAuton();
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        EyeSubsystem.setDefaultColor(Constants.PURPLE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);

        ingestorIntakeObj.revOut(Constants.INGESTOR_EXPEL_SPEED_MID, Constants.INGESTOR_EXPEL_SPEED_MID);
        
        // changed to mid speed to help score cube in Auton 
        // cube was going too high and bouncing off the wall
        if (timer.get() >= 1.0) {   // allow time for rollers to get up to speed
           //System.out.println(" ------   Commanding Servos on   -------");
            gamePieceScoopObj.servoOnAuton();
        }
    }
  

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        timer.stop();
        //if (!ingestorIntakeObj.isCubeInIngestor()) { beam break causing servos to not move.  
            //I don't know why.  Moving on wihtout it.
           // EyeSubsystem.setDefaultColor(Constants.RED);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return //!ingestorIntakeObj.isCubeInIngestor() || 
        //this beam break call was causing command to end early - before shooting
         timer.get() >= 3.5;
        
        // keep timer in as OR so if servos fail to eject cube, 
        // robot still backs up and crosses community line for 3 pts
        // True means cube in ingestor
        
    }
}
