package main.java.com.error;

public class EmailNotCorrectTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotCorrectTypeException(String error) {
		super(error);
	}
}
