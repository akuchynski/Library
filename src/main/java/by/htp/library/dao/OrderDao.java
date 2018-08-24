package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Order;

public interface OrderDAO extends BaseDAO<Order> {

	public List<Order> readOrdersByStatus(String status);

	public List<Order> readOrdersByEmployeeId(int emplId);
	
	public List<Order> readLastOrdersByEmployeeId(int emplId, int count);

}
