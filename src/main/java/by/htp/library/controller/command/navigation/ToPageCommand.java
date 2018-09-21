package by.htp.library.controller.command.navigation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.BookService;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;

public class ToPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			List<Book> bookList = bookService.getAllBooks();
			List<Employee> employeeList = employeeService.getAllEmployees();
			List<Employee> employeeNotRegList = employeeService.getNotRegisteredEmployees();
			request.setAttribute(ATTR_BOOK_LIST, bookList);
			request.setAttribute(ATTR_EMPLOYEE_LIST, employeeList);
			request.setAttribute(ATTR_EMPLOYEE_NOT_REG_LIST, employeeNotRegList);
			
			request.getSession().setAttribute(ATTR_LAST_QUERY, request.getQueryString().toString());
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + request.getParameter(PARAM_PAGE_NAME) + JSP)
					.forward(request, response);
		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}