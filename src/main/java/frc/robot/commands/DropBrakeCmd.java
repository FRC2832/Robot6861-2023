// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BrakeSubsystem;

public class DropBrakeCmd extends CommandBase {
    private BrakeSubsystem brakeSubsystemObj;
    private static final Timer TIMER = new Timer();

    public DropBrakeCmd(BrakeSubsystem brakeSubsystemObj) {
        this.brakeSubsystemObj = brakeSubsystemObj;
        addRequirements(brakeSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        TIMER.restart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (TIMER.get() >= 1.5 || brakeSubsystemObj.getBrakeEncoder() >= 10.0) {
            brakeSubsystemObj.stopBrakes();
        } else {
            brakeSubsystemObj.lowerBrakes();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        TIMER.stop();
        TIMER.reset();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return TIMER.get() >= 1.5;
    }
}
