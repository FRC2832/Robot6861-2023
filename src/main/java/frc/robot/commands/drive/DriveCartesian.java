// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.JoystickSubsystem;
/*import frc.robot.subsystems.eyes.EyeColor;
import frc.robot.subsystems.eyes.EyeMovement;
import frc.robot.subsystems.eyes.EyeSubsystem;
*/

public class DriveCartesian extends CommandBase {
    /**
     * Creates a new DriveArcade.
     */
    private final Drivetrain drivetrainObj;
    private final JoystickSubsystem joystickSubsystemObj;
    // private EyeSubsystem eyeballObj = new EyeSubsystem();

    public DriveCartesian(Drivetrain drivetrainObj, JoystickSubsystem joystickSubsystemObj) {
        this.drivetrainObj = drivetrainObj;
        this.joystickSubsystemObj = joystickSubsystemObj;
        addRequirements(drivetrainObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        zRotationTrue();


        // added snail mode along with existing turtle mode
        if (joystickSubsystemObj.getDriverRightTriggerValue() >= 0.5) {
            drivetrainObj.mecanumDriveCartesian(-joystickSubsystemObj.getDriverLeftX() * 0.5,
                    joystickSubsystemObj.getDriverLeftY() * 0.5, joystickSubsystemObj.getDriverRightX() * 0.5);
        } else if (joystickSubsystemObj.getDriverLeftTriggerValue() >= 0.5) {
            drivetrainObj.mecanumDriveCartesian(-joystickSubsystemObj.getDriverLeftX() * 0.25,
                    joystickSubsystemObj.getDriverLeftY() * 0.25, joystickSubsystemObj.getDriverRightX() * 0.25);
        } else {
            drivetrainObj.mecanumDriveCartesian(-joystickSubsystemObj.getDriverLeftX(),
                    joystickSubsystemObj.getDriverLeftY(), joystickSubsystemObj.getDriverRightX());
        }
    }

    public void zRotationTrue() {
        if (Math.abs(joystickSubsystemObj.getDriverRightX()) >= 0.1) {
            drivetrainObj.setRightBackMotorInversion(true);
            drivetrainObj.setLeftFrontMotorInversion(false);
        } else {
            drivetrainObj.setRightBackMotorInversion(false);
            drivetrainObj.setLeftFrontMotorInversion(true);
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
        return false;
    }
}
