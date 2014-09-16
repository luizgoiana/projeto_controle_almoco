package controle.almoco.dao;

import javax.ejb.Stateless;

import controle.almoco.model.Funcionario;

@Stateless
public interface FuncionarioDAO extends GenericDAO<Funcionario>{

	public abstract void save(Funcionario funcionario) throws Exception;
	
	public abstract Funcionario recuperarFuncionarioPorEmail(String email);
	
	public abstract void update(Funcionario funcionario) throws Exception;
	
	public void delete(Object id, Class<Funcionario> funcionario) throws Exception;
	
	

}
