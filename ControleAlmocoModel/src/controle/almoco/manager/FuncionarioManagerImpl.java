package controle.almoco.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controle.almoco.dao.FuncionarioDAO;
import controle.almoco.model.Funcionario;
import controle.almoco.util.Constantes;
import controle.almoco.util.Util;

@Stateless
public class FuncionarioManagerImpl implements FuncionarioManager{

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public void save(Funcionario funcionario) throws Exception{
		Funcionario funcionarioExistente = new Funcionario();
		funcionarioExistente = funcionarioDAO.recuperarFuncionarioPorEmail(funcionario.getEmail());
		if(!Util.validaObjeto(funcionarioExistente)){
			funcionarioDAO.save(funcionario);			
		}else{
			throw new Exception(Constantes.EXCEPTION.USUARIO_EXISTENTE);
		}
		
		
	}

	@Override
	public List<Funcionario> findAll() {
		return funcionarioDAO.findAll();
	}

	@Override
	public Funcionario recuperarFuncionarioPorEmail(String email) {
		return funcionarioDAO.recuperarFuncionarioPorEmail(email);
	}

	@Override
	public Funcionario findFuncionarioById(int idFuncionario) {
		return funcionarioDAO.find(idFuncionario);
	}

	@Override
	public void update(Funcionario funcionario) throws Exception {
		funcionarioDAO.update(funcionario);
		
	}

	@Override
	public void delete(Object id, Class<Funcionario> funcionario) throws Exception{
		funcionarioDAO.delete(id, funcionario);
	}

}
