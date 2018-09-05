package by.htp.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.dao.exception.DAOException;

public abstract class AbstractDAO {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	protected void close(ResultSet resultSet, PreparedStatement preparedStatement) throws DAOException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Closing ResultSet error", e);
		}
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Closing PreparedStatement error", e);
		}
	}

	protected void close(PreparedStatement preparedStatement) throws DAOException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Closing PreparedStatement error", e);
		}
	}
}
