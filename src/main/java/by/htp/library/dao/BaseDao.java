package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Entity;
import by.htp.library.dao.exception.DAOException;

public interface BaseDAO<T extends Entity> {

	public void create(T entity) throws DAOException;

	public T read(int id) throws DAOException;

	public List<T> readAll() throws DAOException;

	public void update(int id, T entity) throws DAOException;

	public void delete(int id) throws DAOException;

}
