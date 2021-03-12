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
  private Direction direction;
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

  public boolean place(int xPos, int yPos, Direction direction) {
    Position placePosition = new Position(xPos, yPos);
    boolean placePositionIsWithinTable = table.isPositionWithinTable(placePosition);
    if(!placePositionIsWithinTable) {
      return false;
    }
    if(!Direction.isDirectionValid(direction)) {
      return false;
    }
    this.position = placePosition;
    this.direction = direction;
    this.validPlaceCommandHasBeenIssued = true;
    return true;
  }

  public boolean move() {
    Position newPosition = new Position(this.position.getXPos(), this.position.getYPos());
    if(direction == Direction.SOUTH) {
      newPosition.setYPos(position.getYPos() - 1);
    }
    if(direction == Direction.WEST) {
      newPosition.setXPos(position.getXPos() - 1);
    }
    if(direction == Direction.NORTH) {
      newPosition.setYPos(position.getYPos() + 1);
    }
    if(direction == Direction.EAST) {
      newPosition.setXPos(position.getXPos() + 1);
    }
    if(table.isPositionWithinTable(newPosition)) {
      this.position = newPosition;
      return true;
    }
    return false;
  }

  public boolean left() {
    if(Direction.isDirectionValid(direction)) {
      int newDirectionValue = direction.directionValue - 1;
      if(newDirectionValue < 0) {
        newDirectionValue = 3;
      }
      direction = Direction.getDirection(newDirectionValue);
      return true;
    }
    return false;
  }

  public boolean right() {
    if(Direction.isDirectionValid(direction)) {
      int newDirectionValue = direction.directionValue + 1;
      if(newDirectionValue > 3) {
        newDirectionValue = 0;
      }
      direction = Direction.getDirection(newDirectionValue);
      return true;
    }
    return false;
  }

  public boolean report() {
    if(validPlaceCommandHasBeenIssued) {
      System.out.println(this.position + ", " + this.direction.directionString);
    } else {
      System.out.println(Message.ERROR_NO_VALID_PLACE_COMMAND);
    }
    return true;
  }

  private boolean operate(String robotCommand) {
    String firstPartOfCommand = robotCommand.split(" ")[0];
    if(Command.PLACE == Command.getCommand(firstPartOfCommand)) {
      String secondPartOfCommand = robotCommand.split(" ")[1];
      int xPositionFromPlaceCommand = Integer.parseInt(secondPartOfCommand.split(",")[0]);
      int yPositionFromPlaceCommand = Integer.parseInt(secondPartOfCommand.split(",")[1]);
      Direction directionFromPlaceCommand = Direction.getDirection(secondPartOfCommand.split(",")[2]);
      this.place(xPositionFromPlaceCommand, yPositionFromPlaceCommand, directionFromPlaceCommand);
    } else if(Command.MOVE == Command.getCommand(firstPartOfCommand)) {
      if(validPlaceCommandHasBeenIssued) {
        this.move();
      }
    } else if(Command.LEFT == Command.getCommand(firstPartOfCommand)) {
      if(validPlaceCommandHasBeenIssued) {
        this.left();
      }
    } else if(Command.RIGHT == Command.getCommand(firstPartOfCommand)) {
      if(validPlaceCommandHasBeenIssued) {
        this.right();
      }
    } else if(Command.REPORT == Command.getCommand(firstPartOfCommand)) {
        this.report();
    } else {
      System.out.println(Message.ERROR_UNKNOWN_COMMAND + ": " + firstPartOfCommand);
    }
    return validPlaceCommandHasBeenIssued;
  }

  public static void main(String[] args) {
    try {
      if(args.length != 1) {
        System.out.println(Message.ERROR_WRONG_NUMBER_OF_ARGUMENTS);
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
