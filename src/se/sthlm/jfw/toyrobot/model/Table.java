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

  public boolean isPositionWithinTable(Position position) {
    if(position.getXPos() < 0) {
      return false;
    }
    if(position.getYPos() < 0) {
      return false;
    }
    if(position.getXPos() >= this.width) {
      return false;
    }
    if(position.getYPos() >= this.height) {
      return false;
    }
    return true;
  }

}
