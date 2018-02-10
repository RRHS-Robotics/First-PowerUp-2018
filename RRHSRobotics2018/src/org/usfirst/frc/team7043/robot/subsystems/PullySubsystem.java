package org.usfirst.frc.team7043.robot.subsystems;

import org.usfirst.frc.team7043.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;


public class PullySubsystem extends Subsystem{

	
	public void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void pullUp() {
		RobotMap.pullyMotor.set(0);
	//change from zero now	
	}
	public void drownDown() {
		RobotMap.pullyMotor.set(0);
	}

}
