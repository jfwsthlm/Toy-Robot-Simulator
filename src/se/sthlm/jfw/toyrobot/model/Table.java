package se.sthlm.jfw.toyrobot.model;

public class Table {
	private int width;
	private int height;
	
	public Table(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean positionIsWithinTable(Position position) {
		if(position.getxPos() < 0)
			return false;
		if(position.getyPos() < 0)
			return false;
		if(position.getxPos() >= this.width)
			return false;
		if(position.getxPos() >= this.height)
			return false;
		
		return true;		
	}

}
