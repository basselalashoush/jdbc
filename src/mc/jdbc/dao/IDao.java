package mc.jdbc.dao;

import java.sql.Connection;
import java.util.List;

public interface IDao<T> {
	
	public Connection connection = Connections.Instance();
	/*
	 * CRUD
	 */
	List<T> list();
	T find(int id);
	boolean add(T item);
	boolean delete(T item);
	boolean update(T item);
	String ucfirst(String chaine);
}
