package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class ToUserEditPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToUserEditPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	UserService userService = serviceFactory.getUserService();
    	EmployeeService employeeService = serviceFactory.getEmployeeService();
    	
    	User currentUser = (User) request.getSession().getAttribute(ATTR_USER);
    	int editId = currentUser.getId();	
		if (request.getParameter(PARAM_NUMBER) != null) {
			editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
		}
		
		try {
			User editUser = userService.read(editId);
			request.setAttribute(ATTR_EDIT_USER, editUser);
			Employee editEmployee = employeeService.read(editId);
			request.setAttribute(ATTR_EDIT_EMPLOYEE, editEmployee);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_USER_EDIT)).forward(request, response);
	}
}