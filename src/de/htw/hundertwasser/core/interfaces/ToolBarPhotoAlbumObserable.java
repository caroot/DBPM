package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoAlbum;

/**
 * This Class represents a carrier for the NavBarPhotBoxObserver
 * @author daniel
 *
 */
public interface ToolBarPhotoAlbumObserable {
	/**
	 * AddThumbNailBarObserver
	 * @param observer
	 */
	void addToolBarPhotoAlbumObserver(ToolBarPhotoAlbumObserver observer);
	/**
	 * Removes the ThumnailbarObserver
	 * @param observer
	 */
	void removeToolBarPhotoAlbumObserver(ToolBarPhotoAlbumObserver observer);
	
	/**
	 * send message to all observers
	 * @param photoAlbum
	 * @param message
	 */
	void sendMessage(PhotoAlbum photoAlbum, ToolBarObserversMessage message);
	}
