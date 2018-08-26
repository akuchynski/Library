package by.htp.library.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.User;
import by.htp.library.util.ConfigManager;

public class AuthentificationFilter implements Filter {

	protected static final Logger logger = LoggerFactory.getLogger(AuthentificationFilter.class);

	public static final String ATTR_USER = "currentUser";
	public static final String PARAM_COMMAND = "commandName";
	public static final String LOGIN_COMMAND = "login";
	public static final String REG_PAGE_COMMAND = "to_registration";
	public static final String REG_COMMAND = "registration";
	public static final String LOGOUT_COMMAND = "logout";
	public static final String LANG_CHANGE_COMMAND = "lang_change";
	public static final String AJAX_COMMANDS = "AJAX";
	public static final String REDIRECT_INDEX = "index.jsp";
	public static final String FORWARD_DEACTIVE = "forward.deactive";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		logger.info(request.getMethod() + " : command parameter : " + request.getParameter(PARAM_COMMAND));

		String command = request.getParameter(PARAM_COMMAND);
		User currentUser = (User) request.getSession().getAttribute(ATTR_USER);

		if ((currentUser != null && currentUser.isActive()) || command.matches(LOGIN_COMMAND)
				|| command.matches(REG_PAGE_COMMAND) || command.matches(REG_COMMAND)
				|| command.matches(LANG_CHANGE_COMMAND) || command.contains(AJAX_COMMANDS)
				|| command.matches(LOGOUT_COMMAND)) {
			chain.doFilter(req, res);
		} else if (currentUser != null && !currentUser.isActive()) {
			request.getSession().invalidate();
			request.getRequestDispatcher(ConfigManager.getProperty(FORWARD_DEACTIVE)).forward(request, response);
		} else {
			response.sendRedirect(REDIRECT_INDEX);
		}
	}

	@Override
	public void destroy() {

	}
}
