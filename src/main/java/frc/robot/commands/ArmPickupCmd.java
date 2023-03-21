// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPickupCmd extends CommandBase {
    /** Creates a new ArmPickupCmd. */
    private ArmSubsystem armSubsystemObj;

    public ArmPickupCmd(ArmSubsystem armSubsystemObj) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.armSubsystemObj = armSubsystemObj;
        addRequirements(armSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        armSubsystemObj.armPickUpPos();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        armSubsystemObj.stopArm();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
