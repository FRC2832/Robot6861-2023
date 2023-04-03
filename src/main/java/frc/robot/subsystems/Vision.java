// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import java.util.ArrayList;

public class Vision extends SubsystemBase {
    private final double[] corners = {0.0, 0.0, 0.0, 0.0};
    private final Transform2d pose;
    // TODO: set cameraName (cameraName can be found in the PhotonVision UI)
    // TODO: find out if there's more functionality to add to the subsystem
    // TODO: maybe name these better?
    // TODO: MUST calibrate cameras per PhotonVision docs
    private PhotonCamera driverCamera;
    private PhotonCamera armCamera;
    private double yaw;
    private double pitch;
    private double area;
    private double skew;

    /**
     * Creates a new Vision.
     */
    public Vision() {
        driverCamera = new PhotonCamera(Constants.DRIVER_CAM_NAME);
        armCamera = new PhotonCamera(Constants.ARM_CAM_NAME);
        pose = new Transform2d();
        driverCamera.setDriverMode(true);
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
        return new ArrayList<>(0);
    }

    public void setArmCamera(PhotonCamera cam) {
        armCamera = cam;
    }

    public PhotonCamera getDriverCamera() {
        return driverCamera;
    }

    public void setDriverCamera(PhotonCamera cam) {
        driverCamera = cam;
    }

    // public PhotonCamera getArmCamera() {
    // return armCamera;
    // }

    public PhotonTrackedTarget bestTarget(PhotonCamera cam) {
        return cam.getLatestResult().getBestTarget();
    }

    public int[] getBestConeCenter() {
        int[] center = new int[2];
        PhotonTrackedTarget target = bestTarget(armCamera);
        for (TargetCorner corner : target.getMinAreaRectCorners()) {
            center[0] = (int) (center[0] + corner.x);
            center[1] = (int) (center[1] + corner.y);
        }
        center[0] /= 4;
        center[1] /= 4;
        return center;
    }

    public void targetData(PhotonCamera camera) {
        // Get information from target.
        var result = camera.getLatestResult();
        if (result.hasTargets()) {
            PhotonTrackedTarget target = result.getBestTarget();
            yaw = target.getYaw();
            pitch = target.getPitch();
            area = target.getArea();
            skew = target.getSkew();
        }
        // pose = target.getCameraToTarget();
        // corners = target.getCorners();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}