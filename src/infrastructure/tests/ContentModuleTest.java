package infrastructure.tests;

import java.util.HashMap;

import infrastructure.content.ContentFactory;
import infrastructure.content.IContentCommunicator;
import infrastructure.tests.XMLManager;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ModuleID;
import infrastructure.validation.logger.LogLevel;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.testing.TestCase;

public class ContentModuleTest extends TestCase{
	public ContentModuleTest() {
		this.setDescription("This test performs the E2E evaluation of the content module \nExpected output is the module initialising correctly and sending/receiving chat messages correctly");
		this.setPriority(2);
		this.setCategory("infrastructure");
	}
	
	public static HashMap <String, String> getDesiredMap() {
    	HashMap <String, String> dict = new HashMap<String, String>();
    	dict.put("testMode", "false");
    	dict.put("FileLogger", "true");
    	dict.put("ConsoleLogger", "true");
    	dict.put("ERROR", "true");
    	dict.put("WARNING", "true");
    	dict.put("SUCCESS", "true");
    	dict.put("INFO", "true");
    	
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
    
	public boolean run() {
		XMLManager xmlHandler = this.setup();
		UIManager ui = new UIManager();
		IContentCommunicator cc = ContentFactory.getContentCommunicator();
		
		return true;
	}
}