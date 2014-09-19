package controle.almoco.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

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
	
	
	public Lotacao save(Lotacao lotacao) throws Exception {
		if (lotacao.getId()!= null) {
			super.update(lotacao);
		} else {
			super.save(lotacao);
		}
		
        return lotacao;
	}
	
	public List<Lotacao> findAll(){
		return super.findAll();
	}
	
	public Lotacao find(int id){
		return super.find(id);
	}
	
	public Lotacao recuperarLotacaoPorNome(String nome){
		Query query = null;
		
		query = em.createQuery("SELECT l FROM Lotacao l WHERE l.descricao =".concat("'").concat(nome).concat("'"));
		
		List<Lotacao> lista = query.getResultList();

		return lista.isEmpty()? null : lista.get(0);
	}

	
}

