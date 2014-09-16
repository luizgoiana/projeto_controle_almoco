package controle.almoco.manager;

import java.util.List;

import javax.ejb.Local;

import controle.almoco.model.Funcionario;

@Local
public interface FuncionarioManager {
	public abstract void save(Funcionario funcionario) throws Exception;
	
	public abstract List<Funcionario> findAll();
}