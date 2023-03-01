// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.PhotonCamera;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Vision extends SubsystemBase {
    // TODO: set cameraName (cameraName can be found in the PhotonVision UI)
    // TODO: find out if there's more functionality to add to the subsystem
    // TODO: maybe name these better?
    // TODO: MUST calibrate cameras per PhotonVision docs
    private PhotonCamera camera;
    private PhotonCamera camera2;
    private double yaw;
    private double pitch;
    private double area; 
    private double skew;
    private double[] corners = {0,0,0,0};
    private Transform2d pose;

    /** Creates a new Vision. */
    public Vision() {
        camera = new PhotonCamera(Constants.CAMERANAME);
        // camera2 = new PhotonCamera(Constants.CAMERA2NAME);
        pose = new Transform2d();
        camera.setDriverMode(true);
    }

    public PhotonPipelineResult getLatestResult(PhotonCamera cam) {
        // gets data about currently detected targets
        // can get targets from the return value with result.getTargets() (make sure to
        // check if there are targets before calling this with result.hasTargets())
        return cam.getLatestResult();
    }

    public void setPipelineIndex(PhotonCamera cam, int idx) {
        // this sets pipeline index
        cam.setPipelineIndex(idx);
    }

    public ArrayList<PhotonTrackedTarget> getTargets(PhotonCamera cam) {
        PhotonPipelineResult res = getLatestResult(cam);
        if (res.hasTargets()) {
            return (ArrayList<PhotonTrackedTarget>) res.getTargets();
        }
        return new ArrayList<PhotonTrackedTarget>();
    }

    public void setCamera(PhotonCamera cam) {
        camera = cam;
    }

    public void setCamera2(PhotonCamera cam) {
        camera2 = cam;
    }

    public PhotonCamera getCamera() {
        return camera;
    }

    // public PhotonCamera getCamera2() {
    //     return camera2;
    // }

    public PhotonTrackedTarget bestTarget(PhotonCamera targets) {
        return targets.getLatestResult().getBestTarget();
    }

    public void targetData(PhotonCamera camera) {
        // Get information from target.
        var result = camera.getLatestResult();
        PhotonTrackedTarget target = result.getBestTarget();
        yaw = target.getYaw();
        pitch = target.getPitch();
        area = target.getArea();
        skew = target.getSkew();
        //pose = target.getCameraToTarget();
        //corners = target.getCorners();
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}