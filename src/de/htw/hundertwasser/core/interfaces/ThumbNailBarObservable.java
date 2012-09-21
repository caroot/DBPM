package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.Photo;

public interface ThumbNailBarObservable {
	/**
	 * AddThumbNailBarObserver
	 * @param observer
	 */
	void addThumbNailBarObserver(ThumbNailBarObserver observer);
	/**
	 * Removes the ThumnailbarObserver
	 * @param observer
	 */
	void removeThumbNailBarObserver(ThumbNailBarObserver observer);
	/**
	 * Send Message to All Observers
	 * @param photo
	 */
	void sendMessage(Photo photo);
}
