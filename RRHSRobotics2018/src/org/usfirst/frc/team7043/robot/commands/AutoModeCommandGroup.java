package org.usfirst.frc.team7043.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
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
 * DriveCommand(Double time, Double speed, Double rotation)
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

	Preferences prefs = Preferences.getInstance();
	
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
    		//prefs.getDouble("<Description>", value);
    		addParallel(new PullyCommand(2.9, 0.1));
    		addSequential(new DriveCommand(0.1, 0.25,0.0));
    		addSequential(new DriveCommand(0.2, 0.5, 0.0));
    		addSequential(new DriveCommand(1.2, 1.0, 0.0));
    		addSequential(new DriveCommand(0.2, 0.5, 0.0));
    		addSequential(new DriveCommand(0.1, 0.25, 0.0));
    		
    }

    //Left Auto Drive
    private void left() {
    		addParallel(new PullyCommand(2.9, 0.1));
		addSequential(new DriveCommand(0.1, 0.25,0.0));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(1.2, 1.0, 0.0));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(0.1, 0.25, 0.0));
    }

    //Right Auto Drive
    private void midRight() {
    		addSequential(new DriveCommand(2.0, 0.5, 0.5));
    	
    }

    //Right Auto Drive
    private void midLeft() {
    		addSequential(new DriveCommand(2.0, 0.5, 0.5));
    	
    }

    //Right Auto Drive w/ Block
    private void rightBlock() {
    		addParallel(new IntakeCommand(8.6, 0.1));
    		addParallel(new PullyCommand(11.0, 0.2));
		addSequential(new DriveCommand(0.6, 0.25, 0.0));
		addSequential(new DriveCommand(5.0, 0.5, 0.0));
		addSequential(new DriveCommand(0.6, 0.25, 0.0));
		addSequential(new DriveCommand(1.2, 0.1, -0.5));//this ~90 degrees right
		addSequential(new IntakeCommand(0.6, -1.0));
    
    	/* OG CODE SAVE
    		addParallel(new PullyCommand(2.7, 0.1));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(1.0, 1.0, 0.0));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(0.2, 0.2, -1.0));//this ~90 degrees right
		addSequential(new IntakeCommand(0.4, -1.0));
  */
    }

    //Left Auto Drive w/ Block
    private void leftBlock() {
    		addParallel(new PullyCommand(2.7, 0.1));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(1.2, 1.0, 0.0));
		addSequential(new DriveCommand(0.2, 0.5, 0.0));
		addSequential(new DriveCommand(0.2, -0.2, 1.0));//this ~90 degrees right
		addSequential(new IntakeCommand(0.4, -1.0));    	

    	
    }

    //Mid Right Auto Drive w/ Block
    private void midRightBlock() {
    		addParallel(new IntakeCommand(6.0,0.5));
    		addSequential(new DriveCommand(2.0, 0.5, 0.5));
    		//addParallel(new DriveCommand())
    	
    }

    //Mid Left Auto Drive w/ Block
    private void midLeftBlock() {
    		// Add Commands here:
    	
    }
}
