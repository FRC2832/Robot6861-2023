
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// test change

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;

import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;

public class Drivetrain extends SubsystemBase {
    // TODO: Identify motor controller ports

    // Xbox controller port
    // Add operator controller
    // Make sure to ask electrical for this necessary information
    private CANSparkMax leftFrontSpark;
    private CANSparkMax leftBackSpark;
    private CANSparkMax rightFrontSpark;
    private CANSparkMax rightBackSpark;
    private MecanumDrive mecanumDriveObj;
    private MecanumDrivePoseEstimator poseEstimator;
    private SparkMaxAbsoluteEncoder leftFrontEncoderObj;
    private SparkMaxAbsoluteEncoder leftBackEncoderObj;
    private SparkMaxAbsoluteEncoder rightFrontEncoderObj;
    private SparkMaxAbsoluteEncoder rightBackEncoderObj;
    private WPI_PigeonIMU pigeon;
    


    // private CANSparkMax leftFrontMotorSparkMax;

    public Drivetrain(WPI_PigeonIMU pigeon) {
        // initialize stuff here
        // CANSparkMax leftFrontMotorSparkMax = new CANSparkMax(1,
        // CANSparkMax.MotorType.kBrushless);

        // Spark max
        leftFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless);
        rightFrontSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_FRONT_SPARK, CANSparkMax.MotorType.kBrushless);
        leftBackSpark = new CANSparkMax(Constants.DRIVETRAIN_LEFT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        rightBackSpark = new CANSparkMax(Constants.DRIVETRAIN_RIGHT_BACK_SPARK, CANSparkMax.MotorType.kBrushless);
        // TODO: zero the wheel encoders
        mecanumDriveObj = new MecanumDrive(leftFrontSpark, leftBackSpark, rightFrontSpark, rightBackSpark);

        // Encoder for the spark max
        leftFrontEncoderObj = leftFrontSpark.getAbsoluteEncoder(Constants.LEFT_FRONT_SPARK_ENCODER_TYPE);
        leftBackEncoderObj =  leftBackSpark.getAbsoluteEncoder(Constants.LEFT_BACK_SPARK_ENCODER_TYPE);
        rightFrontEncoderObj = rightFrontSpark.getAbsoluteEncoder(Constants.RIGHT_FRONT_SPARK_ENCODER);
        rightBackEncoderObj = rightBackSpark.getAbsoluteEncoder(Constants.RIGHT_BACK_SPARK_ENCODER);
        this.pigeon = pigeon;

        // Pose/Orientation
        poseEstimator = null;
    }


    public void mecanumDriveCartesian(double xSpeed, double ySpeed, double zRotation) {
        mecanumDriveObj.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    /** Creates a new DriveSubsystem.**/
  public void DriveSubsystem() {
    // TODO: Add encoder information if required

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    // rightFrontSpark.setInverted(true);
    // rightBackSpark.setInverted(true);
    // TODO: Figure out which spark max to invert
  }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

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
