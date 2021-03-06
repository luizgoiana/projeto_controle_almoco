package controle.almoco.manager;

import java.util.List;

import javax.ejb.Local;

import controle.almoco.model.Cozinha;

@Local
public interface CozinhaManager {
	public abstract Cozinha save(Cozinha cozinha) throws Exception;
	
	public abstract List<Cozinha> findAll();
	
	public abstract Cozinha findCozinhaById(int idCozinha);
	
	public abstract Cozinha update(Cozinha cozinha) throws Exception;
	
	public abstract void delete(Object id, Class<Cozinha> cozinha) throws Exception;
}
