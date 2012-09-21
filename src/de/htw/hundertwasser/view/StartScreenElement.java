package de.htw.hundertwasser.view;


import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.core.DialogHandler;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Klasse die ein bestimmtes Photoalbum oder Photobox vom Startscrenn aus ansprechen kann. (Und der Hinzuf�gen Knopf)
 * @author Fabian
 *
 */
public class StartScreenElement extends JPanel {
	private static final long serialVersionUID = 1L;
	//Konstanten
	public static final int ELEMENT = 0;
	public static final int ADDITION = 1;
	public static final int ALBUM = 0;
	public static final int BOX = 1;
	public static final String ALBUMSTR = "Album";
	public static final String BOXSTR = "Box";
	public static final String DEFAULT_NAME_ALBUM = "New Photoalbum";
	public static final String DEFAULT_NAME_BOX = "New Photobox";
	
	//variablen

	private static StartScreenToolPanel chosenElementToolPanel = null;
	private int elementTyp;
	private JPanel parentPanel = null;
	private JButton elementButton = null;
	private Object element = null; //<-- Ein Album oder eine Box, je nachdem was elementTyp ist.
	private String elementName = null;
	public StartScreenElement(int elementTyp, int panelTyp, JPanel parentPanel, String name) {
		elementName = name;
		this.elementTyp = elementTyp;
		if(panelTyp == ADDITION) {
			makeAddButton();
		}
		if(panelTyp == ELEMENT)
			makeElementButton();
		this.parentPanel = parentPanel;
		setPreferredSize(StartScreen.getElementSize());
		setLayout(new GridLayout(2, 1, 1, 0));
		setBackground(StartScreen.getBGColor());
		repaint();
	}

	private void makeElementButton() {
		try {
			if(elementTyp == ALBUM) {
				if(elementName == null)
					elementName = DEFAULT_NAME_ALBUM;
				Icon elementIcon;
					elementIcon = RessourcenEnummeration.PHOTOALBUM_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			} else {
				if(elementName == null)
					elementName = DEFAULT_NAME_BOX; 
				Icon elementIcon = RessourcenEnummeration.PHOTOBOX_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			}
			elementButton.setHorizontalTextPosition(SwingConstants.CENTER);
			elementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			elementButton.setFont(RessourcenEnummeration.FONT_CALIBRI.getFont().deriveFont(14f));
			ActionListener addListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openToolPanel();
					elementButton.setBackground(Color.LIGHT_GRAY);
					parentPanel.validate();
					parentPanel.repaint();
				}
			};
			elementButton.addActionListener(addListen);
			
			elementButton.setBackground(StartScreen.getBGColor());
			
			add(elementButton);
		} catch (IOException e1) {
			// TODO Ausgabe des Fehlers
			e1.printStackTrace();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// added Tooltipps (tim)
	private void makeAddButton() {
		Icon addIcon;
		String helpAddButton;
		try {
			if(elementTyp == ALBUM) {
				addIcon = RessourcenEnummeration.PHOTOALBUM_HINZUFUEGEN_NEU.getIcon();
				helpAddButton = "Add a new Photo Album";
			} else {
				addIcon = RessourcenEnummeration.PHOTOBOX_HINZUFUEGEN_NEU.getIcon();
				helpAddButton = "Add a new Photo Box";
			}	
			JButton addButton = new JButton(addIcon);
			
			addButton.setBackground(StartScreen.getBGColor());
			addButton.setToolTipText( helpAddButton );
		
		ActionListener addListen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				if(elementTyp == StartScreenElement.ALBUM) {
					name = DialogHandler.inputDialog("Insert name here", "New PhotoBox");
					if(name == null)
						return; //cancel if no Name was inserted
					element = new PhotoAlbum();
//					((PhotoAlbum) element).setName(name);
					StartScreen.noOfAlbums++;
					StartScreen.retextAlbum();
				} else {
					name = DialogHandler.inputDialog("Insert name here", "New PhotoBox");
					if(name == null)
						return; //cancel if no Name was inserted
					String path = DialogHandler.chooseSource();
					if(path == null)
						return; //cancel if no Path was chosen
					JProgressBar progress = DialogHandler.showProgressBar();
					element = new PhotoBox(path);
					//TODO Photobox mit Photos f�llen, (Folder Manager i-wan)
					((PhotoBox) element).setName(name);
					StartScreen.noOfBoxes++;
					StartScreen.retextBox();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					((Window) progress.getParent().getParent().getParent().getParent()).dispose();
				}
				parentPanel.add(new StartScreenElement(elementTyp, ELEMENT, parentPanel, name));
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
			chosenElement.elementButton.setBackground(StartScreen.getBGColor());
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
	 * Methode, die das Element aus dem System L�scht, bzw die relevanten Buttons von der Oberfl�che.
	 */
	public void delete() {
		JPanel parent = (JPanel) getParent();
		parent.remove(this);
		parent.repaint();
		parent.validate();
		if(elementTyp == ALBUM) { //TODO L�schen der boxen und alben
//			((PhotoAlbum) element).destroy();
			StartScreen.noOfAlbums--;
			StartScreen.retextAlbum();
		} else {
//			((PhotoBox) element).destroy();
			StartScreen.noOfBoxes--;
			StartScreen.retextBox();
		}
		parent.setSize(0,0); //Reset der gr��e des Panels
	}
	
	public int getTyp() {
		return elementTyp;
	}
	
	/**
	 * Methode, that returns the assosiated Photo Box or Album
	 * @return PhotoBox or PhotoAlbum, depends on the Typ.
	 */
	public Object getElement() {
		return element;
	}

}
