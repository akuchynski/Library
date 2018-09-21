package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.controller.exception.ControllerException;
import by.htp.library.service.BookService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToBookEditPageCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(ToBookEditPageCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int editId = 0;
			if (request.getParameter(PARAM_NUMBER) != null) {
				editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
			}
			Book editBook = bookService.read(editId);
			request.setAttribute(ATTR_EDIT_BOOK, editBook);
			
			request.getSession().setAttribute(ATTR_LAST_QUERY, request.getQueryString().toString());
			request.getRequestDispatcher(
					request.getSession().getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_BOOK_EDIT))
					.forward(request, response);
		} catch (ServletException | ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}