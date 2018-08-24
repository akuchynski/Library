package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class AddUserCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(AddUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	UserService userService = serviceFactory.getUserService();
    	
		User user = new User();
		user.setId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
		user.setLogin(request.getParameter(PARAM_LOGIN));
		user.setEmail(request.getParameter(PARAM_EMAIL));
		user.setPassword(request.getParameter(PARAM_PASS));
		
		try {
			userService.create(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		logger.info(request.getMethod() + " redirect - command : " + request.getParameter(PARAM_COMMAND_NAME));
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_USER_LIST));
		
	}
}