package de.htw.hundertwasser.view;


import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.backend.FolderManager;
import de.htw.hundertwasser.core.DialogHandler;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Klasse die ein bestimmtes Photoalbum oder Photobox vom Startscrenn aus ansprechen kann. (Und der Hinzuf�gen Knopf)
 * @author Fabian, Tim
 *
 */
public class StartScreenElement extends JPanel {
	private static final long serialVersionUID = 1L;
	//Constants
	public static final int ELEMENT = 0;
	public static final int ADDITION = 1;
	public static final int ALBUM = 0;
	public static final int BOX = 1;
	public static final String ALBUMSTR = "Album";
	public static final String BOXSTR = "Box";
	public static final String DEFAULT_NAME_ALBUM = "New Photoalbum";
	public static final String DEFAULT_NAME_BOX = "New Photobox";
	
	//Error Constants
	public static final String ERROR_TITLE = "StartScreenElement Error";
	public static final String FAILURE_TITLE = "Nya... that was wrong!";
	public static final String NAME_EMPTY = "The name can not be empty!";
	
	//Variables
	private static StartScreenToolPanel chosenElementToolPanel = null;
	private int elementTyp;
	private JPanel parentPanel = null;
	private JButton elementButton = null;
	private Object element = null; //<-- ONE Album or Box, depending on parameter.
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

	/**
	 * This method builds the main button for an StartScreen element.
	 * That button opens the toolpanel.
	 */
	private void makeElementButton() {
		try {
			if(elementTyp == ALBUM) {
				Icon elementIcon = RessourcenEnummeration.PHOTOALBUM_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			} else {
				Icon elementIcon = RessourcenEnummeration.PHOTOBOX_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			}
			elementButton.setHorizontalTextPosition(SwingConstants.CENTER);
			elementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			elementButton.setFont(RessourcenEnummeration.FONT_CALIBRI.getFont().deriveFont(14f)); //Loads and resizes font
			ActionListener addListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openToolPanel();
					elementButton.setBackground(Color.LIGHT_GRAY); //Changes Button background when pressed, to shop, that it is.
					parentPanel.validate();
					parentPanel.repaint();
				}
			};
			elementButton.addActionListener(addListen);
			
			elementButton.setBackground(StartScreen.getBGColor());
			
			add(elementButton);
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE, ioe.getStackTrace().toString());
		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, onse.getMessage(), ERROR_TITLE, onse.getStackTrace().toString());
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, ffe.getMessage(), ERROR_TITLE, ffe.getStackTrace().toString());
		}
	}

	/**
	 * This method creates a StartScreenElement with the add button to create new StartScreenElements.
	 */
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
				try {
					String name;
					if(elementTyp == StartScreenElement.ALBUM) {
						name = DialogHandler.inputDialog("Insert name here", "New PhotoBox");
						if(name == null)
							return; //cancel if no Name was inserted
						if(name.trim().isEmpty()) {
							ErrorMessageDialog.showMessage(null, NAME_EMPTY, FAILURE_TITLE); //Cancel if Name is empty
							return;
						}
						element = new PhotoAlbum(name);
						StartScreen.noOfAlbums++;
						StartScreen.retextAlbum();
					} else {
						name = DialogHandler.inputDialog("Insert name here", "New PhotoBox");
						if(name == null)
							return; //cancel if no Name was inserted
						if(name.trim().isEmpty()) {
							ErrorMessageDialog.showMessage(null, NAME_EMPTY, FAILURE_TITLE); //Cancel if Name is empty
							return;
						}
						String path = DialogHandler.chooseSource();
						if(path == null)
							return; //cancel if no Path was chosen
						JProgressBar progress = DialogHandler.showProgressBar();
						FolderManager manager = new FolderManager();
							element = manager.importPhotoBox(name, path);
						//TODO fill Photobox with Photos, (Folder Manager s-times)
						StartScreen.noOfBoxes++;
						StartScreen.retextBox();
							Thread.sleep(3000);
						((Window) progress.getParent().getParent().getParent().getParent()).dispose();
					}
					parentPanel.add(new StartScreenElement(elementTyp, ELEMENT, parentPanel, name));
					parentPanel.getParent().validate();

				} catch (FileNotFoundException fnfe) {
					ErrorMessageDialog.showMessage(null, fnfe.getMessage(), ERROR_TITLE, fnfe.getStackTrace().toString());
				} catch (InterruptedException ie) {
					ErrorMessageDialog.showMessage(null, ie.getMessage(), ERROR_TITLE, ie.getStackTrace().toString());
				}
			}
		};
		addButton.addActionListener(addListen);
		add(addButton);
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE, ioe.getStackTrace().toString());
		}
	}
	
	
	/**
	 * This Method shows the tool panel below the ElementButton
	 */
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

	/**
	 * This method changes the name of the Photoalbum/-box
	 * @param newName: the new name of the Photoalbum/-box
	 */
	public void changeName(String newName) {
		elementButton.setText(newName);
		repaint();
		validate();
	}

	/**
	 * This method deletes an element
	 */
	public void delete() {
		JPanel parent = (JPanel) getParent();
		parent.remove(this);
		parent.repaint();
		parent.validate();
		if(elementTyp == ALBUM) { //TODO delete Boxes and Albums
//			((PhotoAlbum) element).destroy();
			StartScreen.noOfAlbums--;
			StartScreen.retextAlbum();
		} else {
//			((PhotoBox) element).destroy();
			StartScreen.noOfBoxes--;
			StartScreen.retextBox();
		}
		parent.setSize(0,0); //Reset of the panel size, to prevent graphical errors.
	}
	
	public int getTyp() {
		return elementTyp;
	}
	
	/**
	 * This method returns the associated Photo Box or Album
	 * @return PhotoBox or PhotoAlbum, depends on the type.
	 */
	public Object getElement() {
		return element;
	}

}
