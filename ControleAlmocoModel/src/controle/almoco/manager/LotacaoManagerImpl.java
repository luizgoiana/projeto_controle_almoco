package controle.almoco.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controle.almoco.dao.LotacaoDAO;
import controle.almoco.model.Cozinha;
import controle.almoco.model.Lotacao;

@Stateless
public class LotacaoManagerImpl implements LotacaoManager{

	@Inject
	private LotacaoDAO lotacaoDAO;
	
	@Override
	public void save(Lotacao lotacao) throws Exception{
		if (lotacao.getId()==null && lotacao.getCozinha()!=null){
			Cozinha cozinha = lotacao.getCozinha();
			lotacao.setCozinha(null);
			lotacaoDAO.save(lotacao);	
			lotacao.setCozinha(cozinha);
			lotacaoDAO.update(lotacao);
		}
				
	}


	@Override
	public List<Lotacao> findAll() {
		return lotacaoDAO.findAll();
	}
	
	@Override
	public Lotacao recuperarLotacaoPorNome(String nome) {
		return lotacaoDAO.recuperarLotacaoPorNome(nome);
	}


	@Override
	public Lotacao findLotacaoById(int id) {
		return lotacaoDAO.find(id);
	}


}
