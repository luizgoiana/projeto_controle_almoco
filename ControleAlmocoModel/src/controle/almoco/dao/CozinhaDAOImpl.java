package controle.almoco.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import controle.almoco.model.Cozinha;

@Stateless
public class CozinhaDAOImpl extends GenericDAOImpl<Cozinha> implements CozinhaDAO {

	private final static String UNIT_NAME = "ControleAlmocoModel";
	
	@PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
	
	public CozinhaDAOImpl() {
		super(Cozinha.class);
	}
	
	public Cozinha save(Cozinha cozinha) throws Exception {
        return super.save(cozinha);
	}
	
	public List<Cozinha> findAll(){
		return super.findAll();
	}
	
	public Cozinha update(Cozinha cozinha) throws Exception{
		return super.update(cozinha);
	}
	
	public void delete(Object id, Class<Cozinha> cozinha) throws Exception{
		super.delete(id, cozinha);
	}
}

