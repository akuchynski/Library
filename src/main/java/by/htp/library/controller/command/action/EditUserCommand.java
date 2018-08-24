package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Employee;
import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class EditUserCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(EditUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	UserService userService = serviceFactory.getUserService();
    	EmployeeService employeeService = serviceFactory.getEmployeeService();
    	
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
		
		try {
			userService.update(id, user);
			employeeService.update(id, employee);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_USER_LIST));
	}
}