package by.htp.library.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.dao.exception.DAOException;

public class ConnectionPool {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

	private static final String DB_CONNECT_PROPERTY = "db_config";
	private static final int POOL_SIZE = 10;

	private static BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<Connection>(POOL_SIZE, true);

	private static ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);

	private static String url = rb.getString("db.url");
	private static String login = rb.getString("db.login");
	private static String pass = rb.getString("db.pass");

	public static void initConnectionPool() throws DAOException {
		try {
			Class.forName(rb.getString("db.driver"));
			for (int i = 0; i < POOL_SIZE; i++) {
				connectionPool.add(DriverManager.getConnection(url, login, pass));
			}
			logger.info("Connection pool initialization");
		} catch (ClassNotFoundException | SQLException e) {
			throw new DAOException(e);
		}
	}

	public static Connection getConnection() throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.take();
			logger.info("Connection get");
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
		return connection;
	}

	public static void putConnection(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				connectionPool.put(connection);
				logger.info("Connection put");
			} catch (InterruptedException e) {
				throw new DAOException(e);
			}
		}
	}

}
