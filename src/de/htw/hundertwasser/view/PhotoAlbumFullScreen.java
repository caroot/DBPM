

package de.htw.hundertwasser.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.htw.hundertwasser.core.ImageViewer;



/**
 * Klasse die zum anzeigen des gewünschten Fotoalbums im Fullscreen Modus genutzt wird
 * @author johannes
 *
 */

public class PhotoAlbumFullScreen extends FullScreen {

	private static final long serialVersionUID = 1L;

	public PhotoAlbumFullScreen(ImageViewer imgViewer){
		super(imgViewer);
//		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{leftArrow, exitButton, rightArrow}));
		exitButton.addActionListener(getExitButtonListener());
		leftArrow.addActionListener(getLeftArrowListener());
		rightArrow.addActionListener(getRightArrowListener());
		setVisible(true);
		
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
	 
//	public static void main(String[] args) {
//		PhotoAlbumFullScreen fsnew = new PhotoAlbumFullScreen();
//		fsnew.setVisible(true);
//  }
}
