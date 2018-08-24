package by.htp.library.controller.command.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class AjaxLoginCheckCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(AjaxLoginCheckCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		String login = request.getParameter("loginname");
		PrintWriter out = response.getWriter();

		try {
			if (userService.getUserByLogin(login).getLogin() != null) {
				out.print("false");
			} else {
				out.print("true");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}