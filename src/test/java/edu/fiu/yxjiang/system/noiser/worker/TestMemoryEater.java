package test.java.edu.fiu.yxjiang.system.noiser.worker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import main.java.edu.fiu.yxjiang.system.noiser.worker.MemoryEater;

import org.junit.Test;

public class TestMemoryEater {

	@Test
	public void testMemoryEater() throws InterruptedException, FileNotFoundException {
		MemoryEater eater = new MemoryEater(10);
		eater.start();
		eater.join();
	}
}
