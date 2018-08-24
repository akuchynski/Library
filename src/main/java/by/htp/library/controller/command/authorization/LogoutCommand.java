package by.htp.library.controller.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.controller.command.Command;
import by.htp.library.util.ConfigManager;

public class LogoutCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().invalidate();
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_INDEX));

	}
}
