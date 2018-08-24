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
import by.htp.library.service.ReportService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToReportPageCommand extends Command {
	private static final int DEFAULT_BOOK_COUNT = 2;
	
	protected static final Logger logger = LoggerFactory.getLogger(ToReportPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	EmployeeService employeeService = serviceFactory.getEmployeeService();
    	UserService userService = serviceFactory.getUserService();
    	ReportService reportService = serviceFactory.getReportService();
    	
		Map<Integer, Integer> emplBooksReport = reportService.getEmployeesBooks(DEFAULT_BOOK_COUNT);
		Map<Integer, Integer> emplBooksDelayReport = reportService.getEmployeesBooksDelay(DEFAULT_BOOK_COUNT);
		
		try {
			
			List<Employee> employeeList = employeeService.getAllEmployees();
			List<User> userList = userService.getAllUsers();
			
			Map<Integer, Employee> employeeMap = employeeList.stream()
					.collect(Collectors.toMap(Employee::getId, item -> item));
			Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
			
			session.setAttribute(ATTR_BOOKS_REPORT, emplBooksReport);
			session.setAttribute(ATTR_BOOKS_DELAY_REPORT, emplBooksDelayReport);
			session.setAttribute(ATTR_EMPLOYEE_MAP, employeeMap);
			session.setAttribute(ATTR_USER_MAP, userMap);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
    	request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_REPORT)).forward(request, response);
	}
}