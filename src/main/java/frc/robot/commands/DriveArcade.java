// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.JoystickSubsystem;

// TODO: Change to DriveCartesian. Maybe add DrivePolar as well.
public class DriveArcade extends CommandBase {
    /** Creates a new DriveArcade. */
    private Drivetrain drivetrainObj;
    private JoystickSubsystem joystickSubsystemObj;

    public DriveArcade(Drivetrain drivetrainObj, JoystickSubsystem joystickSubsystemObj) {
        this.drivetrainObj = drivetrainObj;
        this.joystickSubsystemObj = joystickSubsystemObj;
        addRequirements(drivetrainObj); // TODO: May need to add joystickSubsystemObj to requirements.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // double moveSpeed =
        // -RobotContainer.driverControllerObj.getRawAxis(Constants.DRIVER_CONTROLLER_MOVE_AXIS);
        // TODO: Do this without referencing the controllers from here.
        // double rotateSpeed =
        // RobotContainer.driverControllerObj.getRawAxis(Constants.DRIVER_CONTROLLER_ROTATE_AXIS);
        drivetrainObj.arcadeDrive(joystickSubsystemObj.getDriverLeftY(), joystickSubsystemObj.getDriverRightX());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainObj.arcadeDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
