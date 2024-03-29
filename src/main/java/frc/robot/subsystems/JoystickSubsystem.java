package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

public class JoystickSubsystem extends SubsystemBase {
    private final CommandXboxController driverController;
    private final CommandXboxController operatorController;
    private double driverLeftX;
    private double driverLeftY;
    private double driverRightX;
    private double driverRightY;
    private double driverLeftTrigger;
    private double driverRightTrigger;
    private double operatorLeftX;
    private double operatorLeftY;
    private double operatorRightX;
    private double operatorRightY;

    /**
     * Creates a new JoystickSubsystem.
     */
    public JoystickSubsystem() {
        driverController = new CommandXboxController(Constants.DRIVER_CONTROLLER);
        setDeadband(1.0);
        operatorController = new CommandXboxController(Constants.OPERATOR_CONTROLLER);

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
        driverLeftTrigger = driverController.getLeftTriggerAxis();
        driverRightTrigger = driverController.getRightTriggerAxis();
        operatorLeftX = operatorController.getLeftX();
        operatorLeftY = operatorController.getLeftY();
        operatorRightX = operatorController.getRightX();
        operatorRightY = operatorController.getRightY();

    }

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

    public double getDriverLeftTriggerValue() {
        return driverLeftTrigger;
    }

    public double getDriverRightTriggerValue() {
        return driverRightTrigger;
    }

    public Trigger
    getDriverRightTrigger() {
        return driverController.rightTrigger();
    }

    public Trigger getDriverLeftTrigger() {
        return driverController.leftTrigger();
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

    public Trigger getOperatorABtn() {
        return operatorController.a();
    }

    public Trigger getOperatorBBtn() {
        return operatorController.b();
    }

    public Trigger getOperatorXBtn() {
        return operatorController.x();
    }

    public Trigger getOperatorYBtn() {
        return operatorController.y();
    }

    public Trigger getOperatorLeftTrigger() {
        return operatorController.leftTrigger();
    }

    public Trigger getOperatorRightTrigger() {
        return operatorController.rightTrigger();
    }

    public Trigger getOperatorRightBumper() {
        return operatorController.rightBumper();
    }

    public Trigger getDriverRightBumper() {
        return driverController.rightBumper();
    }

    public Trigger getDriverLeftBumper() {
        return driverController.leftBumper();
    }

    public Trigger getDriverABtn() {
        return driverController.a();
    }

    public Trigger getDriverBBtn() {
        return driverController.b();
    }

    public Trigger getDriverXBtn() {
        return driverController.x();

    }
}
