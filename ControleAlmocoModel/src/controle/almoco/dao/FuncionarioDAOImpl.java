package controle.almoco.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import controle.almoco.model.Funcionario;

@Stateless
public class FuncionarioDAOImpl extends GenericDAOImpl<Funcionario> implements FuncionarioDAO {

	private final static String UNIT_NAME = "ControleAlmocoModel";
	
	@PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
	
	public FuncionarioDAOImpl() {
		super(Funcionario.class);
	}
	
	public void save(Funcionario funcionario) throws Exception {
        super.save(funcionario);
	}
	
	public List<Funcionario> findAll(){
		return super.findAll();
	}
	
	public void update(Funcionario funcionario) throws Exception{
		super.update(funcionario);
	}
	
	public void delete(Object id, Class<Funcionario> funcionario) throws Exception{
		super.delete(id, funcionario);
	}
	
	public Funcionario recuperarFuncionarioPorEmail(String email){
		Query query = null;
		
		query = em.createQuery("SELECT f FROM Funcionario f WHERE f.email =".concat("'").concat(email).concat("'"));
		
		List<Funcionario> lista = query.getResultList();

		return lista.isEmpty()? null : lista.get(0);
	}

}

