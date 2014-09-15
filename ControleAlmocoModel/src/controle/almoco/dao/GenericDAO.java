package controle.almoco.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> findAll();
}
