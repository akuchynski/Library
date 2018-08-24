package by.htp.library.service;

import by.htp.library.service.impl.BookServiceImpl;
import by.htp.library.service.impl.EmployeeServiceImpl;
import by.htp.library.service.impl.OrderServiceImpl;
import by.htp.library.service.impl.ReportServiceImpl;
import by.htp.library.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final BookService bookService = new BookServiceImpl();
	private final EmployeeService employeeService = new EmployeeServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();
	private final UserService userService = new UserServiceImpl();
	private final ReportService reportService = new ReportServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public BookService getBookService() {
		return bookService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public UserService getUserService() {
		return userService;
	}

	public ReportService getReportService() {
		return reportService;
	}
}
