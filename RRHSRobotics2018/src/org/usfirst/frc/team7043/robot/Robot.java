/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7043.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team7043.robot.commands.IntakeCommand;
import org.usfirst.frc.team7043.robot.commands.PullyCommand;
import org.usfirst.frc.team7043.robot.commands.AutoModeCommandGroup;
import org.usfirst.frc.team7043.robot.commands.DriveCommand;
import org.usfirst.frc.team7043.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team7043.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team7043.robot.subsystems.PullySubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrainSubsystem DriveTrain = new DriveTrainSubsystem();
	public static final IntakeSubsystem Intake = new IntakeSubsystem();
	public static final PullySubsystem Pully = new PullySubsystem();
	public static OI refOI = new OI();
	public static RobotMap robotMap = new RobotMap();
	public Double ratio;
	public Double autoPullyUpSpeed;
	public Double autoPullyDownSpeed;
	public Double autoIntakeInSpeed;
	public Double autoIntakeOutSpeed;

	public Preferences prefs;
	
	public UsbCamera topCamera;
	public UsbCamera intakeCamera;
	public VideoSink cameraServer;
	public CvSink cvsink1;
	public CvSink cvsink2;
	
	Command driveTrainCommand = new DriveCommand();
	
	Command selectedAutonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoChooser.addDefault("Right Auto Drive", new AutoModeCommandGroup("right"));
		autoChooser.addDefault("Left Auto Drive", new AutoModeCommandGroup("left"));
		//autoChooser.addDefault("Mid Right Auto Drive", new AutoModeCommandGroup("midRight"));
		//autoChooser.addDefault("Right Auto Drive", new AutoModeCommandGroup("midLeft"));
		autoChooser.addDefault("Right Auto Block Drive", new AutoModeCommandGroup("rightBlock"));
		autoChooser.addDefault("Left Auto Block Drive", new AutoModeCommandGroup("leftBlock"));
	//	autoChooser.addDefault("Right Auto Drive", new AutoModeCommandGroup("midRightBlock"));
	//	autoChooser.addDefault("Right Auto Drive", new AutoModeCommandGroup("midLeftBlock"));
		
		SmartDashboard.putData("Auto Mode:", autoChooser);
		
		prefs = Preferences.getInstance();
		ratio = prefs.getDouble("Percent of Max Speed (0.0 to 1.0)", 1.0);
		autoPullyUpSpeed = prefs.getDouble("Speed of pully up: ", 0.4);
		autoPullyDownSpeed = prefs.getDouble("Speed of pully down: ", -0.4);
		autoIntakeInSpeed = prefs.getDouble("Speed of intake pull: ", 0.4);
		autoIntakeOutSpeed = prefs.getDouble("Speed of intake release: ", -1.0);
		
		topCamera = CameraServer.getInstance().startAutomaticCapture(0);
		intakeCamera = CameraServer.getInstance().startAutomaticCapture(1);
		cameraServer = CameraServer.getInstance().getServer();
		cvsink1 = new CvSink("IntakeCameraCV");
		cvsink1.setSource(intakeCamera);
		cvsink1.setEnabled(true);
		cvsink2 = new CvSink("TopCameraCV");
		cvsink2.setSource(topCamera);
		cvsink2.setEnabled(true);
		
		RobotMap.leftDrive.setInverted(true);
		RobotMap.robotDriveMain = new DifferentialDrive(RobotMap.leftDrive, RobotMap.rightDrive);
		refOI.intakeReverse.whileHeld(new IntakeCommand("pull"));
		refOI.intakeForward.whileHeld(new IntakeCommand("release"));		
		refOI.raiseIntake.whileHeld(new PullyCommand("raise"));
		refOI.lowerIntake.whileHeld(new PullyCommand("lower"));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		selectedAutonomousCommand = autoChooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (selectedAutonomousCommand != null) {
			selectedAutonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (selectedAutonomousCommand != null) {
			selectedAutonomousCommand.cancel();
		}
		if (driveTrainCommand != null) {
			driveTrainCommand.start();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		if (refOI.intakeCameraButton() && !refOI.topCameraButton()) {
			SmartDashboard.putString("Info", "Setting Intake Camera");
		    cameraServer.setSource(intakeCamera);
		} else if (!refOI.intakeCameraButton() && refOI.topCameraButton()) {
			SmartDashboard.putString("Info", "Setting Top Camera");
		    cameraServer.setSource(topCamera);
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
