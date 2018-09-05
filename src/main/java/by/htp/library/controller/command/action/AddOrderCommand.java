package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Order;
import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;
import by.htp.library.util.STATUS;

public class AddOrderCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AddOrderCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private OrderService orderService = serviceFactory.getOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			Order order = new Order();
			User user = (User) request.getSession().getAttribute(ATTR_USER);
			order.setEmplId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
			order.setBookId(Integer.parseInt(request.getParameter(PARAM_BOOK_ID)));
			order.setDays(Integer.parseInt(request.getParameter(PARAM_DAYS)));
			order.setStatus(user.getRole().equals(ROLE_ADMIN)? STATUS.DELIVERED : STATUS.WAIT);
			orderService.create(order);
			
			request.getSession().setAttribute("successOrderSubmit", true);
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_ORDER_ADD));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}