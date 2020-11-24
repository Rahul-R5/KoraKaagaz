package infrastructure.validation.testing;

import infrastructure.validation.testing.TestHarness;
class runner{
	public static void main(String args[]){
		TestHarness th = new TestHarness();
		String testNamePath = "src/infrastructure/tests/HarnessTest.java";
		th.runByName(testNamePath);

	}
}
