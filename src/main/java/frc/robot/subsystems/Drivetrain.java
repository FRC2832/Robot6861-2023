
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// test change

package frc.robot.subsystems;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
    // TODO: Identify motor controller ports.  DOne?

    // Xbox controller port
    // Add operator controller
    // Make sure to ask electrical for this necessary information

    //Motor controllers:
    private CANSparkMax leftFrontSpark;
    private CANSparkMax leftBackSpark;
    private CANSparkMax rightFrontSpark;
    private CANSparkMax rightBackSpark;


    private MecanumDrive mecanumDriveObj;
    private MecanumDrivePoseEstimator poseEstimator;


    // Drive Motor Encoders:
    private RelativeEncoder leftFrontEncoderObj;
    private RelativeEncoder leftBackEncoderObj;
    private RelativeEncoder rightFrontEncoderObj;
    private RelativeEncoder rightBackEncoderObj;

    //private JoystickSubsystem joystickSubsystemObj;

    // Pigeon IMU also used for gyro:
    private WPI_PigeonIMU pigeon;

    //TODO: Add odomtery class for tracking robot pose

    //Creates a new Drivetrain
    public Drivetrain(WPI_PigeonIMU pigeon) {
        // initialize stuff here

        // Spark max
        leftFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless);
        rightFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless);
        leftBackSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        rightBackSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        
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

        // IIRC, you invert left/right, not front/back.

        leftFrontSpark.setIdleMode(IdleMode.kBrake);
        rightFrontSpark.setIdleMode(IdleMode.kBrake);
        leftBackSpark.setIdleMode(IdleMode.kBrake);
        rightBackSpark.setIdleMode(IdleMode.kBrake);
        // Pose/Orientation
        // poseEstimator = null;
        leftFrontSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        leftBackSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        rightFrontSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        rightBackSpark.setSmartCurrentLimit(Constants.DRIVETRAIN_MOTOR_CURRENT_LIMIT_AMPS);
        mecanumDriveObj.setMaxOutput(0.75);
        // mecanumDriveObj.setSafetyEnabled(false);
    }

    public void setRightBackMotorInversion(boolean isInverted) {
        rightBackSpark.setInverted(isInverted);
    }

    public void setLeftFrontMotorInversion(boolean isInverted) {
        leftFrontSpark.setInverted(isInverted);
    }

    public void mecanumDriveCartesian(double xSpeed, double ySpeed, double zRotation) {
        mecanumDriveObj.driveCartesian(xSpeed, ySpeed, zRotation);
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

    /*  TODO: CO-pilot suggested this method to be added.
     Do we want to keep it?  Might be useful for autonomous?
    public double getAvgEncoderDistance() {
        return getAvgEncoderRotations() * Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    }
    */
    


    /**
     * Returns the pose of the robot (e.g., x and y position of the robot on the
     * field and the robot's
     * rotation). The origin of the field to the lower left corner (i.e., the corner
     * of the field to
     * the driver's right). Zero degrees is away from the driver and increases in
     * the CCW direction.
     *
     * @return the pose of the robot
     */
    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();

    }
}
