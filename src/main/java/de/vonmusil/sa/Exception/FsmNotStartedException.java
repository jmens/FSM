package de.vonmusil.sa.Exception;

public class FsmNotStartedException extends UnexpectedSimpleFsmException {

	private static final long serialVersionUID = 1L;

	public FsmNotStartedException() {
		super();
	}

	public FsmNotStartedException(String message, Throwable cause) {
		super(message, cause);
	}

	public FsmNotStartedException(String message) {
		super(message);
	}

	public FsmNotStartedException(Throwable cause) {
		super(cause);
	}
}
