package by.htp.library.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception {
	private static final Logger logger = LoggerFactory.getLogger(ServiceException.class);
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
		logger.error("ERROR");
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
		logger.error(message, e);
	}

	public ServiceException(String message) {
		super(message);
		logger.error(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}
}
