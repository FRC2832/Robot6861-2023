// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.eyes.EyeSubsystem;

public class BalanceCrossCmd extends CommandBase {
  /** Creates a new BalanceCrossCmd. */

    private double kp;
    private double angle;
    private double drivePower;
    private Drivetrain drivetrainObj;
    private boolean isClimbingUp;
    private boolean isOnGround;


  public BalanceCrossCmd(Drivetrain drivetrainObj, boolean isClimbingUp, boolean isOnGround) {
        this.drivetrainObj = drivetrainObj;
        addRequirements(drivetrainObj);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
        angle = drivetrainObj.getPitch();
        isClimbingUp = true;
        isOnGround = false;
        System.out.println("angle: " + angle);
        

        if (isClimbingUp = true) {
            if (Math.abs(angle) > 5.0) {
                kp = 0.012;  //climbing up backwards
                System.out.println("                                                climbing up :) ");
            }
            } else {
                kp = 0.008; // on flat part of charge station
                isClimbingUp = false;
                System.out.println("                                                On top of the World :0 ");
            }

            if (isClimbingUp = false) {
                if (Math.abs(angle) < 14 && Math.abs(angle) > 3.0) {
                    kp = 0.004;  //going down backwards
                    System.out.println("                                                wheeeeeee - going downnnnnnnnn  ");
                }
                } else {
                    kp = 0.001; // on ground.  may not need any power, momentum may be enough
                    isOnGround = true;
                    System.out.println("                                                all done, ready to go back up!");
                }
        
        

        drivePower = kp * angle;
        System.out.println("                                               drivepower = " + drivePower);
        if (Math.abs(drivePower) > 0.4) {
            drivePower = Math.copySign(0.4, drivePower);
        }

        if (Math.abs(drivePower) < 0.02) {
            drivePower = 0.0;
        }

        if (drivePower < 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_4);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_1);
        } else if (drivePower > 0.0) {
            EyeSubsystem.setDefaultMovementLeft(Constants.EYE_MOVEMENT_3);
            EyeSubsystem.setDefaultMovementRight(Constants.EYE_MOVEMENT_2);
        }

         // drive forward at drivePower (the negative is becuase of inversions)
         drivetrainObj.mecanumDriveCartesian(0.0, -drivePower, 0.0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (isOnGround);
  }
}
