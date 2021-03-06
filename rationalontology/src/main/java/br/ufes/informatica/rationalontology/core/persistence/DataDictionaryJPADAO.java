package br.ufes.informatica.rationalontology.core.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary;
import br.ufes.informatica.rationalontology.core.domain.DataDictionary_;
import br.ufes.informatica.rationalontology.core.domain.Ontology;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class DataDictionaryJPADAO extends BaseJPADAO<DataDictionary> implements DataDictionaryDAO{
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
    @PersistenceContext
    private EntityManager entityManager;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
    
    
	/** TODO: generated by FrameWeb. Should be documented. */
    @Override
	public List<DataDictionary> getDataDictionary(Ontology ontology) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DataDictionary> cq = cb.createQuery(DataDictionary.class);		
		Root<DataDictionary> root = cq.from(DataDictionary.class);

		cq.where(	cb.equal(root.get(DataDictionary_.Source), ontology) ) ;
		TypedQuery<DataDictionary> q = entityManager.createQuery(cq);
		
		return q.getResultList();
	}
	

}