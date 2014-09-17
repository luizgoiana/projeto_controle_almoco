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
	public void save(Cozinha cozinha){
		try {
			cozinhaDAO.save(cozinha);
		} catch (Exception e) {
			
		}	
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
	public void update(Cozinha cozinha) throws Exception {
		cozinhaDAO.update(cozinha);
		
	}

	@Override
	public void delete(Object id, Class<Cozinha> cozinha) throws Exception{
		cozinhaDAO.delete(id, cozinha);
	}

}
