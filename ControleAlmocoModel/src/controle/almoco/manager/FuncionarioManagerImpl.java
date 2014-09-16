package controle.almoco.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controle.almoco.dao.FuncionarioDAO;
import controle.almoco.model.Funcionario;

@Stateless
public class FuncionarioManagerImpl implements FuncionarioManager{

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public void save(Funcionario funcionario) throws Exception{
		funcionarioDAO.save(funcionario);
		
	}

	@Override
	public List<Funcionario> findAll() {
		return funcionarioDAO.findAll();
	}

}
