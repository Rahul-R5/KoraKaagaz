import networking.INotificationHandler;
import org.json.*; 

/*
 * NetworkMessageHandler class includes one method i.e @onMessageReceived()
 */
public class NetworkMessageHandler implements INotificationHandler {
	
	/*
	 * @param: json string message (Meta fields are newUser or message or userExit)
	 * if meta field is newUser, then remaining field would only be username
	 * if meta field is actualMsg, then remaining fields would be username, msg and time
	 * if meta field is exitUser, then remaining field would only be username
	 * Networking module will be calling this method with json string message to send data to the content module
	 */
	public void onMessageReceived(String message) {
		JSONObject message = new JSONObject(message);
		String type = message.getString("type");
		
		if(type.equals("newUser")) {
			String username = message.getString("username");
		}
		else if(type.equals("actualMsg")) {
			String username = message.getString("username");
			String msg = message.getString("msg");
			String time = message.getString("time");
		}
		else {
			String username = message.getString("username");
		}
	}
}
