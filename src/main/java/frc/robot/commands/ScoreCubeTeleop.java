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

public class ScoreCubeTeleop extends CommandBase {
    private static final Timer timer = new Timer(); // Static because we only need one timer. It's shared btwn all
                                                    // instances.
    /**
     * Creates a new ScoreCubeCmd.
     */
    private final IngestorIntake ingestorIntakeObj;
    private final GamePieceScoop gamePieceScoopObj;

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
        EyeSubsystem.setDefaultColor(Constants.WHITE); // doesn't look purple
        // EyeSubsystem.getDefaultColor()
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5); // Both eyes were previously EyeMovement 2
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // EyeSubsystem.setDefaultColor(Constants.WHITE); // doesn't look purple
        // EyeSubsystem.getDefaultColor()
        // EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5); // Both eyes
        // were previously EyeMovement 2
        // EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);

        // ingestorIntakeObj.revOut(Constants.INGESTOR_EXPEL_SPEED_MID,
        // Constants.INGESTOR_EXPEL_SPEED_MID);

        ingestorIntakeObj.revOut(Constants.INGESTOR_EXPEL_SPEED_MID, Constants.INGESTOR_EXPEL_SPEED_MID);
        // 95/50 great for mid level score

        // ingestorIntakeObj.revOutIngestorIntakeNew(Constants.TOP_ROLLER_EXPEL_SPEED_HIGH,
        // Constants.LOWER_ROLLER_EXPEL_SPEED_HIGH);
        // ingestorIntakeObj.revOutIngestorIntakeNew(Constants.TOP_ROLLER_EXPEL_SPEED_AUTON,
        // Constants.LOWER_ROLLER_EXPEL_SPEED_AUTON);

        // changed to mid speed to help score cube in Auton
        // cube was going too high and bouncing off the wall

        if (timer.get() >= 1.5) {
            gamePieceScoopObj.servoOnTeleop();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        ingestorIntakeObj.stop();
        timer.stop();
        EyeSubsystem.setDefaultColor(Constants.WHITE); // doesn't look purple
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // use timer for now
        // keep timer in as OR so if servos fail to eject cube,

        return timer.get() >= 2.5;
    }
}
