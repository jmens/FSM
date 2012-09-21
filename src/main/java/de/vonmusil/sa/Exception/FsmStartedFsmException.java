package de.vonmusil.sa.Exception;

public class FsmStartedFsmException extends UnexpectedSimpleFsmException {

	private static final long serialVersionUID = 1L;

	public FsmStartedFsmException() {
		super();
	}

	public FsmStartedFsmException(String message, Throwable cause) {
		super(message, cause);
	}

	public FsmStartedFsmException(String message) {
		super(message);
	}

	public FsmStartedFsmException(Throwable cause) {
		super(cause);
	}
}
