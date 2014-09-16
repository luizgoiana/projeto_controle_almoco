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
	
	public Funcionario recuperarFuncionarioPorEmail(String email){
		Query query = em.createQuery("SELECT funcionario FROM Funcionario funcionario WHERE funcionario.email = email");
		query.setParameter("email", email);

		return (Funcionario) query.getSingleResult();
	}

}

