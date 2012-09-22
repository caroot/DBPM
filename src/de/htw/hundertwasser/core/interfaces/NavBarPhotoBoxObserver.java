package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoBox;
/**
 * The NavigationBarObserver looking for the Navigationbar. </br>
 * If the Navigationbar is selected, the method is emitted.
 * @author daniel
 *
 */
public interface NavBarPhotoBoxObserver {
	
	/**
	 * This is the method that will transfer the Fotobox to the Thumbnailbar.
	 * @param photobox
	 */
	public void receivePhotoBox(PhotoBox photobox);
	
}
