package by.htp.library.controller.command.authorization;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class LoginCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();
	private static final String AVATAR_DIR = "assets\\images\\users\\";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			HttpSession session = request.getSession();
			String login = request.getParameter(PARAM_LOGIN);
			String password = request.getParameter(PARAM_PASS);
			User currentUser = userService.getUserByLoginPassword(login, password);

			if (currentUser == null) {
				request.getSession().setAttribute("errorLogin", true);
				response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
			} else {
				session.setAttribute(ATTR_USER_ID, currentUser.getId());
				session.setAttribute(ATTR_USER, currentUser);
				session.setAttribute(ATTR_EMPLOYEE, employeeService.read(currentUser.getId()));
				
				String appPath = request.getServletContext().getRealPath("");
				String avatarPath = appPath + AVATAR_DIR + "avatar" + currentUser.getId() + ".jpg";
				File file = new File(avatarPath);
				
				if (file.exists() && file.isFile()) {
					session.setAttribute("avatarNumber", currentUser.getId());
				} else {
					session.setAttribute("avatarNumber", 0);
				}
				
				if (currentUser.getRole().equals(ROLE_ADMIN)) {
					session.setAttribute(ATTR_MENU_PATH, ConfigManager.getProperty(FORWARD_ADMIN));
				} else if (currentUser.getRole().equals(ROLE_USER)) {
					session.setAttribute(ATTR_MENU_PATH, ConfigManager.getProperty(FORWARD_USER));
				} else {
					response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
				}
				response.sendRedirect(ConfigManager.getProperty(REDIRECT_DASHBOARD));
			}
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}