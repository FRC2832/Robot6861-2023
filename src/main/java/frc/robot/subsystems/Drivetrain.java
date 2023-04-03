// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// test change

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {

    // Xbox controller port
    // Add operator controller
    // Make sure to ask electrical for this necessary information

    //Motor controllers:
    private final CANSparkMax leftFrontSpark;
    private final CANSparkMax leftBackSpark;
    private final CANSparkMax rightFrontSpark;
    private final CANSparkMax rightBackSpark;


    private final MecanumDrive mecanumDriveObj;
    // Drive Motor Encoders:
    private final RelativeEncoder leftFrontEncoderObj;
    private final RelativeEncoder leftBackEncoderObj;
    private final RelativeEncoder rightFrontEncoderObj;
    private final RelativeEncoder rightBackEncoderObj;
    // Pigeon IMU also used for gyro:
    private final WPI_PigeonIMU pigeon;

    //private JoystickSubsystem joystickSubsystemObj;
    private MecanumDrivePoseEstimator poseEstimator;


    //TODO: Add odometery class for tracking robot pose

    //Creates a new Drivetrain
    public Drivetrain(WPI_PigeonIMU pigeon) {
        // initialize stuff here

        // Spark max
        leftFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT_SPARK, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftBackSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK_SPARK, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightBackSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK_SPARK, CANSparkMaxLowLevel.MotorType.kBrushless);
        mecanumDriveObj = new MecanumDrive(leftFrontSpark, leftBackSpark, rightFrontSpark, rightBackSpark);

        // Encoder for the spark max
        // TODO: zero the wheel encoders
        leftFrontEncoderObj = leftFrontSpark.getEncoder(); // TODO: Correct countsPerRev
        leftBackEncoderObj = leftBackSpark.getEncoder();
        rightFrontEncoderObj = rightFrontSpark.getEncoder();
        rightBackEncoderObj = rightBackSpark.getEncoder();
        this.pigeon = pigeon;

        leftFrontSpark.setInverted(true);
        rightFrontSpark.setInverted(true);


        leftFrontSpark.setIdleMode(IdleMode.kBrake);
        rightFrontSpark.setIdleMode(IdleMode.kBrake);
        leftBackSpark.setIdleMode(IdleMode.kBrake);
        rightBackSpark.setIdleMode(IdleMode.kBrake);

        // ramp rate for 0-full acceleration
        leftFrontSpark.setOpenLoopRampRate(Constants.DRIVETRAIN_MOTOR_RAMP_RATE);
        rightFrontSpark.setOpenLoopRampRate(Constants.DRIVETRAIN_MOTOR_RAMP_RATE);
        leftBackSpark.setOpenLoopRampRate(Constants.DRIVETRAIN_MOTOR_RAMP_RATE);
        rightBackSpark.setOpenLoopRampRate(Constants.DRIVETRAIN_MOTOR_RAMP_RATE);


        // Pose/Orientation
        // poseEstimator = null;


        // need to limit current for mecanum to keep battery from dropping so low we can't drive
        leftFrontSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        leftBackSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        rightFrontSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        rightBackSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);

        mecanumDriveObj.setMaxOutput(0.95);
        /*  maxOutput must be less than 1 to avoid overloading the battery and 
             not being able to drive. */

        // mecanumDriveObj.setSafetyEnabled(false);
    }

    public void setRightBackMotorInversion(boolean isInverted) {
        rightBackSpark.setInverted(isInverted);
    }

    public void setLeftFrontMotorInversion(boolean isInverted) {
        leftFrontSpark.setInverted(isInverted);
    }

    public void mecanumDriveCartesian(double xSpeed, double ySpeed, double zRotation) {
        // Creates a SlewRateLimiter that limits the rate of change of the signal 
        //SlewRateLimiter filterFwdBack = new SlewRateLimiter(Constants.FORWARD_BACK_SLEW_RATE);
        //SlewRateLimiter filterLeftRight = new SlewRateLimiter(Constants.LEFT_RIGHT_SLEW_RATE);
        mecanumDriveObj.driveCartesian(xSpeed, ySpeed, zRotation);
        //mecanumDriveObj.driveCartesian(filterLeftRight.calculate(xSpeed), filterFwdBack.calculate(ySpeed), zRotation);
    }

    public double getPitch() {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        return ypr[1];
    }


    public double getYaw() {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        return ypr[0];
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    // scales the max speed of the drivetrain. default = 1
    // use <1 to slow down the drivetrain all the time
    public void setMaxOutput(double maxOutput) {
        mecanumDriveObj.setMaxOutput(maxOutput);
    }

    public double getAvgEncoderRotations() {
        double rotationSum = leftFrontEncoderObj.getPosition() + leftBackEncoderObj.getPosition()
                + rightFrontEncoderObj.getPosition() + rightBackEncoderObj.getPosition();
        return rotationSum / 4;
    }

    // returns distance in inches
    public double getAvgEncoderDistance() {
        return getAvgEncoderRotations() * Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI * 1;
        //added 0.634 offset because robot is consistently off when commanding a distance
        //was 1.575 on 3/27, changed to 0.634 on 3/28, then changed to 1 on 3/28
    }

    public double getEncoderDistance() {
        System.out.println("rightFrontEncoder = " + rightFrontEncoderObj.getPosition());
        return rightFrontEncoderObj.getPosition() * Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI * 1;
    }

    public void resetEncoders() {
        leftFrontEncoderObj.setPosition(0);
        rightFrontEncoderObj.setPosition(0);
        leftBackEncoderObj.setPosition(0);
        rightBackEncoderObj.setPosition(0);
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }
}
