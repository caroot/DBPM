package de.htw.hundertwasser.core.interfaces;

import de.htw.hundertwasser.core.PhotoBox;

public interface FolderManagerObservable {

	/**
	 * Add FolderManagerObserver
	 * @param observer
	 */
	void addFolderManagerObserver(FolderManagerObserver observer);
	/**
	 * Removes the FolderManagerObserver
	 * @param observer
	 */
	void removeFolderManagerObserver(FolderManagerObserver observer);
	/**
	 * Send Message to All Observers
	 */
	void sendFolderManagerMessage();
}
