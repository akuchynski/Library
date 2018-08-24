package by.htp.library.controller.command.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Order;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;
import by.htp.library.util.STATUS;

public class EditOrderCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(EditOrderCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService bookService = serviceFactory.getBookService();
		OrderService orderService = serviceFactory.getOrderService();

		int id = Integer.parseInt(request.getParameter(PARAM_EDIT_ID));

		Order order = new Order();
		order.setEmplId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
		order.setBookId(Integer.parseInt(request.getParameter(PARAM_BOOK_ID)));
		order.setDays(Integer.parseInt(request.getParameter(PARAM_DAYS)));
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			order.setDate(format.parse(request.getParameter(PARAM_DATE)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		STATUS orderStatus = STATUS.valueOf(request.getParameter(PARAM_STATUS));
		order.setStatus(orderStatus);

		try {

			if (orderStatus.equals(STATUS.RETURNED) && !orderService.read(id).getStatus().equals(STATUS.RETURNED)) {
				bookService.putOneBook(orderService.read(id).getBookId());
			} else if ((orderStatus.equals(STATUS.WAIT) || orderStatus.equals(STATUS.DELIVERED))
					&& orderService.read(id).getStatus().equals(STATUS.RETURNED)) {
				bookService.takeOneBook(orderService.read(id).getBookId());
			}
			orderService.update(id, order);
			
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}

		response.sendRedirect(ConfigManager.getProperty(REDIRECT_ORDER_LIST));
	}
}