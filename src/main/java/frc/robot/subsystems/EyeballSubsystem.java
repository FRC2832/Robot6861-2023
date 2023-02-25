// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class EyeballSubsystem extends SubsystemBase {
    private Servo eyePupilServo1;
    private Servo eyePupilServo2;
    private Servo eyeLidServo1;
    private Servo eyeLidServo2;

    // Creates a new EyeballSubsystem. 
    public EyeballSubsystem() {
        eyePupilServo1 = new Servo(4);         // eyePupilServo1 connected to roboRIO PWM 4
        eyePupilServo2 = new Servo(5);         // eyePupilServo2 connected to roboRIO PWM 5
        eyeLidServo1 = new Servo(1);         // eyeLidServo1 connected to roboRIO PWM 1
        eyeLidServo2 = new Servo(2);         // eyeLidServo2 connected to roboRIO PWM 2
        
    }



    public void setServo(double r, double g, double b){
		//A: tbd
		//B: tbd
        //C: tbd
		eyePupilServo1.set(1.0);
		eyePupilServo2.set(1.0);
		eyeLidServo1.set(0.);
        eyeLidServo2.set(1);
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
