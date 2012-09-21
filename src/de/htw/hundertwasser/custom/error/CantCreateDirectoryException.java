package de.htw.hundertwasser.custom.error;

public class CantCreateDirectoryException extends Throwable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public CantCreateDirectoryException()
	{
		super();
	}
	
	/**
	 * Konstruktor with message
	 * @param message
	 */
	public CantCreateDirectoryException(String message)
	{
		super(message);
	}
}
