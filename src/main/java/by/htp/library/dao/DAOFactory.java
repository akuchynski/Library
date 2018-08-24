package by.htp.library.dao;

import by.htp.library.dao.impl.BookDAOImpl;
import by.htp.library.dao.impl.EmployeeDAOImpl;
import by.htp.library.dao.impl.OrderDAOImpl;
import by.htp.library.dao.impl.ReportDAOImpl;
import by.htp.library.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();

	private final UserDAO userDAO = new UserDAOImpl();
	private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private final BookDAO bookDAO = new BookDAOImpl();
	private final OrderDAO orderDAO = new OrderDAOImpl();
	private final ReportDAO reportDAO = new ReportDAOImpl();

	private DAOFactory() {
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	
	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}
}
