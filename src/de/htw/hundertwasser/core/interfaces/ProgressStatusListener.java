package de.htw.hundertwasser.core.interfaces;

import java.util.EventObject;

/**
 * This listener listen for the statusinformation whithin the Progressprocess.
 * @author daniel
 *
 */
public interface ProgressStatusListener {
	/**
	 * Emits the progress status.
	 * @param statusinformation
	 */
	void handleProgressStatusEvent(EventObject statusinformation);
}
