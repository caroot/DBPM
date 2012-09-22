package de.htw.hundertwasser.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * 
 * Class that creates the PhotoBoxFullScreen
 * 
 * @author johannes schramm
 * 
 */

public class PhotoBoxFullScreen extends FullScreen {

	private static final long serialVersionUID = 1L;

	public PhotoBoxFullScreen(BufferedImage bui) {
		super(bui);
		exitButton.addActionListener(getExitButtonListener());
		leftArrow.addActionListener(getLeftArrowListener());
		rightArrow.addActionListener(getRightArrowListener());
	}

	// exitButton
	public ActionListener getExitButtonListener() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
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
