package controle.almoco.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controle.almoco.dao.LotacaoDAO;
import controle.almoco.model.Lotacao;

@Stateless
public class LotacaoManagerImpl implements LotacaoManager{

	@Inject
	private LotacaoDAO lotacaoDAO;
	
	@Override
	public void save(Lotacao lotacao) throws Exception{
		lotacaoDAO.save(lotacao);			
	}


	@Override
	public List<Lotacao> findAll() {
		return lotacaoDAO.findAll();
	}


}
