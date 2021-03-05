package se.sthlm.jfw.toyrobot.data;

public class Direction {
	public static int SOUTH = 0;
	public static int WEST = 1;
	public static int NORTH = 2;
	public static int EAST = 3;
	public static int INVALID_DIRECTION = -1;
	
	public static int getDirection(int direction) {
		if(direction == 0)
			return SOUTH;
		if(direction == 1)
			return WEST;
		if(direction == 2)
			return NORTH;
		if(direction == 3)
			return EAST;
		return -1;
	}
	
	public static int getDirection(String direction) {
		if(direction.equals(Message.SOUTH))
			return SOUTH;
		if(direction.equals(Message.WEST))
			return WEST;
		if(direction.equals(Message.NORTH))
			return NORTH;
		if(direction.equals(Message.EAST))
			return EAST;
		return -1;
	}

	public static String getDirectionString(int direction) {
		if(direction == 0)
			return Message.SOUTH;
		if(direction == 1)
			return Message.WEST;
		if(direction == 2)
			return Message.NORTH;
		if(direction == 3)
			return Message.EAST;
		return Message.INVALID;
	}

	public static boolean isDirectionValid(int direction) {
		return getDirection(direction) != INVALID_DIRECTION;
	}
}
