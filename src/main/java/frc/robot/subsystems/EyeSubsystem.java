// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EyeSubsystem extends SubsystemBase {
    private Servo eyePupilServo1;
    private Servo eyePupilServo2;
    private Servo eyeLidServo1;
    private Servo eyeLidServo2;

    
    public EyeSubsystem() {
        eyePupilServo1 = new Servo(4);         // eyePupilServo1 connected to roboRIO PWM 4
        eyePupilServo2 = new Servo(5);         // eyePupilServo2 connected to roboRIO PWM 5
        eyeLidServo1 = new Servo(1);         // eyeLidServo1 connected to roboRIO PWM 1
        eyeLidServo2 = new Servo(2);         // eyeLidServo2 connected to roboRIO PWM 2
    }

    public void eyePosition(double eyePupil, double eyeLid){
		eyePupilServo1.set(eyePupil);
		eyePupilServo2.set(eyePupil);
		eyeLidServo1.set(eyeLid);
        eyeLidServo2.set(eyeLid);     
    }

    /**
     * Example command factory method.
     *
     * @return a command
     */
    public CommandBase resetEyes() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    /* one-time action goes here */
                    eyePosition(0, 0);
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
