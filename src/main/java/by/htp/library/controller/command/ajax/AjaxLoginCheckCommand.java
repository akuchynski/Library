package by.htp.library.controller.command.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class AjaxLoginCheckCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AjaxLoginCheckCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			String login = request.getParameter("loginname");
			PrintWriter out = response.getWriter();
			
			if (userService.getUserByLogin(login).getLogin() != null) {
				out.print("false");
			} else {
				out.print("true");
			}
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}