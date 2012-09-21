package de.htw.hundertwasser.custom.error;

/**
 * This Error occurs if you're unable to do operations on a File caused by
 * insufficent privileges.
 * 
 * @author daniel
 * 
 */
public class InsufficientPrivilegesException extends Throwable {

	// Constants
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public InsufficientPrivilegesException() {
		super();
	}

	/**
	 * Constructor with a certain message
	 * 
	 * @param message
	 */
	public InsufficientPrivilegesException(String message) {
		super(message);
	}
}
