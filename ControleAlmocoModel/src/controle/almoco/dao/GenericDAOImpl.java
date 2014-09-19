package controle.almoco.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

@Stateless
public abstract class GenericDAOImpl<T> implements GenericDAO<T>{
	private final static String UNIT_NAME = "ControleAlmocoModel";
	 
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
 
    private Class<T> entityClass;
 
    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    public T save(T entity) throws Exception{
    	ut.begin();
        em.persist(entity);
        ut.commit();
        return entity;
    }
 
    public void delete(Object id, Class<T> classe) throws Exception {
        T entityToBeRemoved = em.getReference(classe, id);
 
        ut.begin();
        em.remove(entityToBeRemoved);
        ut.commit();
    }
 
    public T update(T entity) throws Exception{
        ut.begin();
    	em.merge(entity);
    	ut.commit();
    	return entity;
    }
 
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
 
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
 
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
    
}
