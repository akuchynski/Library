package by.htp.library.controller.command.authorization;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class RegistrationCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int emplId = Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID));
			String login = request.getParameter(PARAM_LOGIN);
			String email = request.getParameter(PARAM_EMAIL);
			String password = request.getParameter(PARAM_PASS);

			User user = new User();
			user.setLogin(login);
			user.setEmail(email);
			user.setPassword(password);
			user.setId(employeeService.read(emplId).getId());
			userService.create(user);
			
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}
