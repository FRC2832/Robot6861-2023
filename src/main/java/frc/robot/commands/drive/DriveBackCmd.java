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
    private double driveDistanceFeet;
    private double startEncoderPos;



    public DriveBackCmd(Drivetrain drivetrainObj, double driveDistanceFeet, double driveSpeed) {
        this.drivetrainObj = drivetrainObj;
        this.driveDistanceFeet = driveDistanceFeet; 
        this.driveSpeed = Math.abs(driveSpeed);
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        startEncoderPos = drivetrainObj.getAvgEncoderDistance();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainObj.mecanumDriveCartesian(0,driveSpeed,0);
        // drive back drives forward in Auton only. So inverting driveSpeed 
        // to positive as temporary fix.  
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0,0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (drivetrainObj.getAvgEncoderDistance() - startEncoderPos) / 12 >= driveDistanceFeet;
    }
}
