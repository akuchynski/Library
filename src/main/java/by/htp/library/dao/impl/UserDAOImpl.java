package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.bean.User;
import by.htp.library.dao.EmployeeDAO;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.util.ConnectionPool;

public class UserDAOImpl implements UserDAO {

	private static final String SQL_CREATE_USER = "INSERT INTO user (user_id, login, email, password) VALUES (?, ?, ?, ?)";
	private static final String SQL_READ_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
	private static final String SQL_READ_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
	private static final String SQL_READ_USERS = "SELECT * FROM user";
	private static final String SQL_READ_LAST_USERS = "SELECT * FROM user order by user_id desc limit ?";
	private static final String SQL_READ_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";
	private static final String SQL_READ_USER_ROLE_BY_LOGIN_PASSWORD = "SELECT role FROM user WHERE login = ? AND password = ?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET email = ?, password = ?, active = ? WHERE user_id = ?";
	private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id = ?";

	@Override
	public void create(User user) {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_CREATE_USER);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);
	}

	@Override
	public void createUserByEmployee(User user, Employee employee) {

		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_CREATE_USER);

			EmployeeDAO employeedao = new EmployeeDAOImpl();
			int empId = employeedao.readIdEmployee(employee);

			ps.setInt(1, empId);
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);
	}

	@Override
	public User read(int id) {

		Connection connection = ConnectionPool.getConnection();
		User user = new User();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setActive(rs.getBoolean("active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return user;
	}

	@Override
	public User readByLogin(String login) {

		Connection connection = ConnectionPool.getConnection();
		User user = new User();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_LOGIN);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setActive(rs.getBoolean("active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return user;
	}

	public List<User> readAll() {

		Connection connection = ConnectionPool.getConnection();
		List<User> users = new ArrayList<>();

		try {

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_READ_USERS);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setActive(rs.getBoolean("active"));

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return users;
	}

	public List<User> readLastUsers(int count) {

		Connection connection = ConnectionPool.getConnection();
		List<User> users = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_LAST_USERS);
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setActive(rs.getBoolean("active"));

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return users;
	}

	public boolean userIsExist(String login, String password) {

		Connection connection = ConnectionPool.getConnection();
		boolean result = false;

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_LOGIN_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return result;
	}
	
	@Override
	public boolean userIsActivated(String login) {
		
		Connection connection = ConnectionPool.getConnection();
		boolean activate = false;

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_LOGIN);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if(rs.getInt("deactivate") != 0) {
					activate = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return activate;
	}
	

	@Override
	public String getRoleByLoginPassword(String login, String password) {

		Connection connection = ConnectionPool.getConnection();
		String role = "";

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_ROLE_BY_LOGIN_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				role = rs.getString("role");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

		return role;
	}

	@Override
	public void update(int id, User entity) {
		
		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);

			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getPassword());
			ps.setBoolean(3, entity.isActive());
			ps.setInt(4, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

	}

	@Override
	public void delete(int id) {
		
		Connection connection = ConnectionPool.getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConnectionPool.putConnection(connection);

	}
}
