package se.sthlm.jfw.toyrobot.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.sthlm.jfw.toyrobot.Robot;

public class RobotTest {
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
  public void testInputA() {
    String[] inputData = {"testInputA.txt"};
    Robot.main(inputData);
    assertEquals("0, 1, NORTH\r\n", outContent.toString());
  }

  @Test
  public void testInputB() {
    String[] inputData = {"testInputB.txt"};
    Robot.main(inputData);
    assertEquals("0, 0, WEST\r\n", outContent.toString());
  }

  @Test
  public void testInputC() {
    String[] inputData = {"testInputC.txt"};
    Robot.main(inputData);
    assertEquals("3, 3, NORTH\r\n", outContent.toString());
  }

}
