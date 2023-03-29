// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.eyes;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class EyeSubsystem extends SubsystemBase {
    private Servo eyePupilServoRight;
    private Servo eyePupilServoLeft;
    private Servo eyeLidServoRight;
    private Servo eyeLidServoLeft;
    private CANifier clights;


    private static EyeMovement currentDefaultMovementLeft = Constants.EYE_MOVEMENT_1;
    private static EyeMovement currentDefaultMovementRight = Constants.EYE_MOVEMENT_1;
    private static EyeColor currentDefaultColor = Constants.LED_OFF;

    public EyeSubsystem() {
        eyePupilServoRight = new Servo(Constants.RIGHT_PUPIL_SERVO); // eyePupilServo1 connected to roboRIO PWM 4
        eyePupilServoLeft = new Servo(Constants.LEFT_PUPIL_SERVO); // eyePupilServo2 connected to roboRIO PWM 5
        eyeLidServoRight = new Servo(Constants.RIGHT_EYELID_SERVO); // eyeLidServo1 connected to roboRIO PWM 1
        eyeLidServoLeft = new Servo(Constants.LEFT_EYELID_SERVO); // eyeLidServo2 connected to roboRIO PWM 2
        clights = new CANifier(Constants.EYE_CANIFIER_ID);
    }

    private synchronized void setLEDColor(EyeColor color) {
        // ChannelA is Green
        // ChannelB is Red
        // ChannelC is Blue
        clights.setLEDOutput(color.getRed(), CANifier.LEDChannel.LEDChannelB); // Red
        clights.setLEDOutput(color.getGreen(), CANifier.LEDChannel.LEDChannelA); // Green
        clights.setLEDOutput(color.getBlue(), CANifier.LEDChannel.LEDChannelC); // Blue
        // mOutputsChanged = true;
    }

    private void setEyePositions(EyeMovement movementLeft, EyeMovement movementRight) {
        eyePupilServoRight.set(movementRight.getEyePupil());
        eyePupilServoLeft.set(movementLeft.getEyePupil());
        eyeLidServoRight.set(movementRight.getEyeLid());
        eyeLidServoLeft.set(movementLeft.getEyeLid());
    }

    public EyeMovement getLeftEyeMovement() {
        return new EyeMovement(eyeLidServoLeft.get(), eyePupilServoLeft.get());
    }

    public EyeMovement getRightEyeMovement() {
        return new EyeMovement(eyeLidServoRight.get(), eyePupilServoRight.get());
    }

    public double getLeftEyeLid() {
        return eyeLidServoLeft.get();
    }
    public double getLeftEyePupil() {
        return eyePupilServoLeft.get();
    }
    public double getRightEyeLid() {
        return eyeLidServoRight.get();
    }
    public double getRightEyePupil() {
        return eyePupilServoRight.get();
    }

    public static EyeColor getDefaultColor() {
        return currentDefaultColor;
    }
    
    public static void setDefaultColor(EyeColor defaultColor) {
        currentDefaultColor = defaultColor;
    }

    public static void setDefaultMovementLeft(EyeMovement defaultMovementLeft) {
        currentDefaultMovementLeft = defaultMovementLeft;
    }

    public static void setDefaultMovementRight(EyeMovement defaultMovementRight) {
        currentDefaultMovementRight = defaultMovementRight;
    }

    /**
     * Example command factory method.
     *
     * @return a command
     */

    public CommandBase setEyes(EyeMovement movementLeft, EyeMovement movementRight, EyeColor color) {
        return run(  // change to run?, runOnce is just a 20 ms loop
                () -> {
                    /* one-time action goes here */
                    setEyePositions(movementLeft, movementRight);
                    setLEDColor(color);
                    //setDefaultColor(color);
                });
    }

    public CommandBase setEyesToDefault() {
        return run(
            () -> {
                setLEDColor(currentDefaultColor);
                setEyePositions(currentDefaultMovementLeft, currentDefaultMovementRight);
                // TODO: Add default eye movement behavior
            });
    }

        /**
     * Example command factory method.
     *
     * @return a command
     */

    public CommandBase setColor(EyeColor color) {
        return run(  // change to run?, runOnce is just a 20 ms loop
                () -> {
                    /* one-time action goes here */
                    setLEDColor(color);
                });
    }

    public CommandBase setMovement(EyeMovement movementLeft, EyeMovement movementRight) {
        return run(  // change to run?, runOnce is just a 20 ms loop
                () -> {
                    /* one-time action goes here */
                    setEyePositions(movementLeft, movementRight);
                });
    }

   // public CommandBase setLeftEyeLidMovement(EyeMovement movementLeft) {
     //   return run(  // change to run?, runOnce is just a 20 ms loop
                //() -> {
                    /* one-time action goes here */
                    //setEyePositions(movementLeft, movementRight);
                 // });
    //}
    

   // public static void setLeftEyelid(double position) {
      //  eyeLidServoLeft.set(position);
    //}

    /**
     * An example method querying a boolean state of the subsystem (for example, a
     * digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    // TODO: this method is from ingestorlift subsystem, it probably should be deleted...
    public boolean isAtTop() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }

    @Override
    public void periodic() {
        // Colors
        // Ask for color of target (inside of ingestor)
        // Change LED color depending on this result
        // For teleop: White if nothing in ingestor, purple if cube in ingestor, yellow
        // if cone in ingestor, red or blue depending on alliance for last few seconds
        // Also, white during auton
        // Pupil
        // __assuming robot is moving forward
        // Move Pupil Forward
        // __assuming robot is driving back
        // Move Pupil Back
        // __assuming robot is balancing
        // repeat Move Pupil Forward and Move Pupil Back (to shake)
        // __assuming robot is balanced
        // Do nothing with pupil
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
