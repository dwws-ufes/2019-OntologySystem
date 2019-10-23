package br.ufes.informatica.rationalontology.core.application;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;
import br.ufes.informatica.rationalontology.core.persistence.UserDAO;

@Stateless
@RolesAllowed("SysAdmin")
public class EditProfilerAppBean implements EditProfilerApp{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;

	@Override
	public void save(User user) throws RegistrationFailedException {
		
		try {
			user.setPassword(TextUtils.produceBase64EncodedMd5Hash(user.getPassword()));

			userDAO.save(user);
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			throw new RegistrationFailedException(e);
		}
	}

	@Override
	public void checkAuthorization() {
		System.out.println("Authorization: EditProfilerApp");
	}

}
