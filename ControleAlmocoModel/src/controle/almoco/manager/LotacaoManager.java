package controle.almoco.manager;

import java.util.List;

import javax.ejb.Local;

import controle.almoco.model.Lotacao;

@Local
public interface LotacaoManager {
	
	public abstract void save(Lotacao lotacao) throws Exception;
	
	public abstract List<Lotacao> findAll();
	
	public Lotacao recuperarLotacaoPorNome(String nome);
	
	public Lotacao findLotacaoById(int id);
}
