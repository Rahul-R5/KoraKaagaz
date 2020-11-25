package infrastructure.tests;
import infrastructure.validation.testing.TestCase;
import infrastructure.validation.testing.TestHarness;

public class HarnessInstanceTest extends TestCase{
	
	HarnessInstanceTest(){
		this.setDescription("This is a sample test for Test Harness which includes only printing stuff. No other modules involved");
		this.setPriority(0);
	}
	
	public boolean run(){
		TestHarness th; 
		try {
			th = new TestHarness();
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
} 
