// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class OpenClawCmd extends CommandBase {
    private ClawSubsystem clawSubsystemObj;
    private static final Timer TIMER = new Timer();

    public OpenClawCmd(ClawSubsystem clawSubsystemObj) {
        this.clawSubsystemObj = clawSubsystemObj;
        addRequirements(clawSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        clawSubsystemObj.stopClaw();
        TIMER.reset();
        TIMER.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println("***********************  Claw timer: " + TIMER.get());
        clawSubsystemObj.openClaw();

        /*if (TIMER.get() >= .75) { // TODO: Figure out time it takes to open
            clawSubsystemObj.stopClaw();
        } else {
            clawSubsystemObj.openClaw();
        }*/

        //TODO: add a timer reset somewhere
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
        return false; //TIMER.get() >= 3.25; // TODO: Figure out offset or same as stop time
    }
}
