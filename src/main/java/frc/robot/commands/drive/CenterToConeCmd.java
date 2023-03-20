// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class CenterToConeCmd extends CommandBase {
    private Drivetrain drivetrainObj;

    public CenterToConeCmd(Drivetrain drivetrainObj, Vision vision) {
        this.drivetrainObj = drivetrainObj;
        // WPILib has a PIDController class that is helpful: 
        // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html#pid-control-in-wpilib
        // the setpoint would be half the camera x resolution and the tolerance about 5-10
        // it would take in the x value (first value) from the array reutrned from vision.getBestConeCenter()
        // a good starting kP value is probably 0.005 but that might even be too high and cause oscillation
        // we want kP to be as high as possible without oscillating
        // there's an example of the PIDController here: https://github.com/FRC2832/Robot2022-2832/blob/main/src/main/java/frc/robot/commands/CenterToCargo.java
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        drivetrainObj.mecanumDriveCartesian(0.0, 0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
