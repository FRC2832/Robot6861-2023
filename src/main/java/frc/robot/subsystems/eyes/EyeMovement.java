package frc.robot.subsystems.eyes;

public class EyeMovement {
    private int eyeLid;
    private int eyePupil;

    public int getEyeLid() {
        return eyeLid;
    }

    public void setEyeLid(int eyelid) {
        this.eyeLid = eyelid;
    }

    public int getEyePupil() {
        return eyePupil;
    }

    public void setEyePupil(int eyeball) {
        this.eyePupil = eyeball;
    }

    public EyeMovement(int lid, int pupil){
        this.eyeLid =lid;
        this.eyePupil = pupil;
    }

}
