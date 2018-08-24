package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.EmployeeDAO;
import by.htp.library.service.EmployeeService;
import by.htp.library.service.exception.ServiceException;

public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeDAO employeeDAO = DAOFactory.getInstance().getEmployeeDAO();

	@Override
	public List<Employee> getAllEmployees() throws ServiceException {
		return employeeDAO.readAll();
	}

	@Override
	public Employee read(int id) throws ServiceException {
		return employeeDAO.read(id);
	}

	@Override
	public void create(Employee employee) throws ServiceException {
		employeeDAO.create(employee);
	}

	@Override
	public void update(int id, Employee employee) throws ServiceException {
		employeeDAO.update(id, employee);
	}

	@Override
	public void delete(int id) throws ServiceException {
		employeeDAO.delete(id);
	}
}
