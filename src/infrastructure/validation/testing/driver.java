package infrastructure.validation.testing;

import infrastructure.validation.testing.TestHarness;
import infrastructure.tests.HarnessInstanceTest;
class driver{
	public static void main(String args[]){
		TestHarness th = new TestHarness();
		String testNamePath = "src/infrastructure/tests/HarnessInstanceTest";
		th.runByName(testNamePath);

	}
}
