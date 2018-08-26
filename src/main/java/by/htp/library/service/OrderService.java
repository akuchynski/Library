package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Order;
import by.htp.library.service.exception.ServiceException;

public interface OrderService {

	public Order read(int id) throws ServiceException;

	public List<Order> getAllOrders() throws ServiceException;

	public void create(Order order) throws ServiceException;

	public void update(int id, Order order) throws ServiceException;

	public void delete(int id) throws ServiceException;

	public List<Order> getOrdersByStatus(String status) throws ServiceException;

	public List<Order> getLastOrdersByEmployeeId(int emplId, int count) throws ServiceException;

}
