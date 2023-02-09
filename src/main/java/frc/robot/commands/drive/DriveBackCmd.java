// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveBackCmd extends CommandBase {
    /** Creates a new DriveBackCmd. */

    private Drivetrain drivetrainObj;
    private double driveSpeed;


    public DriveBackCmd(Drivetrain drivetrainObj, double driveSpeed) {
        this.drivetrainObj = drivetrainObj;
        this.driveSpeed = Math.abs(driveSpeed);
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainObj.mecanumDriveCartesian(0,-driveSpeed,0); 
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0,0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        // TODO: code it to stop when it has traveled the correct distance using the encoders
        // TODO: multiply rotations by distance per rotation in order to see if we have traveled the correct distance
    }
}
