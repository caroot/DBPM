package de.htw.hundertwasser.core.interfaces;

import java.util.EventObject;

/**
 * This StartUpEventListener will handle Events that are used for the Startup
 * @author daniel
 *
 */
public interface ProgressStartUpEventListener {

	/**
	 * Emits the progress status.
	 * @param event
	 */
	void handleProgressStartUpEvent(EventObject event);
}
