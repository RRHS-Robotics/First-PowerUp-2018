package org.usfirst.frc.team7043.robot.subsystems;

import org.usfirst.frc.team7043.robot.RobotMap;
import org.usfirst.frc.team7043.robot.commands.TankDriveCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDriveCommand());
    }
    
    public void telopDrive(Joystick joyLeft, Joystick joyRight) {
    		RobotMap.robotDriveMain.tankDrive(-joyLeft.getY(), -joyRight.getY());
    }
    
    public void autoDrive(Double speed, Double direction, Double time) {
    		if (RobotMap.masterTimer.get() < time) {
    			RobotMap.robotDriveMain.arcadeDrive(speed, direction); // drive forwards half speed
		} else {
			RobotMap.robotDriveMain.stopMotor(); // stop robot
		}
    }
    
    public void stop() {
    		RobotMap.robotDriveMain.stopMotor();
    }
}

