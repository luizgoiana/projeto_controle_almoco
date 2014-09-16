package controle.almoco.dao;

import java.util.List;

import javax.ejb.Stateless;

import controle.almoco.model.Funcionario;

@Stateless
public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements FuncionarioDAO {

	public FuncionarioDAOImpl() {
		super(Funcionario.class);
	}
	
	public void save(Funcionario funcionario) throws Exception {
        super.save(funcionario);
	}
	
	public List<Funcionario> findAll(){
		return super.findAll();
	}

}

