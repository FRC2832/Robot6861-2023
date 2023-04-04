// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IngestorIntake extends SubsystemBase {
    private static final Timer timer = new Timer();
    /**
     * Creates a new IngestorIntake.
     */

    private final TalonSRX ingestorIntakeTopTalon;
    private final TalonSRX ingestorIntakeBottomTalon;
    private final DigitalInput ingestorBeamBreak;

    public IngestorIntake(TalonSRX ingestorIntakeTopTalon) {
        this.ingestorIntakeTopTalon = ingestorIntakeTopTalon;
        ingestorIntakeTopTalon = new TalonSRX(Constants.INGESTOR_INTAKE_UPPER_TALON);
        ingestorIntakeBottomTalon = new TalonSRX(Constants.INGESTOR_INTAKE_LOWER_TALON);
        ingestorBeamBreak = new DigitalInput(Constants.DIGITAL_INPUT_BEAM);

    }

    public void revIn() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, Constants.INGESTOR_INTAKE_SPEED);
        //System.out.println("++++++++    revIn() called    ++++++++");
    }

    public void revOut(double speedTop, double speedBottom) {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, speedTop);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, speedBottom);
        //System.out.println("ooooooo    revOUt(2 speeds) called    ooooooo");
    }

    public CommandBase revOutIngestorIntake(double speed) {

        return run(
                () -> {

                    revOut(speed, speed);
                });

        //System.out.println("***********************  revOut: " + speed + " " + speed + "  ***********************");
    }


    public CommandBase revOutIngestorIntakeNew(double speedTop, double speedBottom) {

        return run(
                () -> {
                    revOut(speedTop, speedBottom);
                    //System.out.println("***********************  revOutingestorintake: " + speedTop + " " + speedBottom + "  ***********************");

                });
    }

    public CommandBase revInIngestorIntake() {
        return run(
                () -> {
                    revIn();
                    //System.out.println("***********************  revInIngestorintake: ");
                });
    }

    public boolean getIngestorBeamBreakValue() {
        //System.out.println("*** Beam sensor " + ingestorBeamBreak.get());
        // True is NO gamepieces, False is gamepieces
        return ingestorBeamBreak.get();
    }

    public boolean isCubeInIngestor() {
        //System.out.println("*** Cube in ingestor " + !ingestorBeamBreak.get());
        return !ingestorBeamBreak.get();
    }

    public CommandBase ingestorBeamBreakCmd() {  // not using beam sensor - it's values read 
        //by Rio are inconsistent, even though the red LED is stabe

        return run(
                () -> {
                    getIngestorBeamBreakValue();
                });
    }

    public void stop() {
        ingestorIntakeTopTalon.set(ControlMode.PercentOutput, 0.0);
        ingestorIntakeBottomTalon.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean isInScoop() {
        return false;

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}

