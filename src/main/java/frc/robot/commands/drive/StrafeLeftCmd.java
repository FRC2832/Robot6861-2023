// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;

public class StrafeLeftCmd extends CommandBase {
    /** Creates a new StrafeLeftCmd. */

    private Drivetrain drivetrainObj;
    private double driveSpeed;
    private double driveDistanceInches;
    private double startEncoderPos;

    public StrafeLeftCmd(Drivetrain drivetrainObj, double driveDistanceInches, double driveSpeed) {
        this.drivetrainObj = drivetrainObj;
        this.driveDistanceInches = driveDistanceInches;
        this.driveSpeed = Math.abs(driveSpeed);
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrainObj.resetEncoders();
        startEncoderPos = drivetrainObj.getEncoderDistance();
        System.out.println("startEncoderPos StrafeLeft: " + startEncoderPos);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainObj.mecanumDriveCartesian(driveSpeed, 0, 0);
        // Strafe Left drives right in Auton only. So inverting driveSpeed 
        // to positive as temporary fix.
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        System.out.println("strafeLeft: " + ((Math.abs(drivetrainObj.getEncoderDistance()) - startEncoderPos) * Constants.DRIVETRAIN_STRAFE_RATIO) / 12);
        return ((Math.abs(drivetrainObj.getEncoderDistance()) - startEncoderPos) * Constants.DRIVETRAIN_STRAFE_RATIO) / 12 >= driveDistanceInches;
    }
}
