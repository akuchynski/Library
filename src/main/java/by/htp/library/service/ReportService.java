package by.htp.library.service;

import java.util.Map;

import by.htp.library.service.exception.ServiceException;

public interface ReportService {

	public Map<Integer, Integer> getEmployeesBooks(int count) throws ServiceException;

	public Map<Integer, Integer> getEmployeesBooksDelay(int count) throws ServiceException;

}
