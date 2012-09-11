package de.htw.hundertwasser.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Klasse die ein bestimmtes Photoalbum oder Photobox vom Startscrenn aus ansprechen kann. (Und der Hinzufügen Knopf)
 * @author Fabian
 *
 */
public class StartScreenElement extends JPanel {
	//Konstanten
	public static final int ELEMENT = 0;
	public static final int ADDITION = 1;
	public static final int ALBUM = 0;
	public static final int BOX = 1;
	public static final String ALBUMSTR = "Album";
	public static final String BOXSTR = "Box";
	public static final String DEFAULT_NAME_ALBUM = "Neues Photoalbum";
	public static final String DEFAULT_NAME_BOX = "Neue Photobox";
	
	//variablen

	private static StartScreenToolPanel chosenElementToolPanel = null;
	private URL buttonImg = null;
	private int elementTyp;
	private JPanel parentPanel = null;
	private JButton elementButton = null;
	private Object element = null; //<-- Ein Album oder eine Box, je nachdem was elementTyp ist.
	public StartScreenElement(int elementTyp, int panelTyp, JPanel parentPanel) {
		
		this.elementTyp = elementTyp;
		if(panelTyp == ADDITION)
			initializeSubPanel();
		if(panelTyp == ELEMENT)
			makeElementButton();
		this.parentPanel = parentPanel;
		Dimension size = new Dimension(325,286);
		setPreferredSize(size);
		setLayout(new GridLayout(2, 1, 1, 0));
		setBackground(StartScreen.getBGColor());
		repaint();
	}

	private void makeElementButton() {
		try {
			if(elementTyp == ALBUM) {
				Icon elementIcon;
					elementIcon = RessourcenEnummeration.PHOTOALBUM.getIcon();
				elementButton = new JButton(DEFAULT_NAME_ALBUM, elementIcon);
			} else { 
				Icon elementIcon = RessourcenEnummeration.PHOTOBOX.getIcon();
				elementButton = new JButton(DEFAULT_NAME_BOX, elementIcon);
			}
			elementButton.setHorizontalTextPosition(SwingConstants.CENTER);
			elementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			ActionListener addListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openToolPanel();
					parentPanel.validate();
					parentPanel.repaint();
				}
			};
			elementButton.addActionListener(addListen);
//			button.setBackground(StartScreen.getBGColor());
			add(elementButton);
		} catch (IOException e1) {
			// TODO Ausgabe des Fehlers
			e1.printStackTrace();
		}
	}

	private void initializeSubPanel() {
		Icon addIcon;
		try {
			if(elementTyp == ALBUM) {
				addIcon = RessourcenEnummeration.PHOTOALBUM_HINZUFUEGEN.getIcon();
			} else {
				addIcon = RessourcenEnummeration.PHOTOBOX_HINZUFUEGEN.getIcon();
			}	
			JButton addButton = new JButton(addIcon);
//			addButton.setBackground(StartScreen.getBGColor());
		
		ActionListener addListen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elementTyp == StartScreenElement.ALBUM)
					element = new PhotoAlbum();
				else
					element = new PhotoBox();
				parentPanel.add(new StartScreenElement(elementTyp, ELEMENT, parentPanel));
				parentPanel.getParent().validate();
				
			}
		};
		addButton.addActionListener(addListen);
		add(addButton);
		} catch (IOException e1) {
			// TODO Ausgabe des Fehlers
			e1.printStackTrace();
		}
	}
	
	
	
	private void openToolPanel() {
		StartScreenElement chosenElement;
		if(chosenElementToolPanel != null) {
			chosenElement = (StartScreenElement) chosenElementToolPanel.getParent();
			chosenElement.remove(chosenElementToolPanel);
			chosenElement.parentPanel.validate();
			chosenElement.parentPanel.repaint();
		}
		chosenElementToolPanel = new StartScreenToolPanel();
		add(chosenElementToolPanel);
		parentPanel.validate();
		parentPanel.repaint();
	}

	public void changeName(String newName) {
		elementButton.setText(newName);
		repaint();
		validate();
	}

	/**
	 * Methode, die das Element aus dem System Löscht, bzw die relevanten Buttons von der Oberfläche.
	 */
	public void delete() {
		JPanel parent = (JPanel) getParent();
		parent.remove(this);
		parent.repaint();
		parent.validate();
		parent.setSize(0,0); //Reset der größe des Panels
	}

}