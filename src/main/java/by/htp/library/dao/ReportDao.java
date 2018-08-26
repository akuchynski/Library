package by.htp.library.dao;

import java.util.Map;

import by.htp.library.dao.exception.DAOException;

public interface ReportDAO {

	public Map<Integer, Integer> getEmployeesBooks(int count) throws DAOException;

	public Map<Integer, Integer> getEmployeesBooksDelay(int count) throws DAOException;

}
