// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ScoreCubeCmd;
import frc.robot.commands.StopIngestor;
import frc.robot.commands.drive.DriveCartesian;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.JoystickSubsystem;
import frc.robot.subsystems.Vision;

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
    private final IngestorIntake ingestorIntakeObj = new IngestorIntake();
    private final Drivetrain drivetrainObj = new Drivetrain();
    private final ExampleSubsystem exampleSubsystemObj = new ExampleSubsystem();
    private final IngestorLift ingestorLiftObj = new IngestorLift();
    private final GamePieceScoop gamePieceScoopObj = new GamePieceScoop();
    private final Vision visionObj = new Vision();
    // TODO: we're missing a subsystem in the list above.  Who will be the first to put it in??


    // public static XboxController driverController = new
    // XboxController(Constants.DRIVER_CONTROLLER);
    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController driverControllerObj = new CommandXboxController(
            Constants.DRIVER_CONTROLLER);
    private final CommandXboxController operatorControllerObj = new CommandXboxController(
            Constants.OPERATOR_CONTROLLER);
    private final JoystickSubsystem joystickSubsystemObj = new JoystickSubsystem(driverControllerObj,
            operatorControllerObj);
    private final SendableChooser<CommandBase> autonChooser = new SendableChooser<>();
    private final SendableChooser<Integer> leftCenterRight = new SendableChooser<>();
    // TODO: may need differnt terms than left, center, right for auton chooser

    

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        
        // Configure the trigger bindings
        configureBindings();
        // drivetrainObj.setDefaultCommand(new DriveArcade(drivetrainObj,
        // joystickSubsystemObj));
        drivetrainObj.setDefaultCommand(new DriveCartesian(drivetrainObj, joystickSubsystemObj));
        ingestorLiftObj.setDefaultCommand(new StopIngestor(ingestorLiftObj)); // TODO: Add Ingestor Intake
        autonChooser.addOption("Example Auton Command", Autos.exampleAuto(ingestorLiftObj));
        autonChooser.addOption("Another Example Command", new ExampleCommand(exampleSubsystemObj));
        // ScoreCubeCmd cmd = new ScoreCubeCmd(ingestorIntakeObj, gamePieceScoopObj, ingestorLiftObj);
        // TODO: choose button on XbOX controller to run this command along with "whileActiveOnce?" or something similar and then test it
        


        // choose location of robot relative to grid for auton
        leftCenterRight.addOption("Left", 0);
        leftCenterRight.addOption("Center", 1);
        leftCenterRight.addOption("Right", 2);
        /*
         * String station = leftCenterRight.getSelected();
         * CommandBase defaultAutonCommand = null;
         * if (station == 0) {
         * // TODO: defaultAutonCommand = leftDriveBackward;
         * }
         * else if (station == 1) {
         * // TODO: defaultAutonCommand = centerDriveBackward;
         * }
         * else if (station == 2) {
         * // TODO: defaultAutonCommand = rightDriveBackward;
         * }
         * autonChooser.setDefaultOption("Drive Backward", defaultAutonCommand);
         */

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
        new Trigger(ingestorLiftObj::isAtTop).onTrue(new StopIngestor(ingestorLiftObj));
        new Trigger(ingestorLiftObj::isAtBottom).onTrue(new StopIngestor(ingestorLiftObj));
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
        CommandBase selectedCommand = autonChooser.getSelected();
        return selectedCommand;

    }
}
