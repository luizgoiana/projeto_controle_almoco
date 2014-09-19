package controle.almoco.dao;

import javax.ejb.Stateless;

import controle.almoco.model.Cozinha;

@Stateless
public interface CozinhaDAO extends GenericDAO<Cozinha>{

	public abstract Cozinha save(Cozinha cozinha) throws Exception;
		
	public abstract Cozinha update(Cozinha cozinha) throws Exception;
	
	public void delete(Object id, Class<Cozinha> cozinha) throws Exception;
	
	

}
