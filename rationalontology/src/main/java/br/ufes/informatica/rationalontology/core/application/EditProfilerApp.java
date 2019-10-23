package br.ufes.informatica.rationalontology.core.application;

import java.io.Serializable;

import br.ufes.informatica.rationalontology.core.domain.User;
import br.ufes.informatica.rationalontology.core.exception.RegistrationFailedException;

public interface EditProfilerApp extends Serializable{
	public void save(User user) throws RegistrationFailedException;
	
	public void checkAuthorization();
}
