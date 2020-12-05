package infrastructure.tests;

import org.json.JSONObject;

import infrastructure.content.IContentCommunicator;
import infrastructure.content.IContentNotificationHandler;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.logger.LogLevel;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ModuleID;

public class UIManager {
	private IContentCommunicator cc;
	private String username;
	private String ipAddress;
	private String image;
	private IContentNotificationHandler uiHandler;
	private ILogger logger;
	
	
	public UIManager() {
		this.cc = TestContentFactory.getTestContentCommunicator();
		this.username = "Rahul R";
		this.ipAddress = "192.168.1.1";
		this.image = "Some random image";
		this.logger = LoggerFactory.getLoggerInstance();
	}
	
	public JSONObject getUserDetails() {
		JSONObject jsonObject;
		jsonObject = new JSONObject();
		jsonObject.put("username", this.username);
		jsonObject.put("ipAddress", this.ipAddress);
		jsonObject.put("image", this.image);
		
		return jsonObject;
	}
	
	public void startContentCommunicator() {
		IContentCommunicator cc = this.cc;
		JSONObject userDetails = this.getUserDetails();
		
		cc.initialiseUser(userDetails.toString());
		
		this.uiHandler = new UIHandler();
		cc.subscribeForNotifications("UI", this.uiHandler);
	}
	public void sendManyMessages(int iterations, int delay, String message) {
		IContentCommunicator cc = this.cc;
		for(int i = 0; i < iterations; i++) {
			cc.sendMessageToContent(message);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				logger.log(
						ModuleID.TEST, 
						LogLevel.WARNING, 
						"Caught Interrupted Exception" + e.getMessage()
				);
			}
		}
		
	}
	public void sendOneMessage(String message) {
		IContentCommunicator cc = this.cc;
		cc.sendMessageToContent(message);
	}
	
	public void stopContentCommunicator() {
		IContentCommunicator cc = this.cc;
		cc.notifyUserExit();
	}
}