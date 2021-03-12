package se.sthlm.jfw.toyrobot.data;

public enum Command {
  PLACE("PLACE"),
  MOVE("MOVE"),
  LEFT("LEFT"),
  RIGHT("RIGHT"),
  REPORT("REPORT"),
  INVALID_COMMAND("INVALID_COMMAND");

  public final String commandString;

  private Command(String commandString) {
    this.commandString = commandString;
  }

  public static Command getCommand(String commandString) {
    for (Command commandElement : values()) {
      if (commandElement.commandString.equals(commandString)) {
        return commandElement;
      }
    }
    return INVALID_COMMAND;
  }
}
