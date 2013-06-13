package net.nueca.imonggo.exceptions;

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
