// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IngestorIntake;


public class StopIngestorIntake extends CommandBase {

    private final IngestorIntake ingestorIntakeObj;


    public StopIngestorIntake(IngestorIntake ingestorIntakeObj) {
        this.ingestorIntakeObj = ingestorIntakeObj;
        addRequirements(ingestorIntakeObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        ingestorIntakeObj.stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
