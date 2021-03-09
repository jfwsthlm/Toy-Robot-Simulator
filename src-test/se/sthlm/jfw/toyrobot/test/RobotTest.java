package se.sthlm.jfw.toyrobot.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.FileWriter;
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
    try {
      File file = new File("testInputA.txt");
      FileWriter fr = new FileWriter(file, false);
      fr.write("PLACE 0,0,NORTH\nMOVE\nREPORT");
      fr.close();
      String[] inputData = {file.getName()};
      Robot.main(inputData);
    } catch(Exception e) {
      //Silently ignored
    }
    assertEquals("0, 1, NORTH\r\n", outContent.toString());
  }

  @Test
  public void testInputB() {
    try {
      File file = new File("testInputB.txt");
      FileWriter fr = new FileWriter(file, false);
      fr.write("PLACE 0,0,NORTH\nLEFT\nREPORT");
      fr.close();
      String[] inputData = {file.getName()};
      Robot.main(inputData);
    } catch(Exception e) {
      //Silently ignored
    }
    assertEquals("0, 0, WEST\r\n", outContent.toString());
  }

  @Test
  public void testInputC() {
    try {
      File file = new File("testInputC.txt");
      FileWriter fr = new FileWriter(file, false);
      fr.write("PLACE 1,2,EAST\nMOVE\nMOVE\nLEFT\nMOVE\nREPORT");
      fr.close();
      String[] inputData = {file.getName()};
      Robot.main(inputData);
    } catch(Exception e) {
      //Silently ignored
    }
    assertEquals("3, 3, NORTH\r\n", outContent.toString());
  }

}
