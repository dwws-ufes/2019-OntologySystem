package br.ufes.informatica.rationalontology.core.exception;

/**
 * Application exception that represents the fact that the system installation process has failed to complete.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
public class RegistrationFailedException extends Exception {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Constructor from superclass. */
	public RegistrationFailedException(Throwable t) {
		super(t);
	}
}
