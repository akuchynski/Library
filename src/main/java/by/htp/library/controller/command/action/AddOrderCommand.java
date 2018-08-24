package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Order;
import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;
import by.htp.library.util.STATUS;

public class AddOrderCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(AddOrderCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	OrderService orderService = serviceFactory.getOrderService();
    	
    	Order order = new Order();
		User user = (User) session.getAttribute(ATTR_USER);
		String role = user.getRole();
		
		STATUS status = STATUS.WAIT;
		if (role.equals(ROLE_ADMIN)) {
			status = STATUS.DELIVERED;
			order.setEmplId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
		} else if (role.equals(ROLE_USER)) {
			order.setEmplId(user.getId());
		}

		order.setBookId(Integer.parseInt(request.getParameter(PARAM_BOOK_ID)));
		order.setDays(Integer.parseInt(request.getParameter(PARAM_DAYS)));
		order.setStatus(status);

		try {
			orderService.create(order);
			bookService.takeOneBook(order.getBookId());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		logger.info(request.getMethod() + " redirect - command : " + request.getParameter(PARAM_COMMAND_NAME));
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_ORDER_ADD));
		
	}
}