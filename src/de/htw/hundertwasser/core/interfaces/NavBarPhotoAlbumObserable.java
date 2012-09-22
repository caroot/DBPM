package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoAlbum;

/**
 * This Class represents a carrier for the NavBarPhotBoxObserver
 * @author daniel
 *
 */
public interface NavBarPhotoAlbumObserable {
	/**
	 * Add ThumbNailBarObserver
	 * @param observer
	 */
	void addNavBarPhotoAlbumObserver(NavBarPhotoAlbumObserver observer);
	/**
	 * Removes the ThumnailbarObserver
	 * @param observer
	 */
	void removeNavBarPhotoAlbumObserver(NavBarPhotoAlbumObserver observer);
	/**
	 * Send Message to All Observers
	 * @param photoAlbum
	 */
	void sendNavBarPhotoAlbumMessage(PhotoAlbum photoAlbum);
}
