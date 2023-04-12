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
    private final Drivetrain drivetrainObj;
    /**
     * Creates a new BalancePIDCmd.
     */
    private double kp;
    private double angle;
    private double drivePower;
    private boolean isDriverControlled;
    //private int victoryCounter;

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
            kp = 0.017;   // competition charge station value = 0.022, frost was 0.026
        } else if (Math.abs(angle) < 5.5) {
            kp = 0.0033;   // was 0.0030 for Livonia.  competition charge station value = 0.0055, frost was 0.0055
        } else {
            kp = 0.012; // competition charge station value = 0.011 was 0.0099 for Livonia
            // (may need to lower this on churchill practice field), frost was 0.12
        }

        drivePower = kp * angle;
        if (Math.abs(drivePower) > 0.4) {
            drivePower = Math.copySign(0.4, drivePower);
        }

        if (Math.abs(drivePower) < 0.02) {
            drivePower = 0.0;
        }

        /*if (drivePower < 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        } else if (drivePower > 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_3);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
        }else {
            if (victoryCounter % 20 < 10) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
            } else {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_3);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
            }
            victoryCounter++;
            victoryCounter %= 20;
        }*/

        // drive forward at drivePower (the negative is becuase of inversions)
        drivetrainObj.mecanumDriveCartesian(0.0, -drivePower, 0.0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
        EyeSubsystem.setDefaultColor(Constants.WHITE);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // it will always be interrupted
        return false;
    }
}
