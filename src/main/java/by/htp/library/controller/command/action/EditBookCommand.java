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

public class EditBookCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(EditBookCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	
    	int id = Integer.parseInt(request.getParameter(PARAM_EDIT_ID));
    	
		Book book = new Book();
		book.setTitle(request.getParameter(PARAM_TITLE));
		book.setDescription(request.getParameter(PARAM_DESCRIPTION));
		book.setAuthor(request.getParameter(PARAM_AUTHOR));
		book.setYear(Integer.parseInt(request.getParameter(PARAM_YEAR)));
		book.setCount(Integer.parseInt(request.getParameter(PARAM_COUNT)));
		
		try {
			bookService.update(id, book);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(ConfigManager.getProperty(REDIRECT_BOOK_LIST));
	}
}