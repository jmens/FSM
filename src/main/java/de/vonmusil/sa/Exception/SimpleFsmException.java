package de.vonmusil.sa.Exception;

public class SimpleFsmException extends Exception {

	private static final long serialVersionUID = 1L;

	public SimpleFsmException() {
		super();
	}

	public SimpleFsmException(String message, Throwable cause) {
		super(message, cause);
	}

	public SimpleFsmException(String message) {
		super(message);
	}

	public SimpleFsmException(Throwable cause) {
		super(cause);
	}

}
