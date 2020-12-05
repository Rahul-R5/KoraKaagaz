package infrastructure.tests;

import java.util.HashMap;

import infrastructure.tests.XMLManager;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ModuleID;
import infrastructure.validation.logger.LogLevel;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.testing.TestCase;


public class LoggerTestModeTest extends TestCase {
	public LoggerTestModeTest(){
		this.setDescription("This test outputs to a console all combinations of module and log level. Expected output is modules x log levels number of lines being printed to console");
		this.setPriority(1);
		this.setCategory("infrastructure");
		
	}
	
    public static HashMap <String, String> getDesiredMap() {
    	HashMap <String, String> dict = new HashMap<String, String>();
    	dict.put("testMode", "true");
    	dict.put("FileLogger", "false");
    	dict.put("ConsoleLogger", "false");
    	dict.put("ERROR", "false");
    	dict.put("WARNING", "false");
    	dict.put("SUCCESS", "false");
    	dict.put("INFO", "false");
    	
        return dict;
    }

    public XMLManager setup() {
    	String xmlFilePath;
    	try {
			String home = System.getProperty("user.home");
			xmlFilePath = home+"/.config/KoraKaagaz/infrastructure_logger.xml";
		} catch (SecurityException se) {
			xmlFilePath = "resources/infrastructure_logger.xml";
		} 
		HashMap <String, String> DesiredMap = getDesiredMap();
		
		return new XMLManager(xmlFilePath, DesiredMap);
    }
    
	public boolean run(){
		
		
		XMLManager xmlHandler = this.setup();
		ILogger loggerInstance;
		try {
			loggerInstance = LoggerFactory.getLoggerInstance();
			for (ModuleID module : ModuleID.values()) {
				for (LogLevel level : LogLevel.values()) {
					loggerInstance.log(
							module, 
							level, 
							"This is a call from module: " + module.toString() 
							+ " and logLevel: " + level.toString()
					);					
				}				
			}
			
			
		}
		catch(Exception e) {
			System.err.println("Caught Exception:" + e.getMessage());
			xmlHandler.resetLoggerXML();
			return false;
		}
		xmlHandler.resetLoggerXML();
		return true;		
	}
}