package others.unde.annotations;

import others.unde.annotations.Test;
import others.unde.annotations.TesterInfo;
import others.unde.annotations.TesterInfo.Priority;


public class TestExample {
	@TesterInfo(
			priority = Priority.HIGH,
			createdBy = " testA mkyong.com",
					tags = {"testA","sales","test" }
		)
	@Test
	void testA() {
	  if (true)
		throw new RuntimeException("This test always failed");
	}
	@TesterInfo(
			priority = Priority.HIGH,
			createdBy = "mkyong.com",
					tags = {"sales","test" }
		)
	@Test(enabled = false)
	void testB() {
	  if (false)
		throw new RuntimeException("This test always passed");
	}
	@TesterInfo(
			priority = Priority.HIGH,
			createdBy = "mkyong.com",
					tags = {"sales","test" }
		)
	@Test(enabled = true)
	void testC() {
	  if (10 > 1) {
		// do nothing, this test always passed.
	  }
	}

}
