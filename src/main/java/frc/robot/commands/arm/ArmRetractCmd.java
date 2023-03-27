// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmRetractCmd extends CommandBase {
    private ArmSubsystem armSubsystemObj;

    public ArmRetractCmd(ArmSubsystem armSubsystemObj) {
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
        armSubsystemObj.armRetractPos();
        //armSubsystemObj.getArmEncoder();
        System.out.println("***********************  Arm Encoder: " + armSubsystemObj.getArmEncoder());
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
