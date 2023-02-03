// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// test change

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    // TODO: Identify motor controller ports
    // Xbox controller port
    // Add operator controller
    // Make sure to ask electrical for this necessary information
    private Talon leftFrontTalon;
    private Talon leftBackTalon;
    private Talon rightFrontTalon;
    private Talon rightBackTalon;
    private MotorControllerGroup leftMotors;
    private MotorControllerGroup rightMotors;
    //private DifferentialDrive differentialDriveObj; 
    private MecanumDrive mecanumDriveObj;
    

    public Drivetrain() {
        // initialize stuff here
        leftFrontTalon = new Talon(Constants.DRIVETRAIN_LEFT_FRONT_TALON);
        rightFrontTalon = new Talon(Constants.DRIVETRAIN_RIGHT_FRONT_TALON);
        leftBackTalon = new Talon(Constants.DRIVETRAIN_LEFT_BACK_TALON);
        rightBackTalon = new Talon(Constants.DRIVETRAIN_RIGHT_BACK_TALON);
        leftMotors = new MotorControllerGroup(leftFrontTalon, leftBackTalon);
        rightMotors = new MotorControllerGroup(rightFrontTalon, rightBackTalon);
        //differentialDriveObj = new DifferentialDrive(leftMotors, rightMotors);
        mecanumDriveObj = new MecanumDrive(leftFrontTalon, leftBackTalon, rightFrontTalon, rightBackTalon);
    }

    /*public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        //differentialDriveObj.arcadeDrive(moveSpeed, rotateSpeed);
    }*/

    public void mecanumDriveCartesian(double xSpeed, double ySpeed, double zRotation) {
        mecanumDriveObj.driveCartesian(xSpeed, ySpeed, zRotation); 
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
