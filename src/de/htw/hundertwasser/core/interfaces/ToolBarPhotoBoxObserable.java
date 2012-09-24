package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;

/**
 * This Class represents a carrier for the NavBarPhotBoxObserver
 * @author daniel
 *
 */
public interface ToolBarPhotoBoxObserable {
	/**
	 * AddThumbNailBarObserver
	 * @param observer
	 */
	void addToolBarPhotoBoxObserver(NavBarPhotoAlbumObserver observer);
	/**
	 * Removes the ThumnailbarObserver
	 * @param observer
	 */
	void removeToolBarPhotoBoxObserver(NavBarPhotoAlbumObserver observer);
	/**
	 * Send Message to All Observers
	 * @param photo
	 * @param message
	 */
	void sendMessage(Photo photo, ToolBarObserversMessage message);
}
