package org.usfirst.frc.team7043.robot.commands;

import org.usfirst.frc.team7043.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	
	private Double speed;
	private Double rotation;
	
	Preferences prefs = Preferences.getInstance();
	
	//AutoMode Constructor
    public DriveCommand(Double timeIn, Double speedIn, Double rotationIn) {
        // Use requires() here to declare subsystem dependencies
    		requires(Robot.DriveTrain);
    		setTimeout(timeIn);
    		speed = speedIn;
    		rotation = rotationIn;
    }
    
    //TeleMode Constructor
    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
    		requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(speed != null) {
    			Robot.DriveTrain.drive(speed, rotation);
    		} else {
    			Robot.DriveTrain.drive(-Robot.refOI.controller.getY(Hand.kLeft)*prefs.getDouble("Percent of Max Speed (0.0 to 1.0)", 1.0), Robot.refOI.controller.getX(Hand.kLeft)*prefs.getDouble("Percent of Max Speed (0.0 to 1.0)", 1.0));
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.DriveTrain.stop(); // stop robot
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.DriveTrain.stop();
    }
}
