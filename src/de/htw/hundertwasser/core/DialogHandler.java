package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.htw.hundertwasser.view.StartScreen;

/*
 * Class that creates the DialogHandlers
 * @author fabian hewer
 */
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
		chooser.setDialogTitle("Choose Source");
	    chooser.setFileFilter(de.htw.hundertwasser.backend.FileAcceptor.getFilter());
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	       System.out.println("You chose to open this file: " +
//	            chooser.getSelectedFile().getPath());
		    return chooser.getSelectedFile().getPath();
	    }
	    return null;
	}
	
	/**
	 * Method, which shows a progressbar and returns it to the calling method for further use
	 * @return JProgressBar to manage the progress.
	 */
	public static JProgressBar showProgressBar() {
		JProgressBar progressBar = new JProgressBar();
		JFrame progressFrame = new JFrame();
		progressFrame.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width-400) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-30) / 2
				);
		progressFrame.setSize(400,30);
		progressFrame.setUndecorated(true);
		progressFrame.add(progressBar);
		progressFrame.setVisible(true);
		progressFrame.setAlwaysOnTop(true);
		
		//when the task of (initially) unknown length begins:
		progressBar.setIndeterminate(true);
		//do some work; get length of task...
		progressBar.setMaximum(10);
		progressBar.setBackground(StartScreen.getBGColor());
		progressBar.setForeground(Color.BLUE);
//		progressBar.setValue(0);
//		progressBar.setValue(1);
//		progressBar.setValue(2);
//		progressBar.setValue(3);
//		progressBar.setValue(4);
//		progressBar.setValue(5);
//		progressBar.setValue(6);
//		progressBar.setValue(7);
//		progressBar.setValue(8);
//		progressBar.setValue(9);
//		progressBar.setValue(10);
		return progressBar;
	}
}
