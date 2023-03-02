package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// TODO: We could merge LED Subsystem and the Eyeball subsystem

public class LEDsCANifier extends SubsystemBase {

    private CANifier clights;

    public LEDsCANifier() {
        clights = new CANifier(4);
    }

    public synchronized void setLEDColor(double red, double green, double blue) {
        // ChannelA is Green
        // ChannelB is Red
        // ChannelC is Blue
        clights.setLEDOutput(red, CANifier.LEDChannel.LEDChannelB); // Red
        clights.setLEDOutput(green, CANifier.LEDChannel.LEDChannelA); // Green
        clights.setLEDOutput(blue, CANifier.LEDChannel.LEDChannelC); // Blue
        // mOutputsChanged = true;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}
