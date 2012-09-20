package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htw.hundertwasser.core.ImageViewer;
import de.htw.hundertwasser.res.RessourcenEnummeration;

public class PhotoAlbumEditScreen extends EditScreen{
	
	
	private static final long serialVersionUID = 1L;



	public PhotoAlbumEditScreen() {
		//TODO Laden des Photoalbums
			
		super();
		try {
			imgViewer.setImage(RessourcenEnummeration.getImage(RessourcenEnummeration.ALBUM));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel mover = new JPanel();
		mover.setPreferredSize(new Dimension(0,100));
		mover.setBackground(Color.WHITE);
		centralPanel.add(mover, BorderLayout.NORTH);

		imgPanel.add(imgViewer,BorderLayout.CENTER);

	}
	
	
	public ImageViewer getImgViewer(){
		//TODO PhotoAlbumView in imageViewer umwandeln
		return imgViewer;
	}
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		JOptionPane.showConfirmDialog(this, "Would you like to save the current Project" , "Confirm Exit", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
		dispose();
		
		
	}





}
