package controle.almoco.dao;

import java.util.List;

import javax.ejb.Stateless;

import controle.almoco.model.Lotacao;

@Stateless
public interface LotacaoDAO extends GenericDAO<Lotacao>{

	public abstract Lotacao save(Lotacao lotacao) throws Exception;
	
	public abstract List<Lotacao> findAll();
	
	public Lotacao recuperarLotacaoPorNome(String nome);
	
	public Lotacao find(int id);

}
