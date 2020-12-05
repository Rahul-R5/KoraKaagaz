package infrastructure.tests;

import infrastructure.content.IContentCommunicator;
import infrastructure.content.IContentNotificationHandler;

public class UITestContainer {

	private UIManager manager;
	private IContentNotificationHandler handler;
	
	public UITestContainer() {
		this.manager = new UIManager();
		this.handler = new UIHandler();
	}
	public boolean verifySentMessage() {
		UIManager manager = this.manager;
		IContentNotificationHandler handler = this.handler;
		
		if(handler.)
	}
}