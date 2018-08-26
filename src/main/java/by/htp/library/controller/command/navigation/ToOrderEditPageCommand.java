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
import by.htp.library.bean.Order;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.BookService;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToOrderEditPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToOrderEditPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();
	private OrderService orderService = serviceFactory.getOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int editId = 0;
			if (request.getParameter(PARAM_NUMBER) != null) {
				editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
			}
			request.setAttribute(ATTR_EDIT_ID, editId);

			List<Book> bookList = bookService.getAvailableBooks();
			List<Employee> employeeList = employeeService.getAllEmployees();
			Order editOrder = orderService.read(editId);
			Book editBook = bookService.read(editOrder.getBookId());
			Employee editEmployee = employeeService.read(editOrder.getEmplId());
			
			request.setAttribute(ATTR_BOOK_LIST, bookList);
			request.setAttribute(ATTR_EMPLOYEE_LIST, employeeList);
			request.setAttribute(ATTR_EDIT_ORDER, editOrder);
			request.setAttribute(ATTR_EDIT_BOOK, editBook);
			request.setAttribute(ATTR_EDIT_EMPLOYEE, editEmployee);
			
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_ORDER_EDIT))
					.forward(request, response);
		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}