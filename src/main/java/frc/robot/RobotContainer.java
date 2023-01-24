// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.StopIngestor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.JoystickSubsystem;
import frc.robot.commands.DriveArcade;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Drivetrain drivetrainObj = new Drivetrain();
    //private final ExampleSubsystem exampleSubsystemObj = new ExampleSubsystem();
    private final IngestorLift ingestorLiftObj = new IngestorLift();
    // public static XboxController driverController = new
    // XboxController(Constants.DRIVER_CONTROLLER);
    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController driverControllerObj = new CommandXboxController(
            Constants.DRIVER_CONTROLLER);
    private final CommandXboxController operatorControllerObj = new CommandXboxController(
            Constants.OPERATOR_CONTROLLER);
    private final JoystickSubsystem joystickSubsystemObj = new JoystickSubsystem(driverControllerObj,
            operatorControllerObj);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
        drivetrainObj.setDefaultCommand(new DriveArcade(drivetrainObj, joystickSubsystemObj));
        ingestorLiftObj.setDefaultCommand(new StopIngestor(ingestorLiftObj)); // TODO: Add Ingestor Intake
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        new Trigger(ingestorLiftObj::isAtTop)
                .onTrue(new StopIngestor(ingestorLiftObj));
        new Trigger(ingestorLiftObj::isAtBottom)
                .onTrue(new StopIngestor(ingestorLiftObj));
        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.
        // TODO: Change driverControllerObj to operatorControllerObj
        driverControllerObj.b().whileTrue(ingestorLiftObj.raiseIngestorLift());

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return Autos.exampleAuto(ingestorLiftObj);
    }
}
