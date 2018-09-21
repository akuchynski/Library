package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.util.ConfigManager;

public class ChangeLanguageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ChangeLanguageCommand.class);
	private static final String ATTR_LANGUAGE = "local";
	private static final String PARAM_LOCAL = "local";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			request.getSession().setAttribute(ATTR_LANGUAGE, request.getParameter(PARAM_LOCAL));
			String lastQuery = String.valueOf(request.getSession().getAttribute(ATTR_LAST_QUERY));
			if(lastQuery.equals("null")) {
				response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
			} else {
				response.sendRedirect(CONTROLLER_QUERY + lastQuery);
			}
		} catch (IOException e) {
			throw new ControllerException(e);
		}
	}
}
