package by.htp.library.controller.command.navigation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;

public class ToPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	EmployeeService employeeService = serviceFactory.getEmployeeService();
		
		try {
			List<Book> bookList = bookService.getAllBooks();
			List<Employee> employeeList = employeeService.getAllEmployees();
			request.setAttribute(ATTR_BOOK_LIST, bookList);
			request.setAttribute(ATTR_EMPLOYEE_LIST, employeeList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		logger.info(request.getMethod() + " forward - command : " + request.getParameter(PARAM_COMMAND_NAME) + " - page : " + request.getParameter(PARAM_PAGE_NAME));
		request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + request.getParameter(PARAM_PAGE_NAME) + JSP).forward(request, response);
	}
}