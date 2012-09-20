

package de.htw.hundertwasser.view;



/**
 * Klasse die zum anzeigen des gewünschten Fotoalbums im Fullscreen Modus genutzt wird
 * @author johannes
 *
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.htw.hundertwasser.core.ImageViewer;



/**
 * Klasse die zum anzeigen des gewünschten Fotoboxenim Fullscreen Modus genutzt wird
 * @author johannes
 *
 */

public class PhotoBoxFullScreen extends FullScreen {
	

	private static final long serialVersionUID = 1L;

	public PhotoBoxFullScreen (ImageViewer iv){
		super(iv);
		exitButton.addActionListener(getExitButtonListener());
		leftArrow.addActionListener(getLeftArrowListener());
		rightArrow.addActionListener(getRightArrowListener());
	}
	
	// exitButton
	public ActionListener getExitButtonListener() {
		return new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dispose();}
		};
	}


	// right arrow
	
	public ActionListener getRightArrowListener() {
		return null;
   }

	// left arrow
	
	public ActionListener getLeftArrowListener() {
		
		return null;
	}
}

