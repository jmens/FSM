package de.vonmusil.sa.Exception;

/**
 * Raised if the user tries to register the same event on the same state more than one time. 
 * 
 * @author musil
 *
 */
public class TooMuchEventsException extends UnexpectedSimpleFsmException {

	private static final long serialVersionUID = 1L;

	public TooMuchEventsException() {
		super();
	}

	public TooMuchEventsException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooMuchEventsException(String message) {
		super(message);
	}

	public TooMuchEventsException(Throwable cause) {
		super(cause);
	}
}
