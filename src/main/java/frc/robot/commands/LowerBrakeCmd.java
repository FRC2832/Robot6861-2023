// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BrakeSubsystem;
import frc.robot.subsystems.Drivetrain;

public class LowerBrakeCmd extends CommandBase {
    private BrakeSubsystem brakeSubsystemObj;
    private Drivetrain drivetrainObj;
    //private static final Timer TIMER = new Timer();

    public LowerBrakeCmd(BrakeSubsystem brakeSubsystemObj, boolean isDriverControlled, Drivetrain drivetrainObj) {
        this.brakeSubsystemObj = brakeSubsystemObj;
        this.drivetrainObj = drivetrainObj;
        addRequirements(brakeSubsystemObj);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //TIMER.restart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (brakeSubsystemObj.getBrakeEncoder() < (Constants.BRAKE_ON_FLOOR)) {
            brakeSubsystemObj.stopBrakes();
        } else {
            
            brakeSubsystemObj.lowerBrakes();
            brakeSubsystemObj.getBrakeEncoder();

           // System.out.println("***********************  Brake Encoder: " + brakeSubsystemObj.getBrakeEncoder());
        }
        
       

        if (drivetrainObj.getPitch() < -4) {
            brakeSubsystemObj.driveBrakeMotorBack();
            // reverse motor for if charge station tilted by alliance partner

        } else if (drivetrainObj.getPitch() > -1.5 || drivetrainObj.getPitch() < 1.5) {
            brakeSubsystemObj.stopDriveBrakeMotor();
            // don't drive brake wheel when mostly balanced

        } else { 
                brakeSubsystemObj.driveBrakeMotor();
            } 
        }
    

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //TIMER.stop();
        //TIMER.reset();
        brakeSubsystemObj.stopBrakes();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
