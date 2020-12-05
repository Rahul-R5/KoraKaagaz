package infrastructure.tests;

import org.json.JSONObject;

import infrastructure.content.IContentCommunicator;
import infrastructure.content.IContentNotificationHandler;

public class UIHandler implements IContentNotificationHandler{
	private IContentCommunicator cc;
	private String username;
	private String ipAddress;
	private String image;
	
	public UIHandler() {
		this.cc = TestContentFactory.getTestContentCommunicator();
		this.username = "Rahul R";
		this.ipAddress = "192.168.1.1";
		this.image = "Some random image";
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
	}
	public void sendOneMessage(String message) {
		IContentCommunicator cc = this.cc;
		cc.sendMessageToContent(message);
	}

	@Override
	public void onNewUserJoined(String username) {
		IContentCommunicator cc = this.cc;
		if(cc.user)
		
	}

	@Override
	public void onMessageReceived(String messageDetails) {
		this.receivedMessage = 
		
	}

	@Override
	public void onUserExit(String username) {
		// TODO Auto-generated method stub
		
	}
}