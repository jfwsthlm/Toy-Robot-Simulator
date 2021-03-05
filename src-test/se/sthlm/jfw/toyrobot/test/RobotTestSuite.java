package se.sthlm.jfw.toyrobot.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	RobotTest.class,
	RobotUnitTest.class,
	RobotNegativeTest.class
})

public class RobotTestSuite {
}