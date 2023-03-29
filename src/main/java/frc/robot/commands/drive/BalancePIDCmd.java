// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class BalancePIDCmd extends CommandBase {
    /** Creates a new BalancePIDCmd. */
    private double kp;
    private double angle;
    private double drivePower;
    private Drivetrain drivetrainObj;
    private boolean isDriverControlled;

    public BalancePIDCmd(Drivetrain drivetrainObj, boolean isDriverControlled) {
        this.drivetrainObj = drivetrainObj;
        addRequirements(drivetrainObj);
        this.isDriverControlled = isDriverControlled;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() { 
        if (DriverStation.getAlliance() == Alliance.Blue) {
            EyeSubsystem.setDefaultColor(Constants.BLUE);
        } else {
            EyeSubsystem.setDefaultColor(Constants.RED);
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // pids work by multiplying the error from the desired position 
        // by the proportional factor, in this case kp.
        angle = drivetrainObj.getPitch();
        System.out.println("angle: " + angle);
        if (Math.abs(angle) < 7.0) {
            isDriverControlled = false;
        }
        
        if (isDriverControlled) {
            kp = 0.028; // competition charge station value = 0.022
        } else if (Math.abs(angle) < 5.0) {
            kp = 0.007; // competition charge station value = 0.0055
        } else {
            kp = 0.014; // competition charge station value = 0.011
        }

        drivePower = kp * angle;
        if (Math.abs(drivePower) > 0.4) {
            drivePower = Math.copySign(0.4, drivePower);
        }

        if (Math.abs(drivePower) < 0.02) {
            drivePower = 0.0;
        }

        if (drivePower < 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_1);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        } else if (drivePower > 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_2);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
        }


        // drive forward at drivePower (the negative is becuase of inversions)
        drivetrainObj.mecanumDriveCartesian(0.0, -drivePower, 0.0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
        EyeSubsystem.setDefaultColor(Constants.WHITE);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // it will always be interrupted
        return false;
    }
}
