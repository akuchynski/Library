package by.htp.library.service.impl;

import java.util.Map;

import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.ReportDAO;
import by.htp.library.service.ReportService;

public class ReportServiceImpl implements ReportService {
	
	private final ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();

	@Override
	public Map<Integer, Integer> getEmployeesBooks(int count) {
		return reportDAO.getEmployeesBooks(count);
	}

	@Override
	public Map<Integer, Integer> getEmployeesBooksDelay(int count) {
		return reportDAO.getEmployeesBooksDelay(count);
	}

}
