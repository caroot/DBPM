package de.htw.hundertwasser.view;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;

/**
 * Klasse die zum Anzeigen aller Photoboxen und Photoalben genutzt wird.
 * @author Fabian
 *
 */
public class StartScreenSubPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//Error Constants
	public static final String ERROR_TITLE = "StartScreenSubPanel Error";
	public static final String ELEMENTS_NULL = "Oh no, the panel is gone, that shouldnt happen.";

	
	//Variable
	JPanel subElements = null;
	
	public StartScreenSubPanel() {
		setLayout(new BorderLayout(5, 5));
	}
	
	/**
	 * This Method initializes the Scrollpane and adds the Add Button.
	 * @param subPanel JPanel: Here will the elements be inserted.
	 * @param typ int: Type of panel: StartScreenElement.ALBUM, StartScreenElement.BOX.
	 */
	public void initialiseElements(JPanel subPanel, int typ) {
		if(subElements != null) {
		ErrorMessageDialog.showMessage(null, ELEMENTS_NULL, ERROR_TITLE);
			return;
		}
		JPanel mainSubPanel = new JPanel();
		mainSubPanel.setBackground(StartScreen.getBGColor());
		subElements = subPanel;
		subElements.setBackground(StartScreen.getBGColor());
		JScrollPane elementScrollPane = new JScrollPane(subElements, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		elementScrollPane.setBorder(BorderFactory.createEmptyBorder());
		elementScrollPane.setPreferredSize(StartScreen.getScrollSize());
		mainSubPanel.add(elementScrollPane);
		if(typ == StartScreenElement.ALBUM)
			mainSubPanel.add(new StartScreenElement(StartScreenElement.ALBUM, StartScreenElement.ADDITION, subElements, ""));
		else
			mainSubPanel.add(new StartScreenElement(StartScreenElement.BOX, StartScreenElement.ADDITION, subElements, ""));
		add(mainSubPanel);
	}
}
