package br.ufes.informatica.rationalontology.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.informatica.rationalontology.core.application.EditProfilerApp;
import br.ufes.informatica.rationalontology.core.application.login.SessionInformation;
import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;

@Named @SessionScoped
public class EditProfilerController  extends JSFController {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EditProfilerApp editProfilereApp;
	
	private User user;
	
	private String repeatPassword;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	//*************************************************
	//*** Métodos de negócio
	//*************************************************
	public void ajaxCheckPasswords() {
		checkPasswords();
	}
	
	private boolean checkPasswords() {
		if (((repeatPassword != null) && (!repeatPassword.equals( user.getPassword()))) || ((repeatPassword == null) && (user.getPassword() != null))
				||  repeatPassword.trim().equals("") || user.getPassword().trim().equals("") ) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_WARN, "registration.passwordsDontMatch.summary", "registration.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}
	
	public String save() {
		// Check if passwords don't match. Add an error in that case.
		if (!checkPasswords()) return null;
		try {
			editProfilereApp.save(user);
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_INFO, "jbutler.message.successUpdate");
		}
		catch (RegistrationFailedException e) {
			addGlobalI18nMessage("msgs", FacesMessage.SEVERITY_FATAL, "registration.error.summary", e.getMessage());
			return null;
		}
		
		return null;
	}
	            
	public void onLoadForm() {
		editProfilereApp.checkAuthorization();
		user = SessionInformation.getInstance().getUsuarioLogado();
	}
	
}
