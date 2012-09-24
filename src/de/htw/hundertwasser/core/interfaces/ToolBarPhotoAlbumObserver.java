package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoAlbum;

public interface ToolBarPhotoAlbumObserver {

	/**
	 * This methode transmitts the photoalbum and the message to the observers
	 * 
	 * @param photoalbum
	 * @param message
	 */
	public void receiveMessage(PhotoAlbum photoalbum, ToolBarObserversMessage message);
}
