package by.htp.library.controller.command.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class AjaxIdCheckCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(AjaxIdCheckCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		EmployeeService employeeService = serviceFactory.getEmployeeService();

		int emplId = Integer.parseInt(request.getParameter("emplId"));
		PrintWriter out = response.getWriter();

		try {
			if (userService.read(emplId).getId() != emplId && employeeService.read(emplId).getId() == emplId) {
				out.print("true");
			} else {
				out.print("false");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}