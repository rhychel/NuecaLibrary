package net.nueca.exceptions;

/**
 * 
 * Exception when the request result is not valid.
 * 
 * @author rhymart
 *
 */
public class RequestException extends Exception {
	
	private String message;
	
	public RequestException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
