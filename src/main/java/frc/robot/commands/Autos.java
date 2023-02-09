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
        // default
        // Drive Back
        // __Use encoders o determine when to stop (once robot has crossed the community line)


        
        // cableCross
        // __assuming servo is in out position at start up 
        // Score Cube
        // __Get the ingestorintake wheels turning backwards, warming up
        // __raise servo once wheels are up to TBD speed
        // Drive Back
        // __use encoders to determine when to stop (after robot has crossed community line)
        
        // cableDock
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __raise servo once wheels are up to TBD speed
        // Drive Back 
        // __Use encoder to determine when to stop
        // Strafe Left
        // __Navigate it to the dock (Strafe Left)
        // Go up the dock (make this Balance Command?)
        // __Use pigeon to determine when it is balanced 

        //coopCross
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __raise servo once wheels are up to TBD speed
        // Drive Back
        // __Use encoder to determine when to stop
        // Strafe Left
        // __Navigate it to the dock (Strafe Left)
        // Go up the dock (make this Balance Command?)
        // __Use pigeon to determine when it is balanced 
        // Balance - needs to be changed
        // __use encoders to determine when to stop (after robot has crossed community line)
        
        // coopPick
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __raise servo once wheels are up to TBD speed
        // Drive Back 
        // __Use encoder to determine when to stop
        // Strafe Left
        // __Navigate it to the dock (Strafe Left)
        // Dock
        // __Use pigeon to determine if we have gotten onto the other side
        // Drive Back
        // __ Use encoder to determine when to stop
        // ??
        // Ingest Gampiece
        // __Rev up the ingestorintake motors forwards
        // __Ingestor lowers to the ingesting level
        // __Ingestor raises (servo at out position)


        // coopEngage
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __servo in once wheels are up to TBD speed
        // Drive Back 
        // __Use encoder to determine when to stop (goes over the charging station)
        // Drive Forward
        // __Use encoder to determine when to stop
        // Balance
        // __Use pigeon to determine when to stop

        // substationDock
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __raise servo once wheels are up to TBD speed
        // Drive back
        // __Use encoder to determine when to stop
        // Strafe Left
        // __Navigate it to the dock (Strafe Left)
        // Go up the dock (make this Balance Command?)
        // __Use pigeon to determine when it is balanced 
        // Balance
        // __Use pigeon to determine when to stop ()

        // substationTelePrep
        // __servo is at out position
        // Score cube
        // __Rev up the ingestorintake motors backwards
        // __raise servo once wheels are up to TBD speed
        // Drive back
        // __Use encoder to determine when to stop
        // 

    
    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
