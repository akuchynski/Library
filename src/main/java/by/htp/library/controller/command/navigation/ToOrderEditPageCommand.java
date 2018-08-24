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
import by.htp.library.bean.Order;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToOrderEditPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToOrderEditPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	EmployeeService employeeService = serviceFactory.getEmployeeService();
    	OrderService orderService = serviceFactory.getOrderService();
    	
		int editId = 0;
		if (request.getParameter(PARAM_NUMBER) != null) {
			editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
		}
		request.setAttribute(ATTR_EDIT_ID, editId);
    	
		try {
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
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
	
		request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_ORDER_EDIT)).forward(request, response);
	}
}