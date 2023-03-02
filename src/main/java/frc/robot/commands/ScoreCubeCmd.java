// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;


public class ScoreCubeCmd extends CommandBase {
    /** Creates a new ScoreCubeCmd. */
    private IngestorIntake ingestorIntakeObj;
    private GamePieceScoop gamePieceScoopObj;
    private static Timer timer = new Timer(); // Static because we only need one timer. It's shared btwn all instances.

    public ScoreCubeCmd(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop) {
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
        gamePieceScoopObj.servoOn();
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        ingestorIntakeObj.revOut();
        if (timer.get() >= 1.5) { // TODO: Figure out time it takes for motors to get up to speed
            gamePieceScoopObj.servoOff(); // should shoot cube
        }
    }
    // TODO: Create a new command for ingestorLift
    // TODO: If ingestorLift is at upper limit switch then zero the encoders

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        timer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // TODO: Use beam break sensors to determine if we shot or not
        // use timer for now
        // keep timer in as OR so if servos fail to eject cube, 
        // robot still backs up and crosses community line for 3 pts
        // TODO: Test if beam break is normally on or off.
        return timer.get() >= 5;
    }
}
