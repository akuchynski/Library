package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import by.htp.library.dao.AbstractDAO;
import by.htp.library.dao.ReportDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class ReportDAOImpl extends AbstractDAO implements ReportDAO {

	private static final String SQL_READ_EMPLOYEES_BOOKS = "SELECT employee_id, COUNT(book_id) AS book_count FROM employee_book GROUP BY employee_id HAVING count(book_id) >= ?";
	private static final String SQL_READ_EMPLOYEES_BOOKS_DELAY = "SELECT employee_id, COUNT(book_id) AS book_count FROM employee_book WHERE status = 'DELIVERED' AND (DATEDIFF(CURDATE(), date) > days) GROUP BY employee_id HAVING count(book_id) >= ?";

	public Map<Integer, Integer> getEmployeesBooks(int count) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<Integer, Integer> report = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEES_BOOKS);
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			report = new HashMap<>();

			while (resultSet.next()) {
				report.put(resultSet.getInt(1), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return report;
	}

	public Map<Integer, Integer> getEmployeesBooksDelay(int count) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<Integer, Integer> report = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEES_BOOKS_DELAY);
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			report = new HashMap<>();

			while (resultSet.next()) {
				report.put(resultSet.getInt(1), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return report;
	}
}
