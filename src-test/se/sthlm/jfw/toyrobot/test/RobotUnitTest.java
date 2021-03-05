package se.sthlm.jfw.toyrobot.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.sthlm.jfw.toyrobot.Robot;
import se.sthlm.jfw.toyrobot.data.Direction;

public class RobotUnitTest {
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
	public void testPlace() {
		Robot robot = new Robot();
		assertTrue(robot.place(3, 2, Direction.NORTH));
		assertTrue(robot.report());
		assertEquals("Output should be 3, 2, NORTH", "3, 2, NORTH\r\n", outContent.toString());
	}

	@Test
	public void testMove() {
		Robot robot = new Robot();
		assertTrue(robot.place(0, 0, Direction.NORTH));
		assertTrue(robot.move());
		assertTrue(robot.report());
		assertEquals("Direction should be 0, 1, NORTH", "0, 1, NORTH\r\n", outContent.toString());
	}

	@Test
	public void testLeft() {
		Robot robot = new Robot();
		assertTrue(robot.place(0, 0, Direction.SOUTH));
		assertTrue(robot.left());
		assertTrue(robot.report());
		assertEquals("Direction should be EAST", "0, 0, EAST\r\n", outContent.toString());
	}
	
	@Test
	public void testRight() {
		Robot robot = new Robot();
		assertTrue(robot.place(0, 0, Direction.SOUTH));
		assertTrue(robot.right());
		assertTrue(robot.report());
		assertEquals("Direction should be WEST", "0, 0, WEST\r\n", outContent.toString());
	}

}
