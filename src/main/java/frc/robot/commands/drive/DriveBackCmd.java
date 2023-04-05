// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class DriveBackCmd extends CommandBase {
    /**
     * Creates a new DriveBackCmd.
     */

    private final Drivetrain drivetrainObj;
    private final double driveSpeed;
    private final double driveDistanceInches;
    private double startEncoderPos;
    //private boolean isRedAlliance;

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
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_2); // pupils go to front of robot
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);

        // System.out.println(" ******* Starting encoder position " + startEncoderPos);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_2); // pupils go
        // to front of robot
        // EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);

        drivetrainObj.mecanumDriveCartesian(0.0, driveSpeed, 0.0);
        // System.out.println(" ********** Current drivetrain encoder position " +
        // drivetrainObj.getEncoderDistance());
        // drive back drives forward in Auton only. So inverting driveSpeed
        // to positive as temporary fix.
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
        EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
        EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // System.out.println(" ********** Current drivetrain calculated position " +
        // (Math.abs(drivetrainObj.getEncoderDistance() - startEncoderPos)) / 12);
        return ((Math.abs(drivetrainObj.getEncoderDistance() - startEncoderPos)) / 12 >= driveDistanceInches);
    }
}
