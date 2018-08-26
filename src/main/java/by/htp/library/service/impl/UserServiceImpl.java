package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

	@Override
	public User read(int id) throws ServiceException {
		try {
			return userDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDAO.readAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void create(User user) throws ServiceException {
		try {
			userDAO.create(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(int id, User user) throws ServiceException {
		try {
			userDAO.update(id, user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			userDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean userIsExist(String login, String password) throws ServiceException {
		try {
			return userDAO.userIsExist(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User getUserByLogin(String login) throws ServiceException {
		try {
			return userDAO.readByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public User getUserByLoginPassword(String login, String password) throws ServiceException {
		try {
			return userDAO.readByLoginPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getLastUsers(int count) throws ServiceException {
		try {
			return userDAO.readLastUsers(count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
