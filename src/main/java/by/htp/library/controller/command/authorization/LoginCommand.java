package by.htp.library.controller.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class LoginCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		EmployeeService employeeService = serviceFactory.getEmployeeService();

		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASS);

		try {
			if (userService.userIsExist(login, password)) {
				User currentUser = userService.getUserByLogin(login);
				session.setAttribute(ATTR_USER, currentUser);
				session.setAttribute(ATTR_USER_ID, currentUser.getId());
				session.setAttribute(ATTR_EMPLOYEE, employeeService.read(currentUser.getId()));

				if (currentUser.getRole().equals(ROLE_ADMIN)) {
					session.setAttribute(ATTR_MENU_PATH, ConfigManager.getProperty(FORWARD_ADMIN));
				} else if (currentUser.getRole().equals(ROLE_USER)) {
					session.setAttribute(ATTR_MENU_PATH, ConfigManager.getProperty(FORWARD_USER));
				} else {
			        response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		logger.info(request.getMethod() + " redirect - command : " + request.getParameter(PARAM_COMMAND_NAME));
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_DASHBOARD));
	}
}