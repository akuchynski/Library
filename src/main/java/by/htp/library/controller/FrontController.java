package by.htp.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.command.CommandFactory;
import by.htp.library.controller.exception.ControllerException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class FrontController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	private static final long serialVersionUID = 1L;

	public static final String ATTR_LAST_COMMAND = "lastCommand";
	public static final String PARAM_COMMAND = "commandName";
	public static final String FORWARD_SERVLET_ERROR = "forward.servlet.error";
	public static final String FORWARD_ERROR = "forward.error";

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Command command = CommandFactory.getCommand(request);
			command.execute(request, response);
			logger.info(request.getMethod() + " : command class: " + command.getClass().getSimpleName());
		} catch (ControllerException e) {
			logger.error("Controller command error");
		}
	}
}