// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.brake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BrakeSubsystem;
import frc.robot.subsystems.Drivetrain;

public class DriveBrakeCmd extends CommandBase {
    /**
     * Creates a new DriveBrakeCmd.
     */

    private final BrakeSubsystem brakeSubsystemObj;
    private Drivetrain drivetrainObj;

    public DriveBrakeCmd(BrakeSubsystem brakeSubsystemObj, boolean isDriverControlled, Drivetrain drivetrainObj) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.brakeSubsystemObj = brakeSubsystemObj;
        addRequirements(brakeSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        if (drivetrainObj.getPitch() < -5) {       // if robot tipping forward, drive brake wheel back
            brakeSubsystemObj.driveBrakeMotorBack();
            //System.out.println("*******  drive brake motor backwards");

        } else if (drivetrainObj.getPitch() > 5) {  // if robot tipping backward, drive brake wheel forward
            brakeSubsystemObj.driveBrakeMotor();
            //System.out.println("*******  drive brake motor forward");

        } else {    //|| DrivetrainObj.getPitch() < 1.5)       Level on charging station, no rolling of wheel needed
            // Stops moving the brake wheels but doesn't raise it up
            brakeSubsystemObj.stopDriveBrakeMotor();
            //System.out.println("*******  drive brake motor stopped");

        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        brakeSubsystemObj.stopDriveBrakeMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
