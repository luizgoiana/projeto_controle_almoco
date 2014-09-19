package controle.almoco.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controle.almoco.dao.CozinhaDAO;
import controle.almoco.model.Cozinha;

@Stateless
public class CozinhaManagerImpl implements CozinhaManager{

	@Inject
	private CozinhaDAO cozinhaDAO;
	
	@Override
	public Cozinha save(Cozinha cozinha){
		try {
			return cozinhaDAO.save(cozinha);
		} catch (Exception e) {
			
		}	
		return null;
	}

	@Override
	public List<Cozinha> findAll() {
		return cozinhaDAO.findAll();
	}

	@Override
	public Cozinha findCozinhaById(int idCozinha) {
		return cozinhaDAO.find(idCozinha);
	}

	@Override
	public Cozinha update(Cozinha cozinha) throws Exception {
		return cozinhaDAO.update(cozinha);
		
	}

	@Override
	public void delete(Object id, Class<Cozinha> cozinha) throws Exception{
		cozinhaDAO.delete(id, cozinha);
	}

}
