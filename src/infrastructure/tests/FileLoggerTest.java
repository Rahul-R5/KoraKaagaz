package infrastructure.tests;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ModuleID;
import infrastructure.validation.logger.LogLevel;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.testing.TestCase;

public class FileLoggerTest extends TestCase {
	public FileLoggerTest(){
		this.setDescription("This test outputs to a console all combinations of module and log level. Expected output is modules x log levels number of lines being printed to console");
		this.setPriority(1);
		this.setCategory("infrastructure");
	}
	public boolean run(){
		ILogger loggerInstance;
		try {
			loggerInstance = LoggerFactory.getLoggerInstance();
			for (ModuleID module : ModuleID.values()) {
				for (LogLevel level : LogLevel.values()) {
					loggerInstance.log(
							module, 
							level, 
							"This is a call from module: " + module.toString() 
							+ "and logLevel: " + level.toString()
					);					
				}				
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;		
	}
}