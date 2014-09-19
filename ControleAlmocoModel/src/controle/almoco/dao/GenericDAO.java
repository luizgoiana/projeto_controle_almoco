package controle.almoco.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> findAll();
	
	public T find(int entityID);
	
	public T update(T entity) throws Exception;
	
	public T save(T entity) throws Exception;
	
	public void delete(Object id, Class<T> classe) throws Exception;
}
