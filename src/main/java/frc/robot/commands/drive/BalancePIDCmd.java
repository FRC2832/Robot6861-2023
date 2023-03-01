// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class BalancePIDCmd extends CommandBase {
    /** Creates a new BalancePIDCmd. */
    private double kp = 0.01; // we need to tune this
    private double angle;
    private double drivePower;
    private Drivetrain drivetrainObj;

    public BalancePIDCmd(Drivetrain drivetrainObj) {
        this.drivetrainObj = drivetrainObj;
        addRequirements(drivetrainObj);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // TODO: get current angle from pigeon and put it in angle
        // pids work by multiplying the error from the desired position 
        // by the proportional factor, in this case kp.
        angle = drivetrainObj.getPitch();
        drivePower = kp * angle;
        // if we go above 0.4 power we’ll be too fast
        if (Math.abs(drivePower) > 0.4) {
            drivePower = Math.copySign(0.4, drivePower);
        }
        // drive forward at drivePower
        drivetrainObj.mecanumDriveCartesian(0, drivePower, 0);


    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(angle) < 1;
        // ends when the angle of the robot is within 1 of being level
    }
}
