package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;
import de.htw.hundertwasser.core.PhotoAlbum;

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
	 */
	void fullScreenMessage(Photo photo);
	void blackAndWhiteMessage(Photo photo);
	void rename(Photo photo);
	void zoom(Photo photo);
	void cut(Photo photo);
	void print(Photo photo);
	void delete(Photo photo);
}
