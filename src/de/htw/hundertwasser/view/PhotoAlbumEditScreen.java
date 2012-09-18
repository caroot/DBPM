package de.htw.hundertwasser.view;

import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class PhotoAlbumEditScreen extends EditScreen{
	
	
	private static final long serialVersionUID = 1L;


	public PhotoAlbumEditScreen() {
		
		super();
	}
	
	
	
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		JOptionPane.showConfirmDialog(this, "Would you like to save the current Project" , "Confirm Exit", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
		dispose();
		
		
	}





}
