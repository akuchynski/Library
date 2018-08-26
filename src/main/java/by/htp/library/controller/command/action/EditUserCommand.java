package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Employee;
import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class EditUserCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(EditUserCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			User user = new User();
			Employee employee = new Employee();
			int id = Integer.parseInt(request.getParameter(PARAM_EDIT_ID));
			user.setEmail(request.getParameter(PARAM_EMAIL));
			user.setActive(Boolean.parseBoolean(request.getParameter(PARAM_ACTIVE_RADIO_BTN)));
			employee.setName(request.getParameter(PARAM_NAME));
			employee.setSurname(request.getParameter(PARAM_SURNAME));
			employee.setYear(Integer.parseInt(request.getParameter(PARAM_YEAR)));
			if (request.getParameter(PARAM_NEW_PASS).isEmpty()) {
				user.setPassword(request.getParameter(PARAM_OLD_PASS));
			} else {
				user.setPassword(request.getParameter(PARAM_NEW_PASS));
			}
			userService.update(id, user);
			employeeService.update(id, employee);
			
			request.getSession().setAttribute("messageClass", "user-update-success");
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_USER_LIST));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}