// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSubsystem extends SubsystemBase {

    private final CANSparkMax clawMotor;
    // private RelativeEncoder clawEncoder;
    // private static final Timer TIMER = new Timer();

    public ClawSubsystem() {
        clawMotor = new CANSparkMax(Constants.CLAW_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushed);
        clawMotor.setSmartCurrentLimit(Constants.CLAW_MOTOR_CURRENT_LIMIT_AMPS);
        // clawEncoder = clawMotor.getEncoder();
        // TIMER.reset();
    }

    public void closeClaw() {
        // TIMER.start();
        // if (TIMER.get() >= 3.0) {
        // stopClaw();
        // } else {
        clawMotor.set(Constants.CLAW_CLOSE_SPEED);
        clawMotor.setIdleMode(IdleMode.kBrake);
    }

    public void openClaw() {
        // TIMER.start();
        // if (TIMER.get() >= 3.0) {
        // stopClaw();
        // } else {
        clawMotor.set(Constants.CLAW_OPEN_SPEED);
    }

    public void stopClaw() {
        clawMotor.set(0.0);
        clawMotor.setIdleMode(IdleMode.kBrake);
    }

    // MUST BE CALLED IN COMMAND FOR IT TO WORK
    /*
     * public void resetClawTimer() {
     * TIMER.stop();
     * TIMER.reset();
     * }
     */

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
