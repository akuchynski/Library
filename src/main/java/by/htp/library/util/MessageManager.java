package by.htp.library.util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class MessageManager {
	
	public static final String PARAM_MESSAGE = "messageList";

	private ArrayList<String> messageList = new ArrayList<String>();

	public void addMessage(String message) {
		if (message != null) {
			messageList.add(message);
		}
	}

	public void saveMessages(HttpServletRequest request) {
		request.setAttribute(PARAM_MESSAGE, messageList);
	}

	public void importMessages(ArrayList<String> messages) {
		if (messages != null) {
			messageList.addAll(messages);
		}
	}
}
