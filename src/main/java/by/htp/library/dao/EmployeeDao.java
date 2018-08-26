package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.dao.exception.DAOException;

public interface EmployeeDAO extends BaseDAO<Employee> {

	public List<Employee> readBySurname(String title) throws DAOException;

	public int readIdEmployee(Employee employee) throws DAOException;

}
