// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class StrafeLeftCmd extends CommandBase {
    /** Creates a new StrafeLeftCmd. */

    private Drivetrain drivetrainObj;
    private double driveSpeed;
    private double driveDist;

    public StrafeLeftCmd(Drivetrain drivetrainObj, double driveSpeed, double driveDist) {
        this.drivetrainObj = drivetrainObj;
        this.driveSpeed = Math.abs(driveSpeed);
        this.driveDist = driveDist;
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrainObj.mecanumDriveCartesian(-driveSpeed, 0, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        //Get the encoder value and use a condition to determine when to stop code
    }
}
