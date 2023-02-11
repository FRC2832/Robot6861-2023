










// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// test change

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

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
    private CANSparkMax leftFrontSpark;
    private CANSparkMax leftBackSpark;
    private CANSparkMax rightFrontSpark;
    private CANSparkMax rightBackSpark;
    //private MotorControllerGroup leftMotors;
    //private MotorControllerGroup rightMotors;
    //private DifferentialDrive differentialDriveObj; 
    private MecanumDrive mecanumDriveObj;
    
    //private CANSparkMax leftFrontMotorSparkMax;

    public Drivetrain() {
        // initialize stuff here
        // CANSparkMax leftFrontMotorSparkMax = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
        leftFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless); // TODO : correct motor type
        rightFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless);
        leftBackSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        rightBackSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        // TODO: zero the wheel encoders
        mecanumDriveObj = new MecanumDrive(leftFrontSpark, leftBackSpark, rightFrontSpark, rightBackSpark);
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
