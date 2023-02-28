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
    private double driveDistanceInches;
    private double startEncoderPos;
    private boolean isRedAlliance;



    public DriveBackCmd(Drivetrain drivetrainObj, double driveDistanceInches, double driveSpeed) {
        this.drivetrainObj = drivetrainObj;
        this.driveDistanceInches = driveDistanceInches; 
        this.driveSpeed = Math.abs(driveSpeed);
        // this.isRedAlliance = isRedAlliance;
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrainObj.resetEncoders();
        startEncoderPos = Math.abs(drivetrainObj.getEncoderDistance());
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
        System.out.println("driveBack" + (Math.abs(drivetrainObj.getEncoderDistance() - startEncoderPos)) / 12);
        return (Math.abs(drivetrainObj.getEncoderDistance() - startEncoderPos)) / 12 >= driveDistanceInches;
    }
}
