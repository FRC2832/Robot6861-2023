// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.brake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BrakeSubsystem;
import frc.robot.subsystems.Drivetrain;

public class LowerBrakeCmd extends CommandBase {
    private BrakeSubsystem brakeSubsystemObj;
    private Drivetrain DrivetrainObj;
    // private static final Timer TIMER = new Timer();

    public LowerBrakeCmd(BrakeSubsystem brakeSubsystemObj, boolean isDriverControlled) {
        this.brakeSubsystemObj = brakeSubsystemObj;
        addRequirements(brakeSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // TIMER.restart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (brakeSubsystemObj.getBrakeEncoder() < (Constants.BRAKE_ON_FLOOR)) {
            brakeSubsystemObj.stopBrakes();
        } else {
            brakeSubsystemObj.lowerBrakes();
            brakeSubsystemObj.getBrakeEncoder();
            // System.out.println("*********************** Brake Encoder: " +
            // brakeSubsystemObj.getBrakeEncoder());
        }

        if (DrivetrainObj.getPitch() < -4) {
            brakeSubsystemObj.driveBrakeMotorBack();
        } else if (DrivetrainObj.getPitch() > -1.5 || DrivetrainObj.getPitch() < 1.5) {
            brakeSubsystemObj.stopDriveBrakeMotor();
        } else {
            brakeSubsystemObj.driveBrakeMotor();
        }
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // TIMER.stop();
        // TIMER.reset();
        brakeSubsystemObj.stopBrakes();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
