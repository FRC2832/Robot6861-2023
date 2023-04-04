// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnToCubeCmd extends CommandBase {
    /**
     * Creates a new TurnToCubeCmd.
     */

    private final Drivetrain drivetrainObj;
    private double driveSpeed = 0.2;
    private double TurnDistanceTarget = 50;
    private double startGyroPos;
    private double start;
    private double initialGyroPos;

    public TurnToCubeCmd(Drivetrain drivetrainObj, double turnDistanceTarget, double driveSpeed) {
        // Use addRequirements() here to declare subsystem dependencies.

        this.drivetrainObj = drivetrainObj;
        this.TurnDistanceTarget = turnDistanceTarget;
        this.driveSpeed = Math.abs(driveSpeed);
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        initialGyroPos = drivetrainObj.getYaw();
        // start = startEncoderPos;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        startGyroPos = drivetrainObj.getYaw();
        if (startGyroPos > startGyroPos - TurnDistanceTarget) {
            drivetrainObj.mecanumDriveCartesian(0, 0.0, driveSpeed);
        } else {
            drivetrainObj.mecanumDriveCartesian(0, 0.0, -driveSpeed);
        }


    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (startGyroPos >= ((startGyroPos - TurnDistanceTarget) + 5)); //starting gyro has been -134 So target is -184
    }
}
