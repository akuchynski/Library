package by.htp.library.dao.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(DAOException.class);
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "DAO error";

	public DAOException() {
		super();
		logger.error(ERROR);
	}

	public DAOException(String message) {
		super(message);
		logger.error(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
		logger.error(message);
	}
}
