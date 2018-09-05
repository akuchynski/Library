package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.bean.User;
import by.htp.library.dao.AbstractDAO;
import by.htp.library.dao.EmployeeDAO;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.dao.util.ConnectionPool;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

	private static final String SQL_CREATE_USER = "INSERT INTO user (user_id, login, email, password) VALUES (?, ?, ?, ?)";
	private static final String SQL_READ_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
	private static final String SQL_READ_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
	private static final String SQL_READ_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";
	private static final String SQL_READ_USERS = "SELECT * FROM user";
	private static final String SQL_READ_LAST_USERS = "SELECT * FROM user order by user_id desc limit ?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET email = ?, password = ?, active = ? WHERE user_id = ?";
	private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id = ?";

	@Override
	public void create(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE_USER);

			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public void createUserByEmployee(User user, Employee employee) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE_USER);

			EmployeeDAO employeedao = new EmployeeDAOImpl();
			int empId = employeedao.readIdEmployee(employee);
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(preparedStatement);
			ConnectionPool.putConnection(connection);
		}
	}

	@Override
	public User read(int id) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USER_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			user = new User();

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(resultSet.getString(5));
				user.setActive(resultSet.getBoolean(6));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return user;
	}

	@Override
	public User readByLogin(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			user = new User();

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(resultSet.getString(5));
				user.setActive(resultSet.getBoolean(6));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return user;
	}

	@Override
	public User readByLoginPassword(String login, String password) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			user = new User();

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(resultSet.getString(5));
				user.setActive(resultSet.getBoolean(6));
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return user;
	}

	public List<User> readAll() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USERS);
			resultSet = preparedStatement.executeQuery();
			users = new ArrayList<>();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(resultSet.getString(5));
				user.setActive(resultSet.getBoolean(6));
				users.add(user);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return users;
	}

	public List<User> readLastUsers(int count) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_LAST_USERS);
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			users = new ArrayList<>();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(resultSet.getString(5));
				user.setActive(resultSet.getBoolean(6));
				users.add(user);
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return users;
	}

	public boolean userIsExist(String login, String password) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean result = false;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return result;
	}

	@Override
	public boolean userIsActivated(String login) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean active = false;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_READ_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("deactivate") != 0) {
					active = true;
				}
			}
		} catch (SQLException e) {
			throw new DAOException("DAO error", e);
		} finally {
			close(resultSet, preparedStatement);
			ConnectionPool.putConnection(connection);
		}
		return active;
	}

	@Override
	public void update(int id, User entity) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
			preparedStatement.setString(1, entity.getEmail());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setBoolean(3, entity.isActive());
			preparedStatement.setInt(4, id);
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
			preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
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
