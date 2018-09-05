package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;

public interface BookDAO extends BaseDAO<Book> {
	
	public List<Book> readAvailableBooks() throws DAOException;
	
	public List<Book> readLastBooks(int quantity) throws DAOException;
	
	public List<Book> readByTitle(String title) throws DAOException;

}
