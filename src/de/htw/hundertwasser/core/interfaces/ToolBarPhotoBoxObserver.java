package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;

public interface ToolBarPhotoBoxObserver {

	/**
	 * This methode transmitts the photo and the message to the observers
	 * 
	 * @param photo
	 * @param message	
	 */
	public void receiveMessage(Photo photo, ToolBarObserversMessage message);
}
