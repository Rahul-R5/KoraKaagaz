package infrastructure.tests;
import infrastructure.tests.LoggerInstanceTest;
import infrastructure.validation.testing.ITest;
import infrastructure.validation.testing.TestCase;
/**
 * Replaced with test harness when that is working
 * @author rahulr
 *
 */
class driver{
	public static void main(String args[]) {
		TestCase test = new LoggerInstanceTest();
		boolean result = test.run();
		System.out.println(result);
	}
}
