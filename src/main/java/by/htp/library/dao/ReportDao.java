package by.htp.library.dao;

import java.util.Map;

public interface ReportDAO {

	public Map<Integer, Integer> getEmployeesBooks(int count);

	public Map<Integer, Integer> getEmployeesBooksDelay(int count);

}