package controle.almoco.manager;

import java.util.List;

import controle.almoco.model.Funcionario;

public interface FuncionarioManager {
	public abstract void save(Funcionario funcionario);
	
	public abstract List<Funcionario> findAll();
}
