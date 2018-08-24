package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Order;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.OrderDAO;
import by.htp.library.service.OrderService;
import by.htp.library.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {
	
	private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

	@Override
	public Order read(int id) throws ServiceException {
		return orderDAO.read(id);
	}

	@Override
	public List<Order> getAllOrders() throws ServiceException {
		return orderDAO.readAll();
	}

	@Override
	public void create(Order order) throws ServiceException {
		orderDAO.create(order);
	}

	@Override
	public void update(int id, Order order) throws ServiceException {
		orderDAO.update(id, order);
	}

	@Override
	public void delete(int id) throws ServiceException {
		orderDAO.delete(id);
	}

	@Override
	public List<Order> getOrdersByStatus(String status) {
		return orderDAO.readOrdersByStatus(status);
	}

	@Override
	public List<Order> getLastOrdersByEmployeeId(int emplId, int count) {
		return orderDAO.readLastOrdersByEmployeeId(emplId, count);
	}
}
