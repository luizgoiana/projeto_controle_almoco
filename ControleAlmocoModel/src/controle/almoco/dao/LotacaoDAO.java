package controle.almoco.dao;

import java.util.List;

import javax.ejb.Stateless;

import controle.almoco.model.Lotacao;

@Stateless
public interface LotacaoDAO extends GenericDAO<Lotacao>{

	public abstract void save(Lotacao lotacao) throws Exception;
	
	public abstract List<Lotacao> findAll();
	
	

}
