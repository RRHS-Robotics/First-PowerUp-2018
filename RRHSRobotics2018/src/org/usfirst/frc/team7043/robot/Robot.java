/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7043.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team7043.robot.commands.AutoExampleRight;
import org.usfirst.frc.team7043.robot.commands.AutoLeftDriveBlockCommand;
import org.usfirst.frc.team7043.robot.commands.AutoLeftDriveCommand;
import org.usfirst.frc.team7043.robot.commands.AutoRightDriveBlockCommand;
import org.usfirst.frc.team7043.robot.commands.AutoRightDriveCommand;
import org.usfirst.frc.team7043.robot.commands.IntakePullCommand;
import org.usfirst.frc.team7043.robot.commands.IntakeReleaseCommand;
import org.usfirst.frc.team7043.robot.commands.LowerIntakeCommand;
import org.usfirst.frc.team7043.robot.commands.RaiseIntakeCommand;
import org.usfirst.frc.team7043.robot.commands.TankDriveCommand;
import org.usfirst.frc.team7043.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team7043.robot.subsystems.IntakeSubsystem;

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
	public static OI refOI = new OI();
	public static RobotMap robotMap = new RobotMap();

	
	
	Command driveTrainCommand = new TankDriveCommand();
	
	Command selectedAutonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoChooser.addDefault("Right Auto Drive", new AutoRightDriveCommand());
		//autoChooser.addDefault("Middle Auto Drive", new AutoMiddleDriveCommand());
		autoChooser.addDefault("Left Auto Drive", new AutoLeftDriveCommand());
		autoChooser.addDefault("Left Auto Drive With Block", new AutoLeftDriveBlockCommand());
		//autoChooser.addDefault("Left Auto Drive and block", new AutoRightDriveBlockCommand());
		autoChooser.addDefault("Example to drive and then turn", new AutoExampleRight());
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", autoChooser);
		RobotMap.leftDrive.setInverted(true);
		RobotMap.robotDriveMain = new DifferentialDrive(RobotMap.leftDrive, RobotMap.rightDrive);
		refOI.triggerLeft.whileHeld(new IntakePullCommand());
		refOI.triggerRight.whileHeld(new IntakeReleaseCommand());
		refOI.raiseIntake.whileHeld(new RaiseIntakeCommand());
		refOI.lowerIntake.whileHeld(new LowerIntakeCommand());
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

		RobotMap.masterTimer.reset();
		RobotMap.masterTimer.start();
		
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
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
