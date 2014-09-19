package controle.almoco.dao;

import javax.ejb.Stateless;

import controle.almoco.model.Funcionario;

@Stateless
public interface FuncionarioDAO extends GenericDAO<Funcionario>{

	public abstract Funcionario save(Funcionario funcionario) throws Exception;
	
	public abstract Funcionario recuperarFuncionarioPorEmail(String email);
	
	public abstract Funcionario update(Funcionario funcionario) throws Exception;
	
	public void delete(Object id, Class<Funcionario> funcionario) throws Exception;
	
	

}
