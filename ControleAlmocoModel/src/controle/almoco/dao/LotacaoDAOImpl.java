package controle.almoco.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import controle.almoco.model.Funcionario;
import controle.almoco.model.Lotacao;

@Stateless
public class LotacaoDAOImpl extends GenericDAOImpl<Lotacao> implements LotacaoDAO {

	private final static String UNIT_NAME = "ControleAlmocoModel";
	
	@PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
	
	public LotacaoDAOImpl() {
		super(Lotacao.class);
	}
	
	
	public void save(Lotacao lotacao) throws Exception {
        super.save(lotacao);
	}
	
	public List<Lotacao> findAll(){
		return super.findAll();
	}

	
}

