package se.sthlm.jfw.toyrobot.model;

public class Position {
  private int xPos;
  private int yPos;

  public Position(int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public int getXPos() {
    return xPos;
  }
  public void setXPos(int xPos) {
    this.xPos = xPos;
  }
  public int getYPos() {
    return yPos;
  }
  public void setYPos(int yPos) {
    this.yPos = yPos;
  }

  @Override
  public String toString() {
    return xPos + ", " + yPos;
  }
}
