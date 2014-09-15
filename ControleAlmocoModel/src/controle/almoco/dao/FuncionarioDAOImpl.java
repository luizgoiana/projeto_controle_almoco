package controle.almoco.dao;

import controle.almoco.model.Funcionario;

public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements FuncionarioDAO {

	public FuncionarioDAOImpl() {
		super(Funcionario.class);
	}
	
	public void save(Funcionario funcionario) {
        super.save(funcionario);
	}

}

