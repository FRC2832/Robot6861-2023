// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ExpelIngestorLiftCmd;
import frc.robot.commands.IntakeCubeCmd;
import frc.robot.commands.LowerIngestorLiftCmd;
import frc.robot.commands.RaiseIngestorLiftCmd;
import frc.robot.commands.ScoreCubeCmd;
import frc.robot.commands.ScoreIngestorLiftCmd;
import frc.robot.commands.StopIngestorIntake;
import frc.robot.commands.autons.BlueCableCrossAuton;
import frc.robot.commands.autons.BlueDefaultCableAuton;
import frc.robot.commands.autons.BlueDefaultSubstationAuton;
import frc.robot.commands.autons.BlueSubstationCrossAuton;
import frc.robot.commands.autons.CoopBalanceAuton;
import frc.robot.commands.autons.RedCableCrossAuton;
import frc.robot.commands.autons.RedDefaultCableAuton;
import frc.robot.commands.autons.RedDefaultSubstationAuton;
import frc.robot.commands.autons.RedSubstationCrossAuton;
import frc.robot.commands.drive.BalancePIDCmd;
import frc.robot.commands.drive.DriveCartesian;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GamePieceScoop;
import frc.robot.subsystems.IngestorIntake;
import frc.robot.subsystems.IngestorLift;
import frc.robot.subsystems.JoystickSubsystem;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.eyes.EyeColor;
import frc.robot.subsystems.eyes.EyeMovement;
import frc.robot.subsystems.eyes.EyeSubsystem;

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
    private final TalonSRX ingestorIntakeUpperTalon = new TalonSRX(Constants.INGESTOR_INTAKE_UPPER_TALON);
    private final IngestorIntake ingestorIntakeObj = new IngestorIntake(ingestorIntakeUpperTalon);
    private final WPI_PigeonIMU pigeon = new WPI_PigeonIMU(ingestorIntakeUpperTalon);
    private final Drivetrain drivetrainObj = new Drivetrain(pigeon);
    private final IngestorLift ingestorLiftObj = new IngestorLift();
    private final GamePieceScoop gamePieceScoopObj = new GamePieceScoop();
    private final Vision visionObj = new Vision();
    // private final ConeFlipper coneFlipperObj = new ConeFlipper();
    private final EyeSubsystem eyeballObj = new EyeSubsystem();

    // TODO: We could merge LED Subsystem and the Eyeball subsystem
    // private final EyeballSubsystem eyeballObj = new EyeballSubsystem();

    private final Map<String, Command> autoEventMap = new HashMap<>();

    // public static XboxController driverController = new
    // XboxController(Constants.DRIVER_CONTROLLER);
    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController driverControllerObj = new CommandXboxController(
            Constants.DRIVER_CONTROLLER);
    private final CommandXboxController operatorControllerObj = new CommandXboxController(
            Constants.OPERATOR_CONTROLLER);
    private final JoystickSubsystem joystickSubsystemObj = new JoystickSubsystem(driverControllerObj,
            operatorControllerObj);

    private final Command redDefaultSubstationAutoCmd = new RedDefaultSubstationAuton(drivetrainObj);
    private final Command redSubstationCrossAutoCmd = new RedSubstationCrossAuton(drivetrainObj, ingestorIntakeObj,
            gamePieceScoopObj);
    private final Command redDefaultCableAutoCmd = new RedDefaultCableAuton(drivetrainObj);
    private final Command redCableCrossAutoCmd = new RedCableCrossAuton(drivetrainObj, ingestorIntakeObj,
            gamePieceScoopObj);
    private final Command blueDefaultSubstationAutoCmd = new BlueDefaultSubstationAuton(drivetrainObj);
    private final Command blueSubstationCrossAutoCmd = new BlueSubstationCrossAuton(drivetrainObj, ingestorIntakeObj,
            gamePieceScoopObj);
    private final Command blueDefaultCableAutoCmd = new BlueDefaultCableAuton(drivetrainObj);
    private final Command blueCableCrossAutoCmd = new BlueCableCrossAuton(drivetrainObj, ingestorIntakeObj,
            gamePieceScoopObj);
    private final Command coopBalanceAutoCmd = new CoopBalanceAuton(ingestorIntakeObj, gamePieceScoopObj,
            drivetrainObj);

    private final SendableChooser<Command> autonChooser = new SendableChooser<>();
    private final SendableChooser<Integer> leftCenterRight = new SendableChooser<>();
    // TODO: may need differnt terms than left, center, right for auton chooser

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        // Configure the trigger bindings
        configureBindings();
        SequentialCommandGroup defaultIngestorLiftSequence = new SequentialCommandGroup(
                new RaiseIngestorLiftCmd(ingestorLiftObj), new ScoreIngestorLiftCmd(ingestorLiftObj));

        drivetrainObj.setDefaultCommand(new DriveCartesian(drivetrainObj, joystickSubsystemObj));
        ingestorLiftObj.setDefaultCommand(defaultIngestorLiftSequence); // TODO: Add Ingestor Intake
        ingestorIntakeObj.setDefaultCommand(new StopIngestorIntake(ingestorIntakeObj));
        gamePieceScoopObj.setDefaultCommand(gamePieceScoopObj.servoOnCmd());
        eyeballObj.setDefaultCommand(
                eyeballObj.setEyes(new EyeMovement(1, 1), new EyeMovement(1, 0), new EyeColor(255, 0, 0)));
        // 2nd eyemovement above is robot right
        // coneFlipperObj.setDefaultCommand(new RaiseConeFlipper(coneFlipperObj));

        EyeMovement movementLeft = new EyeMovement(1, 0);
        EyeMovement movementRight = new EyeMovement(1, 1);
        EyeColor color = new EyeColor(0, 0, 0);
        ParallelCommandGroup defaultSubstationAuton = new ParallelCommandGroup(
                eyeballObj.setEyes(movementLeft, movementRight, color),
                redDefaultSubstationAutoCmd);

        System.out.println("The current alliance is:" + DriverStation.getAlliance());
        autonChooser.setDefaultOption("Coop Grid Balance Auton (both alliances)", coopBalanceAutoCmd);
        if (DriverStation.getAlliance().equals(Alliance.Red)) {
            autonChooser.addOption("RED Default Substation Auton", defaultSubstationAuton);
            autonChooser.addOption("RED Substation Cross Auton", redSubstationCrossAutoCmd);
            autonChooser.addOption("RED Default Cable Auton", redDefaultCableAutoCmd);
            autonChooser.addOption("RED Cable Cross Auton", redCableCrossAutoCmd);
        } else if (DriverStation.getAlliance().equals(Alliance.Blue)) {
            autonChooser.addOption("BLUE Default Substation Auton", blueDefaultSubstationAutoCmd);
            autonChooser.addOption("BLUE Substation Cross Auton", blueSubstationCrossAutoCmd);
            autonChooser.addOption("BLUE Default Cable Auton", blueDefaultCableAutoCmd);
            autonChooser.addOption("BLUE Cable Cross Auton", blueCableCrossAutoCmd);
        } else {
            autonChooser.addOption("RED Default Substation Auton", defaultSubstationAuton);
            autonChooser.addOption("RED Substation Cross Auton", redSubstationCrossAutoCmd);
            autonChooser.addOption("RED Default Cable Auton", redDefaultCableAutoCmd);
            autonChooser.addOption("RED Cable Cross Auton", redCableCrossAutoCmd);
            autonChooser.addOption("BLUE Default Substation Auton", blueDefaultSubstationAutoCmd);
            autonChooser.addOption("BLUE Substation Cross Auton", blueSubstationCrossAutoCmd);
            autonChooser.addOption("BLUE Default Cable Auton", blueDefaultCableAutoCmd);
            autonChooser.addOption("BLUE Cable Cross Auton", blueCableCrossAutoCmd);
        }

        SmartDashboard.putData("Auton Chooser", autonChooser);
        // autonChooser.addOption("Example Auton Command",
        // Autos.exampleAuto(ingestorLiftObj));
        // autonChooser.addOption("Another Example Command", new
        // ExampleCommand(exampleSubsystemObj));
        // ScoreCubeCmd cmd = new ScoreCubeCmd(ingestorIntakeObj, gamePieceScoopObj,
        // ingestorLiftObj);
        // TODO: choose button on XbOX controller to run this command along with
        // "whileActiveOnce?" or something similar and then test it

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
        // new Trigger(ingestorLiftObj::isAtTop).onTrue(new
        // StopIngestorLift(ingestorLiftObj));
        // new Trigger(ingestorLiftObj::isAtBottom).onTrue(new
        // StopIngestorLift(ingestorLiftObj));
        // Schedule `exampleMethodCommand` when the Xbox controller's B button is
        // pressed,
        // cancelling on release.

        // triggers and commands for shooting cube to high spot and middle spot
        ParallelCommandGroup shootCubeUpper = new ParallelCommandGroup(
                ingestorIntakeObj.revOutIngestorIntake(Constants.INGESTOR_EXPEL_SPEED_HIGH),
                gamePieceScoopObj.servoOffCmd());

        ParallelCommandGroup shootCubeMid = new ParallelCommandGroup(
                ingestorIntakeObj.revOutIngestorIntake(Constants.INGESTOR_EXPEL_SPEED_MID),
                gamePieceScoopObj.servoOffCmd());

        ParallelCommandGroup shootCubeLower = new ParallelCommandGroup(
                ingestorIntakeObj.revOutIngestorIntake(Constants.INGESTOR_EXPEL_SPEED_LOW),
                gamePieceScoopObj.servoOffCmd());

        ParallelCommandGroup lowerAndIngest = new ParallelCommandGroup(
                new LowerIngestorLiftCmd(ingestorLiftObj),
                new IntakeCubeCmd(ingestorIntakeObj, gamePieceScoopObj));

        SequentialCommandGroup lowerAndExpel = new SequentialCommandGroup(new ExpelIngestorLiftCmd(ingestorLiftObj),
                shootCubeLower);

        /*
         * ParallelCommandGroup stopAndRaise = new ParallelCommandGroup(
         * new StopIngestorIntake(ingestorIntakeObj), new
         * RaiseIngestorLiftCmd(ingestorLiftObj)
         * );
         */

        /*
         * SequentialCommandGroup ingestionSequence = new SequentialCommandGroup(
         * lowerAndIngest, stopAndRaise); //, new RaiseIngestorLiftCmd(ingestorLiftObj)
         */
        Trigger opATrigger = operatorControllerObj.a();
        Trigger driverBTrigger = driverControllerObj.b();
        Trigger opXTrigger = operatorControllerObj.x();
        Trigger opYTrigger = operatorControllerObj.y();
        Trigger opRightTriggerTrigger = operatorControllerObj.rightTrigger();
        Trigger opRightBumperTrigger = operatorControllerObj.rightBumper();

        // TODO: The above might allow us to interrupt the command with the X button.
        // operatorControllerObj.y().whileFalse(ingestorLiftObj.raiseIngestorLift());

        opRightTriggerTrigger.whileTrue(shootCubeMid);
        opRightBumperTrigger.whileTrue(shootCubeUpper);

        opATrigger.whileTrue(lowerAndExpel);
        driverBTrigger.whileTrue(new BalancePIDCmd(drivetrainObj, true));
        // opXTrigger.whileTrue(new LowerConeFlipper(coneFlipperObj));
        opYTrigger.whileTrue(lowerAndIngest);

    }

    /*
     * Example turtle mode
     * Drive at half speed when the right bumper is held
     * new JoystickButton(m_driverController, Button.kRightBumper.value)
     * .onTrue(new InstantCommand(() -> m_robotDrive.setMaxOutput(0.5)))
     * .onFalse(new InstantCommand(() -> m_robotDrive.setMaxOutput(1)));
     * 
     */

    /*
     * private void configureAutoCommands() {
     * autoEventMap.put("event1", Commands.print("Passed marker 1"));
     * autoEventMap.put("event2", Commands.print("passed marker 2"));
     * 
     * 
     * List<PathPlannerTrajectory> auto1Paths =
     * PathPlanner.loadPathGroup(
     * "Default Auton", 4, 3);
     * 
     * Command autoTest =
     * Commands.sequence(
     * new FollowPathWithEvents(
     * new FollowPathCmd(auto1Paths.get(0), drivetrainObj, true)
     * auto1Paths.get(0).getMarkers(0),
     * autoEventMap),
     * // Commands.runOnce(drivetrainObj, drivetrainObj),
     * // Commands.waitSeconds(5.0),
     * // Commands.runOnce(drivetrainObj::disableXstance, drivetrainObj),
     * new FollowPathWithEvents(
     * new FollowPathCmd(auto1Paths.get(1), drivetrainObj, false)
     * auto1Paths.get(0).getMarkers(0),
     * autoEventMap)
     * );
     * 
     * }
     */

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        Command selectedCommand = autonChooser.getSelected();
        return selectedCommand;

    }
}
