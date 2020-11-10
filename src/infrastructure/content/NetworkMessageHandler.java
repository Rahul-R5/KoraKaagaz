package infrastructure.content;

import networking.INotificationHandler;
import org.json.JSONObject;
import java.util.HashMap;

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
	 */
	public void onMessageReceived(String message) {
		JSONObject jsonObject = new JSONObject(message);
		metafield = jsonObject.getString("meta");
		handler = handlerMap.get("UI");
		jsonObject.remove("meta");
		username = jsonObject.getString("username");
		userimage = imageMap.get(username);
		jsonObject.put("image", userimage);
		
		if(metafield.equals("newUser")) {
			handler.onNewUserJoined(jsonObject.toString());
		}
		else if(metafield.equals("message")) {
			handler.onMessageReceived(jsonObject.toString());
		}
		else if (metafield.equals("userExit")) {
			handler.onUserExit(jsonObject.toString());
		}
		else {}
	}
}
