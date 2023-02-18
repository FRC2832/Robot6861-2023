// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IngestorLift;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class StopIngestorLift extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final IngestorLift ingestorLiftObj;

    /**
     * Creates a new ExampleCommand.
     *
     * @param ingestorLiftObj The subsystem used by this command.
     */
    public StopIngestorLift(IngestorLift ingestorLiftObj) {
        this.ingestorLiftObj = ingestorLiftObj;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(ingestorLiftObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // TODO: Set ingestorLiftObj motors to 0
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
