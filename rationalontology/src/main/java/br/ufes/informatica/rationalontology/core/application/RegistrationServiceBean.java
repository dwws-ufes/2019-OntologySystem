package br.ufes.informatica.rationalontology.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.persistence.UserDAO;

/** TODO: generated by FrameWeb. Should be documented. */
@Stateless
public class RegistrationServiceBean implements RegistrationService {
	/** Serialization id (using default value, change if necessary). */
	private static final long serialVersionUID = 1L;

	/** TODO: generated by FrameWeb. Should be documented. */
	@EJB
	private UserDAO userDAO;

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void getAccess(String email) {
		// FIXME: auto-generated method stub
		return;
	}

	/** TODO: generated by FrameWeb. Should be documented. */
	@Override
	public void register(User user) {
		// FIXME: auto-generated method stub
		return;
	}

}