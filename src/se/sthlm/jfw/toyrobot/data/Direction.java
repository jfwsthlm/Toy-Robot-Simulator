package se.sthlm.jfw.toyrobot.data;

public enum Direction {
  SOUTH(0, "SOUTH"),
  WEST(1, "WEST"),
  NORTH(2, "NORTH"),
  EAST(3, "EAST"),
  INVALID_DIRECTION(-1, "INVALID");
      
  public final int directionValue;
  public final String directionString;

  private Direction(int directionValue, String directionString) {
    this.directionValue = directionValue;
    this.directionString = directionString;
  }

  public static Direction getDirection(String directionString) {
    for (Direction directionElement : values()) {
      if (directionElement.directionString.equals(directionString)) {
        return directionElement;
      }
    }
    return INVALID_DIRECTION;
  }

  public static Direction getDirection(int directionValue) {
    for (Direction directionElement : values()) {
      if (directionElement.directionValue == directionValue) {
        return directionElement;
      }
    }
    return INVALID_DIRECTION;
  }

  public static boolean isDirectionValid(Direction direction) {
    return direction != Direction.INVALID_DIRECTION;
  }
}
