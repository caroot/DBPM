package de.htw.hundertwasser.custom.error;

/*
 * This Exception appears when an Directory can't be created
 */
public class CantCreateDirectoryException extends Throwable {

	// Constants
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public CantCreateDirectoryException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public CantCreateDirectoryException(String message) {
		super(message);
	}
}
