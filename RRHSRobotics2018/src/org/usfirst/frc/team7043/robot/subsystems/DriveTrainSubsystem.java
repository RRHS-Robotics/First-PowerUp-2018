package org.usfirst.frc.team7043.robot.subsystems;

import org.usfirst.frc.team7043.robot.RobotMap;
import org.usfirst.frc.team7043.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    
    public void drive(Double speed, Double rotation) {
		RobotMap.robotDriveMain.arcadeDrive(speed, rotation); // drive forwards half speed
    }
    
    public void stop() {
    		RobotMap.robotDriveMain.stopMotor();
    }
}

