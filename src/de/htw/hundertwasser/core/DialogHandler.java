package de.htw.hundertwasser.core;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogHandler {

	/**
	 * Methode, which creates an input dialog
	 * @param message
	 * @param title
	 * @return Inserted String, ore null, if cancle iss pressed.
	 */
	public static String inputDialog(String message, String title) {
		String newName = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
		return newName;
	}
	
	/**
	 * Method, which shows a file chooser that filters from various image files.
	 * Directories choosable.
	 * @return the chosen file or directory, or null, if canceled is pressed.
	 */
	public static String chooseSource() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Images", "jpg", "gif", "jpeg", "tiff", "png");
	    chooser.setFileFilter(filter);
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	       System.out.println("You chose to open this file: " +
//	            chooser.getSelectedFile().getPath());
		    return chooser.getSelectedFile().getPath();
	    }
	    return null;
	}
	
	public static JProgressBar showProgressBar() {
		JProgressBar progressBar = new JProgressBar();
		JFrame progressFrame = new JFrame();
		progressFrame.add(progressBar);
		progressFrame.setVisible(true);
		
		//when the task of (initially) unknown length begins:
		progressBar.setIndeterminate(true);
		//do some work; get length of task...
		progressBar.setMaximum(10);
		progressBar.setValue(5);
		progressBar.setIndeterminate(false);
		return progressBar;
	}
}
