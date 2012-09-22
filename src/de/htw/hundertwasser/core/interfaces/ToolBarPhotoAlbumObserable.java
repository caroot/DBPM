package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;
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
	 * FullScreenMessage 
	 * @param photoAlbum
	 */
	void fullScreenMessage(PhotoAlbum photoAlbum);
	/**
	 * Changes the Color of the PhotoAlbum to Black and White
	 * @param photoAlbum
	 */
	void blackAndWhiteMessage(PhotoAlbum photoAlbum);
	/**
	 * Rename the PhotoAlbum to a certain name 
	 * @param photoAlbum
	 */
	void renameMessage(PhotoAlbum photoAlbum);
	/**
	 * ZoomMessage creates a Message to zoom into the PhotoAlbum
	 * @param photoAlbum
	 */
	void zoomMessage(PhotoAlbum photoAlbum);
	/**
	 * CutMessage creates a Message to Cut the PhotoAlbum
	 * @param photoAlbum
	 */
	void cutMessage(PhotoAlbum photoAlbum);
	/**
	 * Creates a Message to Print the eternal PhotoAlbum
	 * @param photoAlbum
	 */
	void printMessage(PhotoAlbum photoAlbum);
	/**
	 * Creates a Message  to Delete the PhotoAlbum
	 * @param photoAlbum
	 */
	void deleteMessage(PhotoAlbum photoAlbum);
	/**
	 * Send a Certain Message to all collaborators
	 * @param message
	 * @param photoAlbum
	 */
	void sendMessage(ToolBarObserversMessage message,PhotoAlbum photoAlbum);
	
}
