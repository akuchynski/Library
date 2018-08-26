package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Order;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.OrderDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.OrderService;
import by.htp.library.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {

	private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

	@Override
	public Order read(int id) throws ServiceException {
		try {
			return orderDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Order> getAllOrders() throws ServiceException {
		try {
			return orderDAO.readAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void create(Order order) throws ServiceException {
		try {
			orderDAO.create(order);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(int id, Order order) throws ServiceException {
		try {
			orderDAO.update(id, order);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			orderDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Order> getOrdersByStatus(String status) throws ServiceException {
		try {
			return orderDAO.readOrdersByStatus(status);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Order> getLastOrdersByEmployeeId(int emplId, int count) throws ServiceException {
		try {
			return orderDAO.readLastOrdersByEmployeeId(emplId, count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
