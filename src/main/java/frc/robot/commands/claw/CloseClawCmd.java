// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class CloseClawCmd extends CommandBase {
    private static final Timer TIMER = new Timer();
    /**
     * Creates a new CloseClawCmd.
     */
    private final ClawSubsystem clawSubsystemObj;

    public CloseClawCmd(ClawSubsystem clawSubsystemObj) {
        this.clawSubsystemObj = clawSubsystemObj;
        addRequirements(clawSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        TIMER.reset();
        TIMER.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //System.out.println("***********************  Claw timer: " + TIMER.get());
        if (TIMER.get() >= 10) { // TODO: Figure out seconds to wait when closng
            clawSubsystemObj.stopClaw();
        } else {
            clawSubsystemObj.closeClaw();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // clawSubsystemObj.stopClaw();  not sure if we want it to set speed to 0?
        TIMER.stop();
        TIMER.reset();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
