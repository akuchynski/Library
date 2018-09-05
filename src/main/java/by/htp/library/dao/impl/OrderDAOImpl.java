package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import by.htp.library.bean.Order;
import by.htp.library.dao.AbstractDAO;
import by.htp.library.dao.OrderDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;
import by.htp.library.util.STATUS;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

	private static final String SQL_CREATE_ORDER = "INSERT INTO employee_book (book_id, employee_id, days, date, status) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_READ_ORDERS = "SELECT * FROM employee_book";
	private static final String SQL_READ_ORDER_BY_ID = "SELECT * FROM employee_book WHERE order_id = ?";
	private static final String SQL_READ_ORDERS_BY_STATUS = "SELECT * FROM employee_book WHERE status = ? order by order_id desc";
	private static final String SQL_READ_ORDERS_BY_EMPLOYEE_ID = "SELECT * FROM employee_book WHERE employee_id = ?";
	private static final String SQL_READ_LAST_ORDERS_BY_EMPLOYEE_ID = "SELECT * FROM employee_book WHERE employee_id = ? order by order_id desc limit ?";
	private static final String SQL_UPDATE_ORDER_BY_ID = "UPDATE employee_book SET book_id = ?, employee_id = ?, days = ?, date = ?, status = ? WHERE order_id = ?";
	private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM employee_book WHERE order_id = ?";
	private static final String SQL_INCREMENT_BOOK_QUANTITY_BY_ID = "UPDATE book SET count = count + 1 WHERE book_id = ?";
	private static final String SQL_DECREMENT_BOOK_QUANTITY_BY_ID = "UPDATE book SET count = count - 1 WHERE book_id = ?";

	@Override
	public void create(Order entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER);
			preparedStatement.setInt(1, entity.getBookId());
			preparedStatement.setInt(2, entity.getEmplId());
			preparedStatement.setInt(3, entity.getDays());
			Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+3"));
			preparedStatement.setDate(4, Date.valueOf(LocalDate.now()), calendar);
			preparedStatement.setString(5, entity.getStatus().name());
			preparedStatement.executeUpdate();
			close(preparedStatement);

			preparedStatement = connection.prepareStatement(SQL_DECREMENT_BOOK_QUANTITY_BY_ID);
			preparedStatement.setInt(1, entity.getBookId());
			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException("Rollback error", ex);
			}
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public Order read(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Order order = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_ORDER_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			order = new Order();

			while (resultSet.next()) {
				order.setId(resultSet.getInt(1));
				order.setBookId(resultSet.getInt(2));
				order.setEmplId(resultSet.getInt(3));
				order.setDays(resultSet.getInt(4));
				order.setDate(resultSet.getDate(5));
				order.setStatus(STATUS.valueOf(resultSet.getString(6)));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return order;
	}

	@Override
	public List<Order> readAll() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Order> orders = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_ORDERS);
			resultSet = preparedStatement.executeQuery();
			orders = new ArrayList<>();

			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setBookId(resultSet.getInt(2));
				order.setEmplId(resultSet.getInt(3));
				order.setDays(resultSet.getInt(4));
				order.setDate(resultSet.getDate(5));
				order.setStatus(STATUS.valueOf(resultSet.getString(6)));
				orders.add(order);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return orders;
	}

	@Override
	public List<Order> readOrdersByStatus(String status) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Order> orders = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_ORDERS_BY_STATUS);
			preparedStatement.setString(1, status);
			resultSet = preparedStatement.executeQuery();
			orders = new ArrayList<>();

			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setBookId(resultSet.getInt(2));
				order.setEmplId(resultSet.getInt(3));
				order.setDays(resultSet.getInt(4));
				order.setDate(resultSet.getDate(5));
				order.setStatus(STATUS.valueOf(resultSet.getString(6)));
				orders.add(order);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return orders;
	}

	@Override
	public List<Order> readOrdersByEmployeeId(int emplId) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Order> orders = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_ORDERS_BY_EMPLOYEE_ID);
			preparedStatement.setInt(1, emplId);
			resultSet = preparedStatement.executeQuery();
			orders = new ArrayList<>();

			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setBookId(resultSet.getInt(2));
				order.setEmplId(resultSet.getInt(3));
				order.setDays(resultSet.getInt(4));
				order.setDate(resultSet.getDate(5));
				order.setStatus(STATUS.valueOf(resultSet.getString(6)));
				orders.add(order);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return orders;
	}

	public List<Order> readLastOrdersByEmployeeId(int emplId, int count) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Order> orders = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_LAST_ORDERS_BY_EMPLOYEE_ID);
			preparedStatement.setInt(1, emplId);
			preparedStatement.setInt(2, count);
			resultSet = preparedStatement.executeQuery();
			orders = new ArrayList<>();

			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setBookId(resultSet.getInt(2));
				order.setEmplId(resultSet.getInt(3));
				order.setDays(resultSet.getInt(4));
				order.setDate(resultSet.getDate(5));
				order.setStatus(STATUS.valueOf(resultSet.getString(6)));
				orders.add(order);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return orders;
	}

	@Override
	public void update(int id, Order entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_BY_ID);

			preparedStatement.setInt(1, entity.getBookId());
			preparedStatement.setInt(2, entity.getEmplId());
			preparedStatement.setInt(3, entity.getDays());
			Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+3"));
			preparedStatement.setDate(4,
					Date.valueOf(entity.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()), calendar);
			preparedStatement.setString(5, entity.getStatus().name());
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public void delete(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public void takeOneBook(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DECREMENT_BOOK_QUANTITY_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public void returnOneBook(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INCREMENT_BOOK_QUANTITY_BY_ID);
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
