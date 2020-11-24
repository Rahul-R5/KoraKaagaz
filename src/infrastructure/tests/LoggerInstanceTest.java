package infrastructure.tests;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.testing.TestCase;

public class LoggerInstanceTest extends TestCase {
	public boolean run(){
		ILogger loggerInstance;
		try {
			loggerInstance = LoggerFactory.getLoggerInstance();
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;		
	}
}