// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class StrafeLeftCmd extends CommandBase {
    /**
     * Creates a new StrafeLeftCmd.
     */

    private final Drivetrain drivetrainObj;
    private final double driveSpeed;
    private final double driveDistanceInches;
    private double startEncoderPos;
    private double start;

    public StrafeLeftCmd(Drivetrain drivetrainObj, double driveDistanceInches, double driveSpeed) {
        this.drivetrainObj = drivetrainObj;
        this.driveDistanceInches = driveDistanceInches;
        this.driveSpeed = Math.abs(driveSpeed);
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // System.out.println("StrafeLeftCmd initialize");
        drivetrainObj.resetEncoders();
        startEncoderPos = drivetrainObj.getEncoderDistance();
        // System.out.println("start encoder pos: " + startEncoderPos);
        start = startEncoderPos / Constants.DRIVETRAIN_STRAFE_RATIO / 12;
        // System.out.println("start: " + start);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainObj.mecanumDriveCartesian(driveSpeed, 0.0, 0.0);
        // Strafe Left drives right in Auton only. So inverting driveSpeed 
        // to positive as temporary fix.
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // System.out.println(((Math.abs(drivetrainObj.getEncoderDistance()) - Math.abs(startEncoderPos)) / Constants.DRIVETRAIN_STRAFE_RATIO) / 12);
        // System.out.println((((Math.abs(drivetrainObj.getEncoderDistance()) - Math.abs(startEncoderPos)) / Constants.DRIVETRAIN_STRAFE_RATIO) / 12) - start);
        return (((Math.abs(drivetrainObj.getEncoderDistance()) - Math.abs(startEncoderPos)) / Constants.DRIVETRAIN_STRAFE_RATIO) / 12) - start >= driveDistanceInches;
    }
}
