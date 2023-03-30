// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.flipper;

import frc.robot.subsystems.ConeFlipper;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RaiseConeFlipper extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final ConeFlipper coneFlipperObj;

    /**
     * Creates a new ExampleCommand.
     *
     * @param flipper The subsystem used by this command.
     */
    public RaiseConeFlipper(ConeFlipper flipper) {
        coneFlipperObj = flipper;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(flipper);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!coneFlipperObj.getLimitSwitch()) {
            coneFlipperObj.setFlipperSpeed(0.25); // TODO: Need to verify that positive = upwards
        } else {
            coneFlipperObj.setFlipperSpeed(0.0);
            //coneFlipperObj.setFlipperIdleMode(IdleMode.kBrake);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        coneFlipperObj.setFlipperSpeed(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
