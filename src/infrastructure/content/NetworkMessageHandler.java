package infrastructure.content;

import networking.INotificationHandler;
import org.json.JSONObject;
import java.util.HashMap;

/*
 * NetworkMessageHandler class implements the INotificationHandler interface.
 * this includes one method i.e @onMessageReceived()
 * this class is used to notify and to send data to the UI module 
 * as new user joined or as a user exit or as message sent by a user
 * 
 * @author Talha Yaseen (111701019)
 */
public class NetworkMessageHandler implements INotificationHandler {
	
	/*
	 * Creating instance of ContentCommunicator class to access map handler and image map
	 */
	private ContentCommunicator cc = new ContentCommunicator();
	
	/*
	 * Accessing mapHandler hashmap from ContentCommunicator class to use it for sending message to UI
	 */
	private HashMap<String, IContentNotificationHandler> mapHandler = cc.getMapHandler();
	
	/*
	 * Accessing mapImage hashmap from ContentCommunicator class to access image corresponding to username
	 */
	private HashMap<String, String> mapImage = cc.getMapImage();
	
	/*
	 * This variable will store the meta field of json string sent by the Networking module
	 */
	private String type;
	
	/*
	 * This variable will store the username of client
	 */
	private String userName;
	
	/*
	 * This variable will store the image accessed by mapImage corresponding to username 
	 */
	private String userImage;
	
	/*
	 * Getting handler of the UI module to send the data
	 */
	 private IContentNotificationHandler handler;
	 
	/*
	 * @param: json string message (Meta fields are newUser or message or userExit)
	 * if meta field is newUser, then remaining field would only be username
	 * if meta field is message, then remaining fields would be username, message and time
	 * if meta field is exitUser, then remaining field would only be username
	 * Networking module will be calling this method with json string message to send data to the content module
	 */
	 
	public void onMessageReceived(String message) {
		JSONObject jsonObject = new JSONObject(message);
		type = jsonObject.getString("meta");
		handler = mapHandler.get("UI");
		jsonObject.remove("meta");
		
		if(type.equals("newUser")) {
			handler.onNewUserJoined (jsonObject.toString());
		}
		else if(type.equals("message")) {
			userName = jsonObject.getString("username");
			userImage = mapImage.get(userName);
			jsonObject.put("image", userImage);
			handler.onMessageReceived (jsonObject.toString());
		}
		else {
			handler.onUserExit (jsonObject.toString());
		}
	}
}
