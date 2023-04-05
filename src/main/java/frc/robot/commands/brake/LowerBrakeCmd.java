// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.brake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BrakeSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class LowerBrakeCmd extends CommandBase {
    // private final EyeSubsystem eyeballobj;
    private final BrakeSubsystem brakeSubsystemObj;
    private final Drivetrain drivetrainObj;

    public LowerBrakeCmd(BrakeSubsystem brakeSubsystemObj, boolean isDriverControlled, Drivetrain drivetrainObj) {
        this.brakeSubsystemObj = brakeSubsystemObj;
        // this.eyeballobj = eyeballobj;
        this.drivetrainObj = drivetrainObj;
        addRequirements(brakeSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (DriverStation.getAlliance() == Alliance.Blue) {
            EyeSubsystem.setDefaultColor(Constants.BLUE);
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_2);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
        } else {
            EyeSubsystem.setDefaultColor(Constants.RED);
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_3);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_3);
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (brakeSubsystemObj.getBrakeEncoder() < (Constants.BRAKE_ON_FLOOR)) {
            brakeSubsystemObj.stopBrakes();
            // eyeballobj.setEyesToDefault();
            // System.out.println(" Eye setDefaultColor = " +
            // EyeSubsystem.getDefaultColor());

        } else { // Moves the brake drops down
            brakeSubsystemObj.lowerBrakes();
            brakeSubsystemObj.getBrakeEncoder();
            // eyeballobj.setEyesToDefault();
            // System.out.println(" Eye setDefaultColor = " +
            // EyeSubsystem.getDefaultColor());
            // System.out.println("*********************** Brake Encoder: " +
            // brakeSubsystemObj.getBrakeEncoder());
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stops the motors for the brakes
        brakeSubsystemObj.stopBrakes();
        EyeSubsystem.setDefaultColor(Constants.WHITE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // Never stops until interrupted (Keeps running until the driver lets go of
        // button)
        return false;
    }
}
