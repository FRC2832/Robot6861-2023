package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class JoystickSubsystem extends SubsystemBase {
    private CommandXboxController driverController;
    private CommandXboxController operatorController;
    private double driverLeftX;
    private double driverLeftY;
    private double driverRightX;
    private double driverRightY;
    private double driverRightTrigger;
    private double driverLeftTrigger;
    private double operatorLeftX;
    private double operatorLeftY;
    private double operatorRightX;
    private double operatorRightY;

    /** Creates a new JoystickSubsystem. */
    public JoystickSubsystem(CommandXboxController driverController, CommandXboxController operatorController) {
        this.driverController = driverController;
        this.operatorController = operatorController;
        setDeadband(1.0);
    }

    private void setDeadband(double d) {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driverLeftX = driverController.getLeftX();
        driverLeftY = driverController.getLeftY();
        driverRightX = driverController.getRightX();
        driverRightY = driverController.getRightY();
        driverRightTrigger = driverController.getRightTriggerAxis();
        driverLeftTrigger = driverController.getLeftTriggerAxis();
        operatorLeftX = operatorController.getLeftX();
        operatorLeftY = operatorController.getLeftY();
        operatorRightX = operatorController.getRightX();
        operatorRightY = operatorController.getRightY();
    }

    // TODO: add slew rate limiter for the driver joystick controls here?
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/filters/slew-rate-limiter.html

    public double getDriverLeftX() {
        return driverLeftX;
    }

    public double getDriverLeftY() {
        return driverLeftY;
    }

    public double getDriverRightX() {
        return driverRightX;
    }

    public double getDriverRightY() {
        return driverRightY;
    }

    public double getDriverRightTrigger() {
        return driverRightTrigger;
    }

    public double getDriverLeftTrigger() {
        return driverLeftTrigger;
    }

    public double getOperatorLeftX() {
        return operatorLeftX;
    }

    public double getOperatorLeftY() {
        return operatorLeftY;
    }

    public double getOperatorRightX() {
        return operatorRightX;
    }

    public double getOperatorRightY() {
        return operatorRightY;
    }
}
