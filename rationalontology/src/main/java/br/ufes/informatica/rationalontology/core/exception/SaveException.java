package br.ufes.informatica.rationalontology.core.exception;


public class SaveException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	private String reason;

	public SaveException(Throwable t, String reason) {
		super(t);
		this.reason = reason;
	}
	
	public String toString() {
		return  reason;
	}
}
