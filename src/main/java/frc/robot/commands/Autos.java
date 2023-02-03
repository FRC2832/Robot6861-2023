// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IngestorLift;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
    /** Example static factory for an autonomous command. */
    public static CommandBase exampleAuto(IngestorLift ingestorLiftObj) {
        return Commands.sequence(ingestorLiftObj.raiseIngestorLift(), new StopIngestor(ingestorLiftObj));
    }

    // RED ALLIANCE
    // cableCross auton
    // __assuming servo is in down position at start up 
    // Score Cube
    // __Get the ingestorintake wheels turning backwards, warming up
    // __raise servo once wheels are up to TBD speed
    // Back up
    // __use encoders to determine when to stop (after robot has crossed community line)
    
    // cableDock
    // __servo is at down position
    // Score cube
    // __Rev up the ingestorintake motors backwards
    // __raise servo once wheels are up to TBD speed
    // Back up 
    // __Use encoder to determine when to stop
    // __Navigate it to the dock (Strafe Left)
    // Go up the dock (make this Balance Command?)
    // __Use pigeon to determine when it is balanced 

    //coopCross
    // __servo is at down position
    // Score cube
    // __Rev up the ingestorintake motors backwards
    // __raise servo once wheels are up to TBD speed
    // Back up 
    // __Use encoder to determine when to stop
    // __Navigate it to the dock (Strafe Left)
    // Go up the dock (make this Balance Command?)
    // __Use pigeon to determine when it is balanced 
    // Back up
    // __use encoders to determine when to stop (after robot has crossed community line)
    
    // coopPick
    // __servo is at down position
    // Score cube
    // __Rev up the ingestorintake motors backwards
    // __raise servo once wheels are up to TBD speed
    // Back up 
    // __Use encoder to determine when to stop
    // __Navigate it to the dock (Strafe Left)
    // Go up the dock (make this Balance Command?)
    // __Use pigeon to determine when it is balanced 
    // Back up
    // __Use pigeon to determine when to stop ()
    // 
    
    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
