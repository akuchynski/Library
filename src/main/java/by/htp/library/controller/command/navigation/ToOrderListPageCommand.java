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

import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;
import by.htp.library.bean.Order;
import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.BookService;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToOrderListPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToOrderListPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private OrderService orderService = serviceFactory.getOrderService();
	private BookService bookService = serviceFactory.getBookService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			User currentUser = (User) request.getSession().getAttribute(ATTR_USER);
			List<Order> orderList = orderService.getAllOrders();
			List<Order> orderListEmpl = orderService.getOrdersByEmployeeId(currentUser.getId()) ;
			List<Book> bookList = bookService.getAllBooks();
			List<Employee> employeeList = employeeService.getAllEmployees();

			Map<Integer, Book> bookMap = bookList.stream().collect(Collectors.toMap(Book::getId, item -> item));
			Map<Integer, Employee> employeeMap = employeeList.stream()
					.collect(Collectors.toMap(Employee::getId, item -> item));

			request.setAttribute(ATTR_ORDER_LIST, orderList);
			request.setAttribute(ATTR_ORDER_LIST_EMPL, orderListEmpl);
			request.setAttribute(ATTR_BOOK_MAP, bookMap);
			request.setAttribute(ATTR_EMPLOYEE_MAP, employeeMap);

			request.getSession().setAttribute(ATTR_LAST_QUERY, request.getQueryString().toString());
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_ORDER_LIST))
					.forward(request, response);

		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}