package br.ufes.informatica.rationalontology.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.informatica.rationalontology.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO{
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
	public User recoverUserLogin(String email) {
		// FIXME: auto-generated method stub
		return null;
	}
	

}