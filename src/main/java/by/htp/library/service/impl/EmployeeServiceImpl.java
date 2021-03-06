package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.EmployeeDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.exception.ServiceException;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDAO employeeDAO = DAOFactory.getInstance().getEmployeeDAO();

	@Override
	public List<Employee> getAllEmployees() throws ServiceException {
		try {
			return employeeDAO.readAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Employee read(int id) throws ServiceException {
		try {
			return employeeDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Employee> getNotRegisteredEmployees() throws ServiceException {
		try {
			return employeeDAO.readNotRegisteredEmployees();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void create(Employee employee) throws ServiceException {
		try {
			employeeDAO.create(employee);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(int id, Employee employee) throws ServiceException {
		try {
			employeeDAO.update(id, employee);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			employeeDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
