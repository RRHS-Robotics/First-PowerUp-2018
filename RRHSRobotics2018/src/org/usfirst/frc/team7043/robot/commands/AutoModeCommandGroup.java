package org.usfirst.frc.team7043.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group for Auto Mode
 * 
 * Available Constructors:
 * IntakeCommand(Double time, Double speed)
 * 	- time is in seconds
 * 	- speed is between -1.0 (full release rate) and 1.0 (full pull rate)
 * PullyCommand(Double time, Double speed)
 * 	- time is in seconds
 * 	- speed is between -1.0 (full lower rate) and 1.0 (full raise rate)
 * TankDriveCommand(Double time, Double speed, Double rotation)
 * 	- time is in seconds
 * 	- speed is between -1.0 (full reverse) and 1.0 (full forward)
 * 	- rotation is between -1.0 (full left turn rate) and 1.0 (full right turn rate)
 * 
 * Examples:
 * To run multiple commands in a sequence,
 * use addSequential()
 * e.g. addSequential(new Command1());
 *      addSequential(new Command2());
 * Command1 and Command2 will run in order.
 *
 * To run multiple commands at the same time,
 * use addParallel()
 * e.g. addParallel(new Command1());
 *      addSequential(new Command2());
 * Command1 and Command2 will run in parallel.
 * 
 * A command group will require all of the subsystems that each member
 * would require.
 * e.g. if Command1 requires chassis, and Command2 requires arm,
 * a CommandGroup containing them would require both the chassis and the
 * arm
 */
public class AutoModeCommandGroup extends CommandGroup {

    public AutoModeCommandGroup(String path) {
    		switch(path) {
    			case "right":	
    				right();
				break;
    			case "left":
    				left();
				break;
    			case "midRight":
    				midRight();
				break;
			case "midLeft":
				midLeft();
				break;
			case "rightBlock":
				rightBlock();
				break;
			case "leftBlock":
				leftBlock();
				break;
			case "midRightBlock":
				midRightBlock();
				break;
			case "midLeftBlock":
				midLeftBlock();
				break;
    		}
    }
    
    //Right Auto Drive
    private void right() {
    		// Add Commands here:
    	
    }

    //Left Auto Drive
    private void left() {
    		// Add Commands here:
    	
    }

    //Right Auto Drive
    private void midRight() {
    		// Add Commands here:
    	
    }

    //Right Auto Drive
    private void midLeft() {
    		// Add Commands here:
    	
    }

    //Right Auto Drive w/ Block
    private void rightBlock() {
    		// Add Commands here:
    	
    }

    //Left Auto Drive w/ Block
    private void leftBlock() {
    		// Add Commands here:
    	
    }

    //Mid Right Auto Drive w/ Block
    private void midRightBlock() {
    		// Add Commands here:
    	
    }

    //Mid Left Auto Drive w/ Block
    private void midLeftBlock() {
    		// Add Commands here:
    	
    }
}
