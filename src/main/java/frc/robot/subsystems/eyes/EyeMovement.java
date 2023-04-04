package frc.robot.subsystems.eyes;

public class EyeMovement {
    private double eyeLid;
    private double eyePupil;

    public EyeMovement(double lid, double pupil) {
        this.eyeLid = lid;
        this.eyePupil = pupil;
    }

    public double getEyeLid() {
        return eyeLid;
    }

    public void setEyeLid(double eyelid) {
        this.eyeLid = eyelid;
    }

    public double getEyePupil() {
        return eyePupil;
    }

    public void setEyePupil(double eyeball) {
        this.eyePupil = eyeball;
    }

}
