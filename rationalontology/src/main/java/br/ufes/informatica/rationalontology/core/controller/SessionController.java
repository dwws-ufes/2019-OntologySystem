package br.ufes.informatica.rationalontology.core.controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.login.LoginService;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.LoginFailedException;
import br.ufes.informatica.rationalontology.core.exception.LoginFailedException.LoginFailedReason;

/**
 * Session-scoped managed bean that provides to web pages the login service, indication if the user is logged in and the
 * current date/time.
 * 
 * @author Adapted from: Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
@Named
@SessionScoped
public class SessionController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Information on the current visitor. */
	//@EJB
	//private SessionInformation sessionInformation;
	
	@EJB
	private LoginService loginService;

	
	private String email;
	private String password;
	
	private User currentUser;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Indicates if the user has already been identified.
	 * 
	 * @return <code>true</code> if the user is logged in, <code>false</code> otherwise.
	 */
	public boolean isLoggedIn() {
		return currentUser != null; //sessionInformation.getCurrentUser() != null;
	}
	
	/**
	 * Provides the current authenticated user.
	 * 
	 * @return The Academic object that represents the user that has been authenticated in this session.
	 */
	public User getCurrentUser() {
		return currentUser;//sessionInformation.getCurrentUser();
	}

	/**
	 * Provides the current date/time.
	 * 
	 * @return A Date object representing the date/time the method was called.
	 */
	//public Date getNow() {
	//	return new Date(System.currentTimeMillis());
	//}

	/**
	 * Provides the expiration date/time for the user session. This makes it possible to warn the user when his session
	 * will expire.
	 * 
	 * @return A Date object representing the date/time of the user's session expiration.
	 */
	/*public Date getSessionExpirationTime() {
		Date expTime = null;

		// Attempts to retrieve this information from the external context.
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			long expTimeMillis = session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000;
			expTime = new Date(expTimeMillis);
		}

		// If it could not be retrieved from the external context, use default of 30 minutes.
		if (expTime == null) {
			expTime = new Date(System.currentTimeMillis() + 30 * 60000);
		}
		
		return expTime;
	}*/

	/**
	 * Accesses the Login service to authenticate the user given his email and password.
	 */
	public String login() {		
		/*
		try {
			sessionInformation.login(email, password);
		}
		catch (LoginFailedException e) {
			// Checks if it's a normal login exception (wrong username or password) or not.
			switch (e.getReason()) {
			case INCORRECT_PASSWORD:
			case UNKNOWN_USERNAME:
				addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "login.error.nomatch.summary", "login.error.nomatch.detail");
				return null;

			default:
				addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "login.error.nomatch.summary", new Object[0], "login.error.nomatch.detail", new Object[] { new Date(System.currentTimeMillis()) });
				return null;
			}
		}

		// If everything is OK, redirect back to the home screen.*/
		try {
			// Uses the Session Information bean to authenticate the user.
			
			loginService.login(email, password);

			// Also authenticates on JAAS.
			// FIXME: is there a way to do this at the application package (in the EJB)?
			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				request.login(email, password);
			}
			catch (Exception e) {
				throw new LoginFailedException(e, LoginFailedReason.NO_HTTP_REQUEST);
			}
		}
		catch (LoginFailedException e) {
			// Checks if it's a normal login exception (wrong username or password) or not.
			switch (e.getReason()) {
			case INCORRECT_PASSWORD:
			case UNKNOWN_USERNAME:
				// Normal login exception (invalid usernaem or password). Report the error to the user.
				addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_ERROR, "login.error.nomatch.summary", "login.error.nomatch.detail");
				return null;

			default:
				// System failure exception. Report a fatal error and ask the user to contact the administrators.
				addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "login.error.fatal.summary", e.toString());
				return null;
			}
		}

		// If everything is OK, stores the current user and redirects back to the home screen.
		currentUser = loginService.getCurrentUser();
		return "core/home.xhtml?faces-redirect=true";
	}
	
	/*
	public String logout() {
		sessionInformation.logout();
		return "/index.xhtml?faces-redirect=true";
	}
	*/
}

