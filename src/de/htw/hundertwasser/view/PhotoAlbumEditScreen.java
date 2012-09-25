package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.backend.ElementStorage;
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
		mover.setPreferredSize(new Dimension(0, 50));
		mover.setBackground(Color.WHITE);
		centralPanel.add(mover, BorderLayout.NORTH);
		JLabel albumName = new JLabel();
		albumName.setText("Name of the album:" + photoalbum.getName());
		albumName.setHorizontalTextPosition(SwingConstants.CENTER);
		albumName.setHorizontalAlignment(SwingConstants.CENTER);
		albumName.setFont(new Font("Arial", Font.BOLD, 14));
		albumName.setBackground(Color.GRAY);
		
		mover.add(albumName);
		
		navigationBar.selectPhotoAlbumElement(photoalbum.getName());
		setState(Frame.MAXIMIZED_BOTH);
		
		navigationBar.addNavBarPhotoBoxObserver(thumbnailBar);
//		navigationBar.selectPhotoBoxElement(photobox.getName());
//		ElementStorage.get
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
