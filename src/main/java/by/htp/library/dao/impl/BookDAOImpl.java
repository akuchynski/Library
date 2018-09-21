package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.AbstractDAO;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class BookDAOImpl extends AbstractDAO implements BookDAO {

	private static final String SQL_CREATE_BOOK = "INSERT INTO book (title, description, author, year, count) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_READ_BOOKS = "SELECT * FROM book";
	private static final String SQL_READ_LAST_BOOKS = "SELECT * FROM book order by book_id desc limit ?";
	private static final String SQL_READ_BOOK_BY_ID = "SELECT * FROM book WHERE book_id = ?";
	private static final String SQL_READ_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ?";
	private static final String SQL_UPDATE_BOOK_BY_ID = "UPDATE book SET title = ?, description = ?, author = ?, year = ?, count = ? WHERE book_id = ?";
	private static final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE book_id = ?";

	public void create(Book entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE_BOOK);

			preparedStatement.setString(1, entity.getTitle());
			preparedStatement.setString(2, entity.getDescription());
			preparedStatement.setString(3, entity.getAuthor());
			preparedStatement.setInt(4, entity.getYear());
			preparedStatement.setInt(5, entity.getCount());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	public List<Book> readAll() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Book> books = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_BOOKS);
			resultSet = preparedStatement.executeQuery();
			books = new ArrayList<>();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setYear(resultSet.getInt(5));
				book.setCount(resultSet.getInt(6));
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return books;
	}

	public List<Book> readAvailableBooks() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Book> books = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_BOOKS);
			resultSet = preparedStatement.executeQuery();
			books = new ArrayList<>();

			while (resultSet.next()) {
				if (resultSet.getInt(6) == 0) {
					continue;
				}
				Book book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setYear(resultSet.getInt(5));
				book.setCount(resultSet.getInt(6));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return books;
	}

	public List<Book> readLastBooks(int quantity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Book> books = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_LAST_BOOKS);
			preparedStatement.setInt(1, quantity);
			resultSet = preparedStatement.executeQuery();
			books = new ArrayList<>();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setYear(resultSet.getInt(5));
				book.setCount(resultSet.getInt(6));
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return books;
	}

	public Book read(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Book book = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_BOOK_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			book = new Book();

			while (resultSet.next()) {
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setYear(resultSet.getInt(5));
				book.setCount(resultSet.getInt(6));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return book;
	}

	public List<Book> readByTitle(String title) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Book> books = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_BOOKS_BY_TITLE);
			preparedStatement.setString(1, "%" + title + "%");
			resultSet = preparedStatement.executeQuery();
			books = new ArrayList<>();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setDescription(resultSet.getString(3));
				book.setAuthor(resultSet.getString(4));
				book.setYear(resultSet.getInt(5));
				book.setCount(resultSet.getInt(6));
				books.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return books;
	}

	public void update(int id, Book entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK_BY_ID);

			preparedStatement.setString(1, entity.getTitle());
			preparedStatement.setString(2, entity.getDescription());
			preparedStatement.setString(3, entity.getAuthor());
			preparedStatement.setInt(4, entity.getYear());
			preparedStatement.setInt(5, entity.getCount());
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	public void delete(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}
}
