package net.nueca.imonggo.exceptions;

/**
 * 
 * Exception when login credentials are not valid.
 * 
 * @author rhymart
 *
 */
public class LoginException extends Exception {
	
	private String message = "";
	
	public LoginException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
