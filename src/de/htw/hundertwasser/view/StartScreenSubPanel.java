package de.htw.hundertwasser.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Klasse die zum Anzeigen aller Photoboxen und Photoalben genutzt wird.
 * @author Fabian
 *
 */
public class StartScreenSubPanel extends JPanel {
	
	//Variablen
	JPanel subElements = null;
	
	public StartScreenSubPanel() {
		setLayout(new BorderLayout(5, 5));
	}
	
	/**
	 * Methode die einen Hinzuf�gen Knopf und das Scrollpane initialisiert
	 * @param subPanel JPanel: Panel in dem die einzelnen Elemente Sp�ter angezeigt werden sollen
	 * @param typ int: Typ des Panels. M�glichkeiten: StartScreenElement.ALBUM, StartScreenElement.BOX.
	 */
	public void initialiseElements(JPanel subPanel, int typ) {
		if(subElements != null) {
			//TODO Fehlerausgabe, wenn so iss...
			return;
		}
		JPanel mainSubPanel = new JPanel();
		mainSubPanel.setBackground(StartScreen.getBGColor());
		subElements = subPanel;
		subElements.setBackground(StartScreen.getBGColor());
		JScrollPane elementScrollPane = new JScrollPane(subElements, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		elementScrollPane.setBorder(BorderFactory.createEmptyBorder());
//		Dimension scrollSize = new Dimension(1002, 300);
		elementScrollPane.setPreferredSize(StartScreen.getScrollSize());
		mainSubPanel.add(elementScrollPane);
		if(typ == StartScreenElement.ALBUM)
			mainSubPanel.add(new StartScreenElement(StartScreenElement.ALBUM, StartScreenElement.ADDITION, subElements, ""));
		else
			mainSubPanel.add(new StartScreenElement(StartScreenElement.BOX, StartScreenElement.ADDITION, subElements, ""));
		add(mainSubPanel);
	}
}
