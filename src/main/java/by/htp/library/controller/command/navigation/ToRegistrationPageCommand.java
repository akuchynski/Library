package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.util.ConfigManager;

public class ToRegistrationPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToRegistrationPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(request.getMethod() + " forward - command : " + request.getParameter(PARAM_COMMAND_NAME));
		request.getRequestDispatcher(ConfigManager.getProperty(FORWARD_REGISTRATION)).forward(request, response);
	}
}