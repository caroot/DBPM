package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoAlbum;
/**
 * The NavigationBarObserver looking for the Navigationbar. </br>
 * If the Navigationbar is selected, the method is emitted.
 * @author daniel
 *
 */
public interface NavBarPhotoAlbumObserver {
	
	/**
	 * This is the method that will transfer the PhotoAlbum to the CentralElement.
	 * @param photobox
	 */
	public void receivePhotoAlbum(PhotoAlbum photoalbum);
	
}
