// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.EyeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class OpenEye extends CommandBase {
    private final EyeSubsystem eyeSubsystemObj;
    private EyeMovement eyemovement;
    private EyeColor eyeColor;

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setEyemovement(EyeMovement eyemovement) {
        this.eyemovement = eyemovement;
    }

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public OpenEye(EyeSubsystem eyeSubsystem) {
        eyeSubsystemObj = eyeSubsystem;
        addRequirements(eyeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
       
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        eyeSubsystemObj.setEyes(eyemovement,eyeColor);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}