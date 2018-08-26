package by.htp.library.service.impl;

import java.util.Map;

import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.ReportDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.ReportService;
import by.htp.library.service.exception.ServiceException;

public class ReportServiceImpl implements ReportService {

	private final ReportDAO reportDAO = DAOFactory.getInstance().getReportDAO();

	@Override
	public Map<Integer, Integer> getEmployeesBooks(int count) throws ServiceException {
		try {
			return reportDAO.getEmployeesBooks(count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Map<Integer, Integer> getEmployeesBooksDelay(int count) throws ServiceException {
		try {
			return reportDAO.getEmployeesBooksDelay(count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
