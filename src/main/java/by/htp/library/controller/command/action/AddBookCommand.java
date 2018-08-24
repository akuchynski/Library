package by.htp.library.controller.command.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class AddBookCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(AddBookCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	
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
		
		try {
			bookService.create(book);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		logger.info(request.getMethod() + " redirect - command : " + request.getParameter(PARAM_COMMAND_NAME));
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_BOOK_ADD));
		
	}
}