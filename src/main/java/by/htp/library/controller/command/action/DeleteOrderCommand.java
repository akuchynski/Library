package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.OrderService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class DeleteOrderCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(DeleteOrderCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private OrderService orderService = serviceFactory.getOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int id = Integer.parseInt(request.getParameter(PARAM_DELETE_ID));
			orderService.delete(id);
			
			request.getSession().setAttribute("messageClass", "order-delete-success");
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_ORDER_LIST));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}