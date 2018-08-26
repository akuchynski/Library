package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class BookDAOImpl implements BookDAO {

	private static final String SQL_CREATE_BOOK = "INSERT INTO book (title, description, author, year, count) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_READ_BOOKS = "SELECT * FROM book";
	private static final String SQL_READ_LAST_BOOKS = "SELECT * FROM book order by book_id desc limit ?";
	private static final String SQL_READ_BOOK_BY_ID = "SELECT * FROM book WHERE book_id = ?";
	private static final String SQL_READ_BOOKS_BY_TITLE = "SELECT * FROM book WHERE title LIKE ?";
	private static final String SQL_INCREMENT_BOOK_QUANTITY_BY_ID = "UPDATE book SET count = count + 1 WHERE book_id = ?";
	private static final String SQL_DECREMENT_BOOK_QUANTITY_BY_ID = "UPDATE book SET count = count - 1 WHERE book_id = ?";
	private static final String SQL_UPDATE_BOOK_BY_ID = "UPDATE book SET title = ?, description = ?, author = ?, year = ?, count = ? WHERE book_id = ?";
	private static final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE book_id = ?";

	public void create(Book entity) throws DAOException {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_CREATE_BOOK);

			ps.setString(1, entity.getTitle());
			ps.setString(2, entity.getDescription());
			ps.setString(3, entity.getAuthor());
			ps.setInt(4, entity.getYear());
			ps.setInt(5, entity.getCount());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);
	}

	public List<Book> readAll() throws DAOException {

		Connection connection = ConnectionPool.getConnection();
		List<Book> books = new ArrayList<>();

		try {

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_READ_BOOKS);

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setCount(rs.getInt("count"));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return books;
	}
	
	public List<Book> readAvailableBooks() throws DAOException {

		Connection connection = ConnectionPool.getConnection();
		List<Book> books = new ArrayList<>();

		try {

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_READ_BOOKS);

			while (rs.next()) {
				if(rs.getInt("count") == 0) {
					continue;
				}
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setCount(rs.getInt("count"));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return books;
	}

	public List<Book> readLastBooks(int quantity) throws DAOException {

		Connection connection = ConnectionPool.getConnection();
		List<Book> books = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_LAST_BOOKS);
			ps.setInt(1, quantity);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setCount(rs.getInt("count"));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return books;
	}

	public Book read(int id) throws DAOException {

		Connection connection = ConnectionPool.getConnection();
		Book book = new Book();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_BOOK_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setCount(rs.getInt("count"));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return book;
	}

	public List<Book> readByTitle(String title) throws DAOException {

		Connection connection = ConnectionPool.getConnection();
		List<Book> books = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_BOOKS_BY_TITLE);
			ps.setString(1, "%" + title + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setCount(rs.getInt("count"));
				books.add(book);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return books;
	}
	
	@Override
	public void incrementBookQuantity(int id) throws DAOException {
		
		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_INCREMENT_BOOK_QUANTITY_BY_ID);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);
		
	}

	@Override
	public void decrementBookQuantity(int id) throws DAOException {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_DECREMENT_BOOK_QUANTITY_BY_ID);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);
		
	}

	public void update(int id, Book entity) throws DAOException {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BOOK_BY_ID);

			ps.setString(1, entity.getTitle());
			ps.setString(2, entity.getDescription());
			ps.setString(3, entity.getAuthor());
			ps.setInt(4, entity.getYear());
			ps.setInt(5, entity.getCount());
			ps.setInt(6, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);
	}

	public void delete(int id) throws DAOException {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK_BY_ID);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);
	}
}
