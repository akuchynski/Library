package by.htp.library.controller.command.navigation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import by.htp.library.service.ReportService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToReportPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToReportPageCommand.class);
	private static final int DEFAULT_BOOK_COUNT = 2;
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();
	private UserService userService = serviceFactory.getUserService();
	private ReportService reportService = serviceFactory.getReportService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			Map<Integer, Integer> emplBooksReport = reportService.getEmployeesBooks(DEFAULT_BOOK_COUNT);
			Map<Integer, Integer> emplBooksDelayReport = reportService.getEmployeesBooksDelay(DEFAULT_BOOK_COUNT);

			List<Employee> employeeList = employeeService.getAllEmployees();
			List<User> userList = userService.getAllUsers();

			Map<Integer, Employee> employeeMap = employeeList.stream()
					.collect(Collectors.toMap(Employee::getId, item -> item));
			Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));

			request.setAttribute(ATTR_BOOKS_REPORT, emplBooksReport);
			request.setAttribute(ATTR_BOOKS_DELAY_REPORT, emplBooksDelayReport);
			request.setAttribute(ATTR_EMPLOYEE_MAP, employeeMap);
			request.setAttribute(ATTR_USER_MAP, userMap);

			request.getSession().setAttribute(ATTR_LAST_QUERY, request.getQueryString().toString());
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_REPORT))
					.forward(request, response);
		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}