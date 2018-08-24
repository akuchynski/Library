package by.htp.library.controller.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.bean.User;
import by.htp.library.controller.command.Command;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class RegistrationCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		EmployeeService employeeService = serviceFactory.getEmployeeService();

		Integer emplId = Integer.parseInt(request.getParameter(PARAM_EMPLOYEE_ID));

		String login = request.getParameter(PARAM_LOGIN);
		String email = request.getParameter(PARAM_EMAIL);
		String password = request.getParameter(PARAM_PASS);

		User user = new User();
		
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);

		try {
			user.setId(employeeService.read(emplId).getId());
			userService.create(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	
        response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));
    }
}
