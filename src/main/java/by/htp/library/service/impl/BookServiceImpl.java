package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class BookServiceImpl implements BookService {

	private final BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();

	public BookServiceImpl() {
	}

	@Override
	public List<Book> getAllBooks() throws ServiceException {
		try {
			return bookDAO.readAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void create(Book book) throws ServiceException {
		try {
			bookDAO.create(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Book read(int id) throws ServiceException {
		try {
			return bookDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(int id, Book book) throws ServiceException {
		try {
			bookDAO.update(id, book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			bookDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Book> getLastBooks(int quantity) throws ServiceException {
		try {
			return bookDAO.readLastBooks(quantity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Book> getAvailableBooks() throws ServiceException {
		try {
			return bookDAO.readAvailableBooks();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void takeOneBook(int id) throws ServiceException {
		try {
			bookDAO.decrementBookQuantity(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void putOneBook(int id) throws ServiceException {
		try {
			bookDAO.incrementBookQuantity(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Book> getByTitle(String title) throws ServiceException {
		try {
			return bookDAO.readByTitle(title);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
