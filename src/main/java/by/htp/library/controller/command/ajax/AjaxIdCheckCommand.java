package by.htp.library.controller.command.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class AjaxIdCheckCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AjaxIdCheckCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	private EmployeeService employeeService = serviceFactory.getEmployeeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int emplId = Integer.parseInt(request.getParameter("emplId"));
			PrintWriter out = response.getWriter();
			
			if (userService.read(emplId).getId() != emplId && employeeService.read(emplId).getId() == emplId) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}