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

public class AddBookCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AddBookCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			String title = request.getParameter(PARAM_TITLE);
			String description = request.getParameter(PARAM_DESCRIPTION);
			String author = request.getParameter(PARAM_AUTHOR);
			int year = Integer.parseInt(request.getParameter(PARAM_YEAR));
			int count = Integer.parseInt(request.getParameter(PARAM_COUNT));

			Book book = new Book();
			book.setTitle(title);
			book.setDescription(description);
			book.setAuthor(author);
			book.setYear(year);
			book.setCount(count);
			bookService.create(book);
			
			request.getSession().setAttribute("successBookSubmit", true);
			response.sendRedirect(ConfigManager.getProperty(REDIRECT_BOOK_ADD));
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}