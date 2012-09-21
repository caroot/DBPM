package de.htw.hundertwasser.custom.error;

/**
 * This exception occurs if a user chooses a File-Object that is not a directory
 * 
 * @author daniel rhein
 * 
 */
public class ChoosenFileNotAFolderException extends Throwable {

	// Constants
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public ChoosenFileNotAFolderException() {
		super();
	}

	/**
	 * Constructor with a certain message
	 * 
	 * @param message
	 */
	public ChoosenFileNotAFolderException(String message) {
		super(message);
	}

}
