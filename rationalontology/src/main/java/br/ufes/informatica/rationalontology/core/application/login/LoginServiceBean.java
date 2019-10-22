package br.ufes.informatica.rationalontology.core.application.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.LoginFailedException;
import br.ufes.informatica.rationalontology.core.persistence.UserDAO;


/**
 * Stateless session bean implementing the login service. See the implemented interface documentation for details.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.LoginService.feees.sigme.core.application.SessionInformation
 */
@Stateless
public class LoginServiceBean implements LoginService {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(LoginServiceBean.class.getCanonicalName());

	/** The DAO for Academic objects. */
	@EJB
	private UserDAO userDAO;

	/** TODO: document this field. */
	@Inject
	private Event<LoginEvent> loginEvent;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.LoginService.feees.sigme.core.application.SessionInformation#login(java.lang.String, java.lang.String) */
	@Override
	public void login(String username, String password) throws LoginFailedException {
		try {
			// Obtains the user given the e-mail address (that serves as username).
			User user = userDAO.retrieveByEmail(username);

			// Creates the MD5 hash of the password for comparison.
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(password);

			// Checks if the passwords match.
			String pwd = user.getPassword();
			
			if ((pwd != null) && (pwd.equals(md5pwd))) {
				User currentUser = user;
				pwd = null;
				
				SessionInformation.getInstance().setUsuarioLogado(currentUser);

				// Fires a login event.
				loginEvent.fire(new LoginEvent(currentUser));
			}
			else {
				// Passwords don't match.
				throw new LoginFailedException(LoginFailedException.LoginFailedReason.INCORRECT_PASSWORD);
			}
		}
		catch (PersistentObjectNotFoundException e) {
			// No academic was found with the given username.
			logger.log(Level.INFO, "User \"{0}\" not logged in: no registered academic found with given username.", username);
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.UNKNOWN_USERNAME);
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// Multiple academics were found with the same username.
			logger.log(Level.WARNING, "User \"{0}\" not logged in: there are more than one registered academic with the given username.", username);
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.MULTIPLE_USERS);
		}
		catch (EJBTransactionRolledbackException e) {
			// Unknown original cause. Throw the EJB exception.
			logger.log(Level.WARNING, "User \"" + username + "\" not logged in: unknown cause.", e);
			throw e;
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// No MD5 hash generation algorithm found by the JVM.
			logger.log(Level.SEVERE, "Logging in user \"" + username + "\" triggered an exception during MD5 hash generation.", e);
			throw new LoginFailedException(LoginFailedException.LoginFailedReason.MD5_ERROR);
		}
	}

	/** @see br.ufes.inf.nemo.marvin.core.application.LoginService#getCurrentUser() */
	@Override
	public User getCurrentUser() {
		try {
			return userDAO.retrieveByEmail(sessionContext.getCallerPrincipal().getName());
		}
		catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			return null;
		}
	}
}
