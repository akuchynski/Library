package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.service.exception.ServiceException;

public interface EmployeeService {

	public Employee read(int id) throws ServiceException;

	public List<Employee> getAllEmployees() throws ServiceException;
	
	public List<Employee> getNotRegisteredEmployees() throws ServiceException;

	public void create(Employee employee) throws ServiceException;

	public void update(int id, Employee employee) throws ServiceException;

	public void delete(int id) throws ServiceException;

}
