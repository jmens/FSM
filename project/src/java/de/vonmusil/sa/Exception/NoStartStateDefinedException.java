package de.vonmusil.sa.Exception;

public class NoStartStateDefinedException extends UnexpectedSimpleFsmException {

	private static final long serialVersionUID = 1L;

	public NoStartStateDefinedException() {
		super();
	}

	public NoStartStateDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoStartStateDefinedException(String message) {
		super(message);
	}

	public NoStartStateDefinedException(Throwable cause) {
		super(cause);
	}
}
