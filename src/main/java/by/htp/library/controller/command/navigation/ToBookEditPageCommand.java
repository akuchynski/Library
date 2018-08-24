package by.htp.library.controller.command.navigation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.util.ConfigManager;

public class ToBookEditPageCommand extends Command {
	protected static final Logger logger = LoggerFactory.getLogger(ToBookEditPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
    	BookService bookService = serviceFactory.getBookService();
    	
    	int editId = 0;
		if (request.getParameter(PARAM_NUMBER) != null) {
			editId = Integer.parseInt(request.getParameter(PARAM_NUMBER));
		}
		
		try {
			Book editBook = bookService.read(editId);
			request.setAttribute(ATTR_EDIT_BOOK, editBook);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(session.getAttribute(ATTR_MENU_PATH) + ConfigManager.getProperty(FORWARD_BOOK_EDIT)).forward(request, response);
	}
}