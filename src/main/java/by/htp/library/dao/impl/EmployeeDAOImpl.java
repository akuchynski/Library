package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.dao.AbstractDAO;
import by.htp.library.dao.EmployeeDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class EmployeeDAOImpl extends AbstractDAO implements EmployeeDAO {

	private static final String SQL_CREATE_EMPLOYEE = "INSERT INTO employee (name, surname, year) VALUES (?, ?, ?)";
	private static final String SQL_READ_EMPLOYEES = "SELECT * FROM employee";
	private static final String SQL_READ_NOT_REG_EMPLOYEES = "SELECT * FROM employee WHERE emp_id NOT IN (SELECT user_id FROM user)";
	private static final String SQL_READ_EMPLOYEES_BY_NAME_SURNAME = "SELECT emp_id, name, surname FROM employee WHERE (CONCAT(surname, ' ', name) LIKE ?) OR (CONCAT(name, ' ', surname) LIKE ?)";
	private static final String SQL_READ_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE emp_id = ?";
	private static final String SQL_READ_ID_BY_EMPLOYEE = "SELECT emp_id FROM employee WHERE name = ? AND surname = ? AND year = ?";
	private static final String SQL_READ_EMPLOYEES_BY_SURNAME = "SELECT * FROM employee WHERE surname = ?";
	private static final String SQL_UPDATE_EMPLOYEE_BY_ID = "UPDATE employee SET name = ?, surname = ?, year = ?  WHERE emp_id = ?";
	private static final String SQL_DELETE_EMPLOYEE_BY_ID = "DELETE FROM employee WHERE emp_id = ?";

	public void create(Employee entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE_EMPLOYEE);

			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getSurname());
			preparedStatement.setInt(3, entity.getYear());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	public List<Employee> readAll() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEES);
			resultSet = preparedStatement.executeQuery();
			employees = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setYear(resultSet.getInt(4));
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return employees;
	}
	
	public List<Employee> readNotRegisteredEmployees() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_NOT_REG_EMPLOYEES);
			resultSet = preparedStatement.executeQuery();
			employees = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setYear(resultSet.getInt(4));
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return employees;
	}

	public List<Employee> readByNameSurname(String fullName) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEES_BY_NAME_SURNAME);
			preparedStatement.setString(1, "%" + fullName + "%");
			preparedStatement.setString(2, "%" + fullName + "%");
			resultSet = preparedStatement.executeQuery();
			employees = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setYear(resultSet.getInt(4));
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return employees;
	}

	public Employee read(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			employee = new Employee();

			while (resultSet.next()) {
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setYear(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return employee;
	}

	@Override
	public int readIdEmployee(Employee employee) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_ID_BY_EMPLOYEE);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getSurname());
			preparedStatement.setInt(3, employee.getYear());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return id;
	}

	public List<Employee> readBySurname(String surname) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEES_BY_SURNAME);
			preparedStatement.setString(1, surname);
			resultSet = preparedStatement.executeQuery();
			employees = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setName(resultSet.getString(2));
				employee.setSurname(resultSet.getString(3));
				employee.setYear(resultSet.getInt(4));
				employees.add(employee);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return employees;
	}

	public void update(int id, Employee entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE_BY_ID);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getSurname());
			preparedStatement.setInt(3, entity.getYear());
			preparedStatement.setInt(4, id);
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
			preparedStatement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_BY_ID);
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
