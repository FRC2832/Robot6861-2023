// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class StrafeLeftCmd extends CommandBase {
    /** Creates a new StrafeLeftCmd. */

    private Drivetrain DrivetrainObj;
    private double driveSpeed;

    public StrafeLeftCmd(Drivetrain drivetrainObj, double driveSpeed) {
        this.DrivetrainObj = drivetrainObj;
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
        DrivetrainObj.mecanumDriveCartesian(-driveSpeed, 0, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        DrivetrainObj.mecanumDriveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
