package br.ufes.informatica.rationalontology.core.persistence;

import javax.ejb.Local;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.rationalontology.core.domain.User;

/** TODO: generated by FrameWeb. Should be documented. */
@Local
public interface UserDAO extends BaseDAO<User> {

	User recoverUserLogin(String email);
	
}