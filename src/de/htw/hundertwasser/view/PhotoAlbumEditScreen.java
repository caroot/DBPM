package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htw.hundertwasser.core.ImageViewer;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Class for the PhotoAlbumEditScreen
 */
public class PhotoAlbumEditScreen extends EditScreen {

	private static final long serialVersionUID = 1L;

	public PhotoAlbumEditScreen(PhotoAlbum photoalbum) {
		// TODO Laden des Photoalbums

		super();
		try {
			imgViewer.setImage(RessourcenEnummeration
					.getImage(RessourcenEnummeration.ALBUM));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel mover = new JPanel();
		mover.setPreferredSize(new Dimension(0, 100));
		mover.setBackground(Color.WHITE);
		centralPanel.add(mover, BorderLayout.NORTH);

		setState(Frame.MAXIMIZED_BOTH);
	}

	/**
	 * @see de.htw.hundertwasser.view.EditScreen#getImgViewer()
	 */
	public ImageViewer getImgViewer() {
		// TODO PhotoAlbumView in imageViewer umwandeln
		return imgViewer;
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		JOptionPane.showConfirmDialog(this,
				"Would you like to save the current Project", "Confirm Exit",
				JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		dispose();

	}

}
