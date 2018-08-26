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
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToDashboardPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToDashboardPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private OrderService orderService = serviceFactory.getOrderService();
	private BookService bookService = serviceFactory.getBookService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();
	private UserService userService = serviceFactory.getUserService();
	private static final int LAST_USERS_COUNT = 3;
	private static final int LAST_BOOKS_COUNT = 5;
	private static final int LAST_ORDERS_COUNT = 5;
	private static final String ORDERS_STATUS = "WAIT";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			List<Book> bookList = bookService.getAllBooks();
			List<Employee> employeeList = employeeService.getAllEmployees();
			List<User> userList = userService.getAllUsers();
			List<User> userLastList = userService.getLastUsers(LAST_USERS_COUNT);
			List<Book> bookLastList = bookService.getLastBooks(LAST_BOOKS_COUNT);

			List<Order> orderList = orderService.getAllOrders();
			List<Order> orderListWait = orderService.getOrdersByStatus(ORDERS_STATUS);

			int emplId = (Integer) request.getSession().getAttribute(ATTR_USER_ID);
			List<Order> orderLastList = orderService.getLastOrdersByEmployeeId(emplId, LAST_ORDERS_COUNT);

			request.setAttribute(ATTR_BOOK_COUNT, bookList.size());
			request.setAttribute(ATTR_EMPLOYEE_COUNT, employeeList.size());
			request.setAttribute(ATTR_USER_COUNT, userList.size());
			request.setAttribute(ATTR_ORDER_COUNT, orderList.size());

			Map<Integer, Book> bookMap = bookList.stream().collect(Collectors.toMap(Book::getId, item -> item));
			Map<Integer, Employee> employeeMap = employeeList.stream()
					.collect(Collectors.toMap(Employee::getId, item -> item));
			Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));

			request.setAttribute(ATTR_ORDER_LIST_WAIT, orderListWait);
			request.setAttribute(ATTR_USER_LAST_LIST, userLastList);
			request.setAttribute(ATTR_BOOK_LAST_LIST, bookLastList);
			request.setAttribute(ATTR_ORDER_LAST_LIST, orderLastList);
			request.setAttribute(ATTR_BOOK_MAP, bookMap);
			request.setAttribute(ATTR_EMPLOYEE_MAP, employeeMap);
			request.setAttribute(ATTR_USER_MAP, userMap);

			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_DASHBOARD))
					.forward(request, response);

		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}