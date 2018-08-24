package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.DAOFactory;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService {

	private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();

	public BookServiceImpl() {
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDAO.readAll();
	}

	@Override
	public void create(Book book) {
		bookDAO.create(book);
	}

	@Override
	public Book read(int id) {
		return bookDAO.read(id);
	}

	@Override
	public void update(int id, Book book) {
		bookDAO.update(id, book);
	}

	@Override
	public void delete(int id) {
		bookDAO.delete(id);
	}

	@Override
	public List<Book> getLastBooks(int quantity) throws ServiceException {
		return bookDAO.readLastBooks(quantity);
	}

	@Override
	public List<Book> getAvailableBooks() throws ServiceException {
		return bookDAO.readAvailableBooks();
	}

	@Override
	public void takeOneBook(int id) throws ServiceException {
		bookDAO.decrementBookQuantity(id);
	}

	@Override
	public void putOneBook(int id) throws ServiceException {
		bookDAO.incrementBookQuantity(id);
	}

	@Override
	public List<Book> getByTitle(String title) {
		return bookDAO.readByTitle(title);
	}
}
