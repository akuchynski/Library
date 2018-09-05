package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Order;
import by.htp.library.dao.exception.DAOException;

public interface OrderDAO extends BaseDAO<Order> {

	public List<Order> readOrdersByStatus(String status) throws DAOException;

	public List<Order> readOrdersByEmployeeId(int emplId) throws DAOException;
	
	public List<Order> readLastOrdersByEmployeeId(int emplId, int count) throws DAOException;
	
	public void returnOneBook(int id) throws DAOException;
	
	public void takeOneBook(int id) throws DAOException;

}
