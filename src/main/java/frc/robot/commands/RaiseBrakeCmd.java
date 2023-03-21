// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BrakeSubsystem;

public class RaiseBrakeCmd extends CommandBase {
  /** Creates a new RaiseBrakeCmd. */
    private BrakeSubsystem brakeSubsystemObj;
  
    public RaiseBrakeCmd(BrakeSubsystem brakeSubsystemObj) {
        this.brakeSubsystemObj = brakeSubsystemObj;
        addRequirements(brakeSubsystemObj);
  }

  // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        brakeSubsystemObj.raiseBrakes();
        brakeSubsystemObj.getBrakeEncoder();
        //System.out.println("***********************  Brake Encoder: " + brakeSubsystemObj.getBrakeEncoder());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      brakeSubsystemObj.stopBrakes();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return brakeSubsystemObj.getBrakeEncoder() > (Constants.BRAKE_UP_POSITION);
  }
}
