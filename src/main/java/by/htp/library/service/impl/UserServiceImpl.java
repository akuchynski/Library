package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.UserDAO;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

	@Override
	public User read(int id) {
		return userDAO.read(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.readAll();
	}

	@Override
	public void create(User user) throws ServiceException {
		userDAO.create(user);	
	}

	@Override
	public void update(int id, User user) throws ServiceException {
		userDAO.update(id, user);
	}

	@Override
	public void delete(int id) throws ServiceException {
		userDAO.delete(id);	
	}
	
	@Override
	public boolean userIsExist(String login, String password) {
		return userDAO.userIsExist(login, password);
	}
	
	@Override
	public User getUserByLogin(String login) {
		return userDAO.readByLogin(login);
	}

	@Override
	public List<User> getLastUsers(int count) throws ServiceException {
		return userDAO.readLastUsers(count);
	}
}
