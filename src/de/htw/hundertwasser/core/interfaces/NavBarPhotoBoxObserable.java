package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoBox;

/**
 * This Class represents a carrier for the NavBarPhotBoxObserver
 * @author daniel
 *
 */
public interface NavBarPhotoBoxObserable {
	/**
	 * AddThumbNailBarObserver
	 * @param observer
	 */
	void addNavBarPhotoBoxObserver(NavBarPhotoBoxObserver observer);
	/**
	 * Removes the ThumnailbarObserver
	 * @param observer
	 */
	void removeNavBarPhotoBoxObserver(NavBarPhotoBoxObserver observer);
	/**
	 * Send Message to All Observers
	 * @param photoBox
	 */
	void sendMessage(PhotoBox photoBox);
}
