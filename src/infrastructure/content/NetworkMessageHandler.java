package infrastructure.content;

import networking.INotificationHandler;
import org.json.JSONObject;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.StringWriter;
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
	 * if meta field is newUser, then remaining field would only be username.
	 * if meta field is message, then remaining fields would be username, message and time
	 * if meta field is exitUser, then remaining field would only be username
	 * Networking module will be calling this method with json string message to send data to the content module
	 *
	 * meta field will be removed and image field will be added and then string will be sent to UI
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
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logMessage = "content: "+sw.toString();
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			metafield = jsonObject.getString("meta");
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logMessage = "content: "+sw.toString();
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			handler = handlerMap.get("UI");
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logMessage = "content: "+sw.toString();
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			username = jsonObject.getString("username");
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logMessage = "content: "+sw.toString();
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		try {
			userimage = imageMap.get(username);
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logMessage = "content: "+sw.toString();
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.ERROR, logMessage);
			return;
		}
		
		jsonObject.remove("meta");
		jsonObject.put("image", userimage);
		
		if(metafield.equals("newUser")) {
			handler.onNewUserJoined(jsonObject.toString());
			logMessage = "content: message successfully passed to UI";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.SUCCESS, logMessage);
		}
		else if(metafield.equals("message")) {
			handler.onMessageReceived(jsonObject.toString());
			logMessage = "content: message successfully passed to UI";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.SUCCESS, logMessage);
		}
		else if (metafield.equals("userExit")) {
			handler.onUserExit(jsonObject.toString());
			logMessage = "content: message successfully passed to UI";
			logger.log(ModuleID.INFRASTRUCTURE, LogLevel.SUCCESS, logMessage);
		}
		else {}
	}
}
