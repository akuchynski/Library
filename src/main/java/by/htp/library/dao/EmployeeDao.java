package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Employee;

public interface EmployeeDAO extends BaseDAO<Employee> {

	public List<Employee> readBySurname(String title);

	public int readIdEmployee(Employee employee);

}
