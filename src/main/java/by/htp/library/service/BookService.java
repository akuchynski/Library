package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

public interface BookService {

	public List<Book> getAllBooks() throws ServiceException;

	public void create(Book book) throws ServiceException;

	public Book read(int id) throws ServiceException;

	public void update(int id, Book book) throws ServiceException;

	public void delete(int id) throws ServiceException;

	public List<Book> getLastBooks(int quantity) throws ServiceException;

	public List<Book> getAvailableBooks() throws ServiceException;

	public void takeOneBook(int id) throws ServiceException;

	public void putOneBook(int id) throws ServiceException;

	public List<Book> getByTitle(String title) throws ServiceException;
}
