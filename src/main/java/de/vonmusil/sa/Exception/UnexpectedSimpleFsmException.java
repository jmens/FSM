package de.vonmusil.sa.Exception;

public class UnexpectedSimpleFsmException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnexpectedSimpleFsmException() {
		super();
	}

	public UnexpectedSimpleFsmException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedSimpleFsmException(String message) {
		super(message);
	}

	public UnexpectedSimpleFsmException(Throwable cause) {
		super(cause);
	}
}
