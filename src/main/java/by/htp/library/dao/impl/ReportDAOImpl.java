package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import by.htp.library.dao.ReportDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class ReportDAOImpl implements ReportDAO {

	private static final String SQL_READ_EMPLOYEES_BOOKS = "SELECT employee_id, COUNT(book_id) AS book_count FROM employee_book GROUP BY employee_id HAVING count(book_id) >= ?";
	private static final String SQL_READ_EMPLOYEES_BOOKS_DELAY = "SELECT employee_id, COUNT(book_id) AS book_count FROM employee_book WHERE status = 'DELIVERED' AND (DATEDIFF(CURDATE(), date) > days) GROUP BY employee_id HAVING count(book_id) >= ?";

	public Map<Integer, Integer> getEmployeesBooks(int count) throws DAOException {

		Map<Integer, Integer> report = new HashMap<>();

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_EMPLOYEES_BOOKS);
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				report.put(rs.getInt("employee_id"), rs.getInt("book_count"));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return report;
	}

	public Map<Integer, Integer> getEmployeesBooksDelay(int count) throws DAOException {

		Map<Integer, Integer> report = new HashMap<>();

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_EMPLOYEES_BOOKS_DELAY);
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				report.put(rs.getInt("employee_id"), rs.getInt("book_count"));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		}

		ConnectionPool.putConnection(connection);

		return report;
	}
}
