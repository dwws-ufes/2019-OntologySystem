package br.ufes.informatica.rationalontology.core.application;

import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.informatica.rationalontology.core.exception.LoginFailedException;
import br.ufes.informatica.rationalontology.core.persistence.UserDAO;
import br.ufes.informatica.rationalontology.core.domain.User;

/**
 * Stateful session bean implementing the session information component. See the implemented interface documentation for
 * details.
 * 
 * @author Adapted from: Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.org.feees.sigme.core.application.SessionInformation
 */
@ManagedBean(name = "usuarioLogadoMB")
@SessionScoped
@Stateful
public class SessionInformationBean  implements SessionInformation {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The DAO for User objects. */
	@EJB
	private UserDAO userDAO;

	/** The current user logged in. */
	private User currentUser;

	/** @see br.org.feees.sigme.core.application.SessionInformation#getCurrentUser() */
	@Override
	public User getCurrentUser() {
		return currentUser;
	}

	/** @see br.org.feees.sigme.core.application.SessionInformation#login(java.lang.String, java.lang.String) */
	@Override
	public void login(String username, String password) throws LoginFailedException {
		try {
			User user = userDAO.retrieveByEmail(username);

			String md5pwd = TextUtils.produceMd5Hash(password);

			String pwd = user.getPassword();
			if ((pwd != null) && (pwd.equals(md5pwd))) {
				currentUser = user;
				SessionContext.getInstance().setAttribute("usuarioLogado", currentUser);
			}
			else {
				// Passwords don't match.
				throw new LoginFailedException(LoginFailedException.LoginFailedReason.INCORRECT_PASSWORD);
			}
		}
		catch (PersistentObjectNotFoundException e) {
			// No user was found with the given username.
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.UNKNOWN_USERNAME);
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// Multiple users were found with the same username.
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.MULTIPLE_USERS);
		}
		catch (EJBTransactionRolledbackException e) {
			// Unknown original cause. Throw the EJB exception.
			throw e;
		}
		catch (NoSuchAlgorithmException e) {
			// No MD5 hash generation algorithm found by the JVM.
			throw new LoginFailedException(LoginFailedException.LoginFailedReason.MD5_ERROR);
		}
	}
	
    public void logout() {
        SessionContext.getInstance().encerrarSessao();
        //saddInfoMessage("Logout realizado com sucesso !");
     }
}
