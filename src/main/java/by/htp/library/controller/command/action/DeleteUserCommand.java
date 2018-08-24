package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class DeleteUserCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(DeleteUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	UserService userService = serviceFactory.getUserService();
  
    	int id = Integer.parseInt(request.getParameter(PARAM_DELETE_ID));
    	
		try {
			userService.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_USER_LIST));
	}
}