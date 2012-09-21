package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;
/**
 * This is the Observer for the ThumbnailBar
 * @author daniel
 *
 */
public interface ThumbNailBarObserver {

	/**
	 * Determine the chosen photo
	 * @param photo
	 */
	public void setPhoto(Photo photo);
	
}
