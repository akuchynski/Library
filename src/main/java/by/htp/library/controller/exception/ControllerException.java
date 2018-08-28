package by.htp.library.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerException extends Exception {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerException.class);
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "Controller error";

	public ControllerException() {
		super();
		logger.error(ERROR);
	}

	public ControllerException(String message) {
		super(message);
		logger.error(message);
	}

	public ControllerException(Exception e) {
		super(e);
		logger.error(e.toString());
	}

	public ControllerException(String message, Exception e) {
		super(message, e);
		logger.error(message, e);
	}
}
