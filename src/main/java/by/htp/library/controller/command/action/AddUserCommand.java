package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class AddUserCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AddUserCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			User user = new User();
			user.setId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
			user.setLogin(request.getParameter(PARAM_LOGIN));
			user.setEmail(request.getParameter(PARAM_EMAIL));
			user.setPassword(request.getParameter(PARAM_PASS));
			userService.create(user);
			
			request.getSession().setAttribute("successUserSubmit", true);
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_USER_ADD));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}