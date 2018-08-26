package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.User;
import by.htp.library.service.exception.ServiceException;

public interface UserService {

	public User read(int id) throws ServiceException;

	public List<User> getAllUsers() throws ServiceException;

	public void create(User user) throws ServiceException;

	public void update(int id, User user) throws ServiceException;

	public void delete(int id) throws ServiceException;

	public boolean userIsExist(String login, String password) throws ServiceException;

	public User getUserByLogin(String login) throws ServiceException;
	
	public User getUserByLoginPassword(String login, String password) throws ServiceException;

	public List<User> getLastUsers(int count) throws ServiceException;

}
