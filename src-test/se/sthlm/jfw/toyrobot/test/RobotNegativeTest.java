package se.sthlm.jfw.toyrobot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.sthlm.jfw.toyrobot.Robot;

public class RobotNegativeTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  public void testUnknownInputFile() {
    String[] inputData = {"fileThatIsNotFound.txt"};
    Robot.main(inputData);
    assertEquals("File not found: fileThatIsNotFound.txt\r\n", outContent.toString());
  }

  @Test
  public void testUnknownDirection() {
    Robot robot = new Robot();
    assertFalse(robot.place(3, 2, 5));
  }

}