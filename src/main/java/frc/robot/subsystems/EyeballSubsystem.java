// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//public class 

public class EyeballSubsystem extends SubsystemBase {
    private CANifier canifier;


    /** Creates a new EyeballSubsystem. */
    public EyeballSubsystem() {
        // Make the eyeball start as white
        setLEDs(255,255,255);
    }

    public void setLEDs(double r, double g, double b){
		//A: tbd
		//B: tbd
        //C: tbd
		canifier.setLEDOutput(r, LEDChannel.LEDChannelA);
		canifier.setLEDOutput(g, LEDChannel.LEDChannelB);
		canifier.setLEDOutput(b, LEDChannel.LEDChannelC);
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
}
