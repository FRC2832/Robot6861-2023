// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.eyes;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EyeSubsystem extends SubsystemBase {
    private Servo eyePupilServo1;
    private Servo eyePupilServo2;
    private Servo eyeLidServo1;
    private Servo eyeLidServo2;
    private CANifier clights;

    
    public EyeSubsystem() {
        eyePupilServo1 = new Servo(4);         // eyePupilServo1 connected to roboRIO PWM 4
        eyePupilServo2 = new Servo(5);         // eyePupilServo2 connected to roboRIO PWM 5
        eyeLidServo1 = new Servo(3);         // eyeLidServo1 connected to roboRIO PWM 1
        eyeLidServo2 = new Servo(2);         // eyeLidServo2 connected to roboRIO PWM 2
        clights = new CANifier(4);
    }

    private synchronized void setLEDColor(EyeColor color) {
        //ChannelA is Green
        //ChannelB is Red
        //ChannelC is Blue
        clights.setLEDOutput(color.getRed(), CANifier.LEDChannel.LEDChannelB); //Red
        clights.setLEDOutput(color.getGreen(), CANifier.LEDChannel.LEDChannelA); //Green
        clights.setLEDOutput(color.getBlue(), CANifier.LEDChannel.LEDChannelC); //Blue
        //mOutputsChanged = true;
    }

    private void setEyePosition(EyeMovement movement){
        
		eyePupilServo1.set(movement.getEyePupil());
		eyePupilServo2.set(movement.getEyePupil());
		eyeLidServo1.set(movement.getEyeLid());
        eyeLidServo2.set(movement.getEyeLid());     
    }

    /**
     * Example command factory method.
     *
     * @return a command
     */

    public CommandBase setEyes(EyeMovement movement, EyeColor color) {
                return runOnce(
                () -> {
                    /* one-time action goes here */
                    setEyePosition(movement);
                    setLEDColor(color);
                });
    }

    /**
     * An example method querying a boolean state of the subsystem (for example, a
     * digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
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
