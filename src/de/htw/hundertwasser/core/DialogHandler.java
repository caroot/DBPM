package de.htw.hundertwasser.core;

import javax.swing.JOptionPane;

public class DialogHandler {

	/**
	 * Methode, die einen InputDialog erzeugt.
	 * @param message
	 * @param title
	 * @return Inserted String, ore null, if cancle iss pressed.
	 */
	public static String inputDialog(String message, String title) {
		String newName = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
		return newName;
	}
	
	public static void chooseSource() {
		
	}
}
