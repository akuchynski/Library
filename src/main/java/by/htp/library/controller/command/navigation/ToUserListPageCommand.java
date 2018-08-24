package by.htp.library.controller.command.navigation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

public class ToUserListPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToUserListPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		EmployeeService employeeService = serviceFactory.getEmployeeService();

		try {
			List<User> userList = userService.getAllUsers();
			List<Employee> employeeList = employeeService.getAllEmployees();
			Map<Integer, Employee> employeeMap = employeeList.stream().collect(Collectors.toMap(Employee::getId, item -> item));
			request.setAttribute(ATTR_EMPLOYEE_MAP, employeeMap);
			request.setAttribute(ATTR_USER_LIST, userList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_USER_LIST)).forward(request, response);
	}
}