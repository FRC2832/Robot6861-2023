// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class ArmPickupCmd extends CommandBase {
    /**
     * Creates a new ArmPickupCmd.
     */
    private final ArmSubsystem armSubsystemObj;

    public ArmPickupCmd(ArmSubsystem armSubsystemObj) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.armSubsystemObj = armSubsystemObj;
        addRequirements(armSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        EyeSubsystem.setDefaultColor(Constants.YELLOW);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (armSubsystemObj.getArmEncoder() < Constants.ARM_MOTOR_POSITION_PICKUP) {
            // stow position = -10. Stops motor if motor keeps going beyond stow position.
            // Keeps winch motor from continuing to pull
            armSubsystemObj.armPickUpPos();
            // EyeSubsystem.setDefaultColor(Constants.YELLOW);
            // EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_5);
            // EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_5);
        } else {
            armSubsystemObj.stopArm();
            EyeSubsystem.setDefaultColor(Constants.WHITE);
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        }
        // System.out.println("*********************** Arm Encoder: " +
        // armSubsystemObj.getArmEncoder());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        armSubsystemObj.stopArm();
        EyeSubsystem.setDefaultColor(Constants.WHITE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
