package infrastructure.content;

import networking.INotificationHandler;
import org.json.JSONObject;
import java.util.HashMap;
import infrastructure.validation.logger.ILogger;
import infrastructure.validation.logger.LogLevel;
import infrastructure.validation.logger.LoggerFactory;
import infrastructure.validation.logger.ModuleID;

/**
 * NetworkMessageHandler class implements the INotificationHandler interface.
 * this includes one method i.e @onMessageReceived()
 * this class is used to notify and to send data to the UI module 
 * as new user joined or as a user exit or as message sent by a user
 * 
 * @author Talha Yaseen
 */
public class NetworkMessageHandler implements INotificationHandler {
	
	/**
	 * Creating instance of ContentCommunicator class to access handler map and image map
	 */
	private ContentCommunicator cc = ContentFactory.getContentCommunicator();
	
	/**
	* Creating instance of class which implements ILogger interface
	*/
	private ILogger logger = LoggerFactory.getLoggerInstance();
	
	/**
	 * Accessing handlerMap hashmap from ContentCommunicator class to use it for sending message to UI
	 */
	private HashMap<String, IContentNotificationHandler> handlerMap = cc.getHandlerMap();
	
	/**
	 * Accessing imageMap hashmap from ContentCommunicator class to access image corresponding to username
	 */
	private HashMap<String, String> imageMap = cc.getImageMap();
	
	/**
	 * This variable will store the meta field of json string sent by the Networking module
	 */
	private String metafield;
	
	/**
	 * This variable will store the username of client
	 */
	private String username;
	
	/**
	 * This variable will store the image accessed by imageMap corresponding to username 
	 */
	private String userimage;
	
	/**
	* This variable will store a string which will contains log message that to be passed to log method of logger
	*/
	private String logMessage;
	
	/**
	 * Getting handler of the UI module to send the data
	 */
	private IContentNotificationHandler handler;
	 
	/**
	 * @param String message - JSON string message (Meta fields are newUser or message or userExit)
	 *
	 * if meta field is newUser, then remaining field of parameter message would only be username,  
	 * to send this message to UI removing metafield, adding image and then calling @onNewUserJoined() method
	 * if meta field is message, then remaining fields of parameter message would be username, message and time, 
	 * to send this message to UI removing metafield, adding image and then calling @onMessageReceived() method
	 * if meta field is exitUser, then remaining field of parameter message would only be username, 
	 * to send this message to UI removing metafield, adding image and then calling @onNewUserJoined() method
	 * Networking module will be calling this method with json string message to send data to the content module
	 *
	 * creating error log message if message in not converting into json object,
	 * or if not able extract field from object, or if not able to extract handler from hashmap (handlerMap)
	 * if message will be successfully sent to UI, then creating success log message
	 */
	@override
	public void onMessageReceived(String message) {
		try {
			JSONObject jsonObject = new JSONObject(message);
		} 
		catch(Exception e) {
			logMessage = "converting JSON string to JSON object process failed";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			metafield = jsonObject.getString("meta");
		}
		catch(Exception e) {
			logMessage = "failed to get metafield data of message parameter";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			handler = handlerMap.get("UI");
		}
		catch(Exception e) {
			logMessage = "failed to get IContentNotification handler from handlerMap";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			username = jsonObject.getString("username");
		}
		catch(Exception e) {
			logMessage = "failed to get username field data of message parameter";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			userimage = imageMap.get(username);
		}
		catch(Exception e) {
			logMessage = "failed to get image from imageMap";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		jsonObject.remove("meta");
		jsonObject.put("image", userimage);
		
		if(metafield.equals("newUser")) {
			handler.onNewUserJoined(jsonObject.toString());
			logMessage = "onNewUserJoined() called";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.INFO, logMessage);
		}
		else if(metafield.equals("message")) {
			handler.onMessageReceived(jsonObject.toString());
			logMessage = "onMessageReceived() called";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.INFO, logMessage);
		}
		else if (metafield.equals("userExit")) {
			handler.onUserExit(jsonObject.toString());
			logMessage = "onUserExit() called";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.INFO, logMessage);
		}
		else {
			logMessage = "no method with this name exists";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
		}
	}
}
