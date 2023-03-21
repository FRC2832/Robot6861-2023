// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
public class ArmScoreCmd extends CommandBase {
    /** Creates a new ArmScoreCmd. */
    private ArmSubsystem armSubsystemObj;
    public boolean isAtScore;

    public ArmScoreCmd(ArmSubsystem armSubsystemObj) {
        this.armSubsystemObj = armSubsystemObj;
        addRequirements(armSubsystemObj);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        armSubsystemObj.ArmScorePos();
        if (armSubsystemObj.getArmEncoder() == Constants.ARM_MOTOR_POSITION_SCORE) {
            isAtScore = true;
        }
    }
    

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isAtScore;
    }
}
