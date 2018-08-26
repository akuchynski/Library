package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.bean.User;
import by.htp.library.dao.exception.DAOException;

public interface UserDAO extends BaseDAO<User> {

	public void createUserByEmployee(User user, Employee employee) throws DAOException;

	public User readByLogin(String title) throws DAOException;
	
	public User readByLoginPassword(String login, String password) throws DAOException;

	public List<User> readLastUsers(int count) throws DAOException;

	public boolean userIsExist(String login, String password) throws DAOException;

//	public String getRoleByLoginPassword(String login, String password) throws DAOException;

	public boolean userIsActivated(String login) throws DAOException;

}
