package controle.almoco.dao;

import javax.ejb.Stateless;

import controle.almoco.model.Cozinha;
import controle.almoco.model.Funcionario;

@Stateless
public interface CozinhaDAO extends GenericDAO<Cozinha>{

	public abstract void save(Cozinha cozinha) throws Exception;
		
	public abstract void update(Cozinha cozinha) throws Exception;
	
	public void delete(Object id, Class<Cozinha> cozinha) throws Exception;
	
	

}
