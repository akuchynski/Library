package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.util.ConfigManager;

public class ToErrorPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToErrorPageCommand.class);
	private String message;

	public ToErrorPageCommand(String message) {
		this.message = message;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			getMessages().addMessage(message);
			getMessages().saveMessages(request);
			request.getRequestDispatcher(ConfigManager.getProperty(FORWARD_ERROR)).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}
}
