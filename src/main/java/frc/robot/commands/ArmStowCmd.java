// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmStowCmd extends CommandBase {
    /** Creates a new ArmStowCmd. */
    private ArmSubsystem armSubsystemObj;

    public ArmStowCmd(ArmSubsystem armSubsystemObj) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.armSubsystemObj = armSubsystemObj;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        armSubsystemObj.setIsAtStow(false);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /*if (armSubsystemObj.getArmEncoder() == Constants.ARM_MOTOR_POSITION_STOW) {
            isAtStow = true;
        }*/
        armSubsystemObj.armStowPos();
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
