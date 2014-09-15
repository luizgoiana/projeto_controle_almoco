package controle.almoco.dao;

import java.util.List;

import controle.almoco.model.Funcionario;

public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements FuncionarioDAO {

	public FuncionarioDAOImpl() {
		super(Funcionario.class);
	}
	
	public void save(Funcionario funcionario) {
        super.save(funcionario);
	}
	
	public List<Funcionario> findAll(){
		return super.findAll();
	}

}

