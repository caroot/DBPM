package de.htw.test;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class JNumberOnlyTextField extends JTextField {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	Sicherung
//	final static String BAD_CHARS 
//    	= "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
	final static String BAD_CHARS 
		= "`~!@#$%^&*()_+=\\|\"':;?/><, ";

	public void processKeyEvent(KeyEvent ev) {

		char c = ev.getKeyChar();

		try {
			// Use this to enable only one '.' in the text field
			if ( c == '.' && getDocument().getText(0, getDocument().getLength()).indexOf('.') >= 0 )
				ev.consume();
			// Use this to deny a '.' as first character
			if ( c == '.' && getDocument().getLength() == 0 )
				ev.consume();
			// Use this to deny a 0 as second character if a 0 is the first character
			if ( c == '0' && getDocument().getText(0, getDocument().getLength()).indexOf('0') >= 0
					&& getDocument().getLength() == 1 )
				ev.consume();
		} catch (BadLocationException e) {
			// Should not happen
			e.printStackTrace();
		}
		
		if((Character.isLetter(c) && !ev.isAltDown()) || BAD_CHARS.indexOf(c) > -1) {
			ev.consume();
        	return;
		}
		// Change the length to 1 to allow a "-" in the front of the numbers.
     	if(c == '-' && getDocument().getLength() >= 0) 
     		ev.consume();
     	else super.processKeyEvent(ev);
	}
 
}
