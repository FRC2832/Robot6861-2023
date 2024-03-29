// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class IntakeCubeCmd extends CommandBase {
    private static final Timer intakeTimer = new Timer();
    /**
     * Creates a new ScoreCubeCmd.
     */
    private final IngestorIntake ingestorIntakeObj;
    private final GamePieceScoop gamePieceScoopObj;
    private final EyeSubsystem eyeballobj;

    public IntakeCubeCmd(IngestorIntake ingestorIntake, GamePieceScoop gamePieceScoop, EyeSubsystem eyeballobj) {
        this.ingestorIntakeObj = ingestorIntake;
        this.gamePieceScoopObj = gamePieceScoop;
        this.eyeballobj = eyeballobj;
        addRequirements(ingestorIntake, gamePieceScoop);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // check that servo is out. If servo is in, then move it out.
        // elseIf servo is out, start wheels turning backwards

        EyeSubsystem.setDefaultColor(Constants.PURPLE);
        //EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
        //EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
        gamePieceScoopObj.servoOff();

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // System.out.println("-------- IntakeCubeCmd: execute --------------");
        ingestorIntakeObj.revIn();
        gamePieceScoopObj.servoOff();
        
        EyeSubsystem.setDefaultColor(Constants.PURPLE);
        eyeballobj.setEyesToDefault();
        //EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
        //EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        //EyeSubsystem.setDefaultColor(Constants.WHITE);
        //EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        //EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        // intakeTimer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return intakeTimer.get() > 1.5;
    }
}
