

package de.htw.hundertwasser.view;



/**
 * Klasse die zum anzeigen des gewünschten Fotoalbums im Fullscreen Modus genutzt wird
 * @author johannes
 *
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Klasse die zum anzeigen des gewünschten Fotoboxenim Fullscreen Modus genutzt wird
 * @author johannes
 *
 */

public class PhotoBoxFullScreen extends FullScreen {
	
	public PhotoBoxFullScreen (){
		super();
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
	
	// main method
	 
	public static void main(String[] args) {
		PhotoBoxFullScreen fsnew = new PhotoBoxFullScreen();
		fsnew.setVisible(true);
  }
}

