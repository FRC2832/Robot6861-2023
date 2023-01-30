// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConeUprighter extends SubsystemBase {
    /** Creates a new ConeUprighter. */
    private CANSparkMax coneUprightMotor;

    /*
     * 
     */
    public ConeUprighter() {
        coneUprightMotor = new CANSparkMax(Constants.CONE_UPRIGHTER_CANSPARKMAX, Constants.UPRIGHT_MOTOR_TYPE);
        // TODO: Figure out motor type, code movement of motor, code buttons
    }

    public void operatorController() {

    }

    public void rotateFull() {

    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
