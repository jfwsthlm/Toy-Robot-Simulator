package se.sthlm.jfw.toyrobot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import se.sthlm.jfw.toyrobot.data.Command;
import se.sthlm.jfw.toyrobot.data.Direction;
import se.sthlm.jfw.toyrobot.data.Message;
import se.sthlm.jfw.toyrobot.model.Position;
import se.sthlm.jfw.toyrobot.model.Table;

public class Robot {
	
	private Table table;
	private Position position;
	private int direction;
	private boolean validPlaceCommandHasBeenIssued;
	
	public Robot() {
		this(0,0);
	}
	
	private Robot(int xPos, int yPos) {
		table = new Table(5, 5);
		position = new Position(xPos, yPos);
		direction = Direction.SOUTH;
		validPlaceCommandHasBeenIssued = false;
	}
	
	public boolean place(int xPos, int yPos, int direction) {
		if(xPos >= table.getWidth())
			return false;
		if(xPos < 0)
			return false;
		if(yPos >= table.getHeight())
			return false;
		if(yPos < 0)
			return false;
		if(!Direction.isDirectionValid(direction))
			return false;
		
		this.position = new Position(xPos, yPos);
		this.direction = direction;
		return true;
	}
	
	public boolean move() {
		Position newPosition = this.position;
		if(direction == Direction.SOUTH)
			newPosition.setyPos(position.getyPos() - 1);
		if(direction == Direction.WEST)
			newPosition.setxPos(position.getxPos() - 1);
		if(direction == Direction.NORTH)
			newPosition.setyPos(position.getyPos() + 1);
		if(direction == Direction.EAST)
			newPosition.setxPos(position.getxPos() + 1);
			
		if(table.positionIsWithinTable(newPosition))
			this.position = newPosition;
		else
			return false;
		
		return true;
	}
	
	public boolean left() {
		direction --;
		if(direction < 0)
			direction = 3;
		return true;
	}
	
	public boolean right() {
		direction ++;
		if(direction > 3)
			direction = 0;
		return true;
	}
	
	public boolean report() {
		System.out.println(this.position + ", " + Direction.getDirectionString(this.direction));
		return true;
	}

	private boolean operate(String robotCommand) {
		String firstPartOfCommand = robotCommand.split(" ")[0];
		if(firstPartOfCommand.equals(Command.PLACE)) {
			String secondPartOfCommand = robotCommand.split(" ")[1];
			int xPositionFromPlaceCommand = Integer.parseInt(secondPartOfCommand.split(",")[0]);
			int yPositionFromPlaceCommand = Integer.parseInt(secondPartOfCommand.split(",")[1]);
			int directionFromPlaceCommand = Direction.getDirection(secondPartOfCommand.split(",")[2]);
			validPlaceCommandHasBeenIssued = this.place(xPositionFromPlaceCommand, yPositionFromPlaceCommand, directionFromPlaceCommand);
		} else if(firstPartOfCommand.equals(Command.MOVE)) {
			if(validPlaceCommandHasBeenIssued)
				this.move();
		} else if(firstPartOfCommand.equals(Command.LEFT)) {
			if(validPlaceCommandHasBeenIssued)
				this.left();
		} else if(firstPartOfCommand.equals(Command.RIGHT)) {
			if(validPlaceCommandHasBeenIssued)
				this.right();
		} else if(firstPartOfCommand.equals(Command.REPORT)) {
			if(validPlaceCommandHasBeenIssued)
				this.report();
		} else {
			System.out.println(Message.ERROR_UNKNOWN_COMMAND);
		}
		return validPlaceCommandHasBeenIssued;
	}
	
	public static void main(String[] args) {
		try {
			if(args.length != 1) {
				System.out.println(Message.ERROR_WRONG_NUMBER_OF_ARGUMENTS);
				return;
			}
			
			if(args[0].toLowerCase().equals("help")) {
				System.out.println(Message.HELP_MESSAGE);
				return;
			}
			
			Robot robot = new Robot();
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			String command = input.readLine();
			while(command != null) {
				robot.operate(command);
				command = input.readLine();
			}
			input.close();
		} catch(FileNotFoundException fnfe) {
			System.out.println(Message.ERROR_FILE_NOT_FOUND + ": " + args[0]);
		} catch(IOException ioe) {
			System.out.println(Message.ERROR_GENERAL_IO + ": " + ioe.getMessage());
		}
	}
	
}
