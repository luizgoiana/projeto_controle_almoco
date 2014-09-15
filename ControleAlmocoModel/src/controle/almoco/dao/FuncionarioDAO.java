package controle.almoco.dao;

import controle.almoco.model.Funcionario;


public interface FuncionarioDAO extends GenericDAO<Funcionario>{

	public abstract void save(Funcionario funcionario);
	
	

}
