package by.htp.library.dao.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.library.dao.exception.DAOException;

@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPool.initConnectionPool();
		} catch (DAOException e) {
			logger.error("ConnectionPool initialization error");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
