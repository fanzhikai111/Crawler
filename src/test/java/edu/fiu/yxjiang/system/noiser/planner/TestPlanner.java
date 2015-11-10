package test.java.edu.fiu.yxjiang.system.noiser.planner;

import main.java.edu.fiu.yxjiang.system.noiser.planner.Planner;

import org.junit.Before;
import org.junit.Test;

public class TestPlanner {
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void testParseFile() {
		String filename = "/home/yxjiang/Downloads/testTasks.txt";
		Planner planner = new Planner(filename);
		planner.run();
	}
	
}
