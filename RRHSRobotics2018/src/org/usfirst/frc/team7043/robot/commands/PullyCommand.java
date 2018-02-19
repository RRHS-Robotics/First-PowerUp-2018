package org.usfirst.frc.team7043.robot.commands;

import org.usfirst.frc.team7043.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PullyCommand extends Command {

	private Double speed;
	
	Preferences prefs = Preferences.getInstance();
	
	//AutoMode Constructor
    public PullyCommand(Double timeIn, Double speedIn) {
        // Use requires() here to declare subsystem dependencies
    		requires(Robot.Pully);
    		setTimeout(timeIn);
    		speed = speedIn;
    }
    
    //TeleMode Constructor
    public PullyCommand(String mode) {
        // Use requires() here to declare subsystem dependencies
    		requires(Robot.Pully);
    		if(mode == "raise") {
    			speed = prefs.getDouble("Speed of pully up: ", 0.4);
    		} else if(mode == "lower") {
    			speed = prefs.getDouble("Speed of pully down: ", -0.4);
    		}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.Pully.activatePulley(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.Pully.stopPulley();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.Pully.stopPulley();
    }
}
