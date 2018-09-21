package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
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

public class ToUserEditPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToUserEditPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			User currentUser = (User) request.getSession().getAttribute(ATTR_USER);
			int editId = currentUser.getId();
			if (request.getParameter(PARAM_NUMBER) != null) {
				editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
			}

			User editUser = userService.read(editId);
			request.setAttribute(ATTR_EDIT_USER, editUser);
			Employee editEmployee = employeeService.read(editId);
			request.setAttribute(ATTR_EDIT_EMPLOYEE, editEmployee);

			request.getSession().setAttribute(ATTR_LAST_QUERY, request.getQueryString().toString());
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_USER_EDIT))
					.forward(request, response);
		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}