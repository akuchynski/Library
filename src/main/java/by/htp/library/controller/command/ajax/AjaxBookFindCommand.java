package by.htp.library.controller.command.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

public class AjaxBookFindCommand extends Command {
	private static final Logger logger = LoggerFactory.getLogger(AjaxBookFindCommand.class);
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private BookService bookService = serviceFactory.getBookService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		logger.info(request.getMethod() + " command name : " + request.getParameter(PARAM_COMMAND_NAME));
		try {
			String title = request.getParameter("bookSearch");
			PrintWriter out = response.getWriter();
			List<Book> bookList = bookService.getByTitle(title);
			if (!bookList.isEmpty()) {
				for (Book book : bookList) {
					out.println("<option value = " + book.getId()
							+ " onclick=\"window.location.href='controller?commandName=TO_BOOK_EDIT&num=" + book.getId()
							+ "'\">" + book.getTitle() + "</option>");
				}
			}
		} catch (ServiceException | IOException e) {
			throw new ControllerException(e);
		}
	}
}