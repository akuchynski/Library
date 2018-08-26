package by.htp.library.controller.command.action;

import java.io.IOException;

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

public class EditBookCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(EditBookCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			int id = Integer.parseInt(request.getParameter(PARAM_EDIT_ID));
			Book book = new Book();
			book.setTitle(request.getParameter(PARAM_TITLE));
			book.setDescription(request.getParameter(PARAM_DESCRIPTION));
			book.setAuthor(request.getParameter(PARAM_AUTHOR));
			book.setYear(Integer.parseInt(request.getParameter(PARAM_YEAR)));
			book.setCount(Integer.parseInt(request.getParameter(PARAM_COUNT)));
			bookService.update(id, book);

			request.getSession().setAttribute("messageClass", "book-update-success");
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_BOOK_LIST));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}