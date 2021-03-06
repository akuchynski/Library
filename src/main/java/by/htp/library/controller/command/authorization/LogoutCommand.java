package by.htp.library.controller.command.authorization;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.util.ConfigManager;

public class LogoutCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(LogoutCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			request.getSession().invalidate();
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
		} catch (IOException e) {
			throw new ControllerException(e);
		}
	}
}