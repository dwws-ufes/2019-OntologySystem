package br.ufes.informatica.rationalontology.core.application.login;

import br.ufes.informatica.rationalontology.core.domain.User;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
public class LoginEvent {
	/** TODO: document this field. */
	private User user;

	/** Constructor. */
	public LoginEvent(User user) {
		this.user = user;
	}

	/** Getter for academic. */
	public User getAcademic() {
		return user;
	}
}
