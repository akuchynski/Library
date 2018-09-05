package by.htp.library.controller.command.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Order;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;
import by.htp.library.util.STATUS;

public class EditOrderCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(EditOrderCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private OrderService orderService = serviceFactory.getOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int id = Integer.parseInt(request.getParameter(PARAM_EDIT_ID));
			Order order = new Order();
			order.setEmplId(Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID)));
			order.setBookId(Integer.parseInt(request.getParameter(PARAM_BOOK_ID)));
			order.setDays(Integer.parseInt(request.getParameter(PARAM_DAYS)));
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				order.setDate(format.parse(request.getParameter(PARAM_DATE)));
			} catch (ParseException e) {
				logger.info("Date error");
			}
			STATUS orderStatus = STATUS.valueOf(request.getParameter(PARAM_STATUS));
			order.setStatus(orderStatus);
			orderService.update(id, order);
			
			request.getSession().setAttribute("messageClass", "order-update-success");
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_ORDER_LIST));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}