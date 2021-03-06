package de.htw.hundertwasser.view;


import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.backend.ElementStorage;
import de.htw.hundertwasser.backend.FolderManager;
import de.htw.hundertwasser.core.DialogHandler;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.core.interfaces.FolderManagerObserver;
import de.htw.hundertwasser.custom.error.CantCreateDirectoryException;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Klasse die ein bestimmtes Photoalbum oder Photobox vom Startscrenn aus ansprechen kann. (Und der Hinzuf�gen Knopf)
 * @author Fabian, Tim
 *
 */
public class StartScreenElement  extends JPanel implements FolderManagerObserver {
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
	public static final String NAME_TOO_LONG = "Not more than 50 characters!";
	
	//Variables
	private static StartScreenToolPanel chosenElementToolPanel = null;
	private int elementTyp;
	private JPanel parentPanel = null;
	private JButton elementButton = null;
	private Object element = null; //<-- ONE Album or Box, depending on parameter.
	private String elementName = null;
	private StartScreenElement thisElement;
	private static StartScreenElement choosenOne = null;
	
	
	public StartScreenElement(int elementTyp, int panelTyp, JPanel parentPanel, Object element) {
		thisElement = this;
		this.element = element;
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
				elementName = ((PhotoAlbum) element).getName();
				Icon elementIcon = RessourcenEnummeration.PHOTOALBUM_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			} else {
				elementName = ((PhotoBox) element).getName();
				Icon elementIcon = RessourcenEnummeration.PHOTOBOX_NEU.getIcon();
				elementButton = new JButton(elementName, elementIcon);
			}
			elementButton.setHorizontalTextPosition(SwingConstants.CENTER);
			elementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			elementButton.setFont(RessourcenEnummeration.FONT_CALIBRI.getFont().deriveFont(14f)); //Loads and resizes font
			ActionListener addListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choosenOne = thisElement;
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
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE, ioe.getStackTrace());
		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, onse.getMessage(), ERROR_TITLE, onse.getStackTrace());
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, ffe.getMessage(), ERROR_TITLE, ffe.getStackTrace());
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
						name = DialogHandler.inputDialog("Insert name here", "New PhotoAlbum");
						if(name == null)
							return; //cancel if no Name was inserted
						if(name.trim().isEmpty()) {
							ErrorMessageDialog.showMessage(null, NAME_EMPTY, FAILURE_TITLE); //Cancel if Name is empty
							return;
						} else if(name.length() > 50) {
							ErrorMessageDialog.showMessage(null, NAME_TOO_LONG, FAILURE_TITLE); //Cancel if Name is empty
							return;
						}
						element = new PhotoAlbum(name);
						ElementStorage.addPhotoAlbum((PhotoAlbum) element);
						StartScreen.retextAlbum();
						
					} else {
						
						name = DialogHandler.inputDialog("Insert name here", "New PhotoBox");
						if(name == null)
							return; //cancel if no Name was inserted
						if(name.trim().isEmpty()) {
							ErrorMessageDialog.showMessage(null, NAME_EMPTY, FAILURE_TITLE); //Cancel if Name is empty
							return;
						} else if(name.length() > 50) {
							ErrorMessageDialog.showMessage(null, NAME_TOO_LONG, FAILURE_TITLE); //Cancel if Name is empty
							return;
						}
						String path = DialogHandler.chooseSource();
						if(path == null)
							return; //cancel if no Path was chosen
//						JProgressBar progress = DialogHandler.showProgressBar();
						FolderManager manager = new FolderManager();
							element = manager.importPhotoBox(name, path);
							if (element==null) return; //Import cancled.
						//TODO fill Photobox with Photos, (Folder Manager s-times)
							ElementStorage.addPhotoBox((PhotoBox) element);
						StartScreen.retextBox();
//						((Window) progress.getParent().getParent().getParent().getParent()).dispose();
					}
					parentPanel.add(new StartScreenElement(elementTyp, ELEMENT, parentPanel, element));
					parentPanel.getParent().validate();

				} catch (FileNotFoundException fnfe) {
					ErrorMessageDialog.showMessage(null, fnfe.getMessage(), ERROR_TITLE);
				} catch (IllegalArgumentException e1) {
					ErrorMessageDialog.showMessage(null, e1.getMessage(), ERROR_TITLE);
				} catch (ChoosenFileNotAFolderException e1) {
					ErrorMessageDialog.showMessage(null, e1.getMessage(), ERROR_TITLE);
				} catch (CantCreateDirectoryException e1) {
					ErrorMessageDialog.showMessage(null, e1.getMessage(), ERROR_TITLE);
				}
			}
		};
		addButton.addActionListener(addListen);
		add(addButton);
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE, ioe.getStackTrace());
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
		if(elementTyp == ALBUM)
			((PhotoAlbum) element).setName(newName);
		if(elementTyp == BOX)
			((PhotoBox) element).setName(newName);
		elementButton.setText(newName);
		repaint();
		validate();
	}

	/**
	 * This method deletes an element
	 */
	public void delete() {
		boolean removed = false;
		if(elementTyp == ALBUM) {
//			((PhotoAlbum) element).destroy();
			removed = ElementStorage.removePhotoAlbum(((PhotoAlbum) element));
			StartScreen.retextAlbum();
		} else {
//			((PhotoBox) element).destroy();
			removed = ElementStorage.removePhotoBox((PhotoBox) element);
			StartScreen.retextBox();
		}
		if (removed) {
			JPanel parent = (JPanel) getParent();
			parent.remove(this);
			parent.repaint();
			parent.validate();
			parent.setSize(0,0); //Reset of the panel size, to prevent graphical errors.
		}
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
	
	public static StartScreenElement getChoosenOne() {
		return choosenOne;
	}

	@Override
	public void importNewPhotoBox() {
		FolderManager folderManager = new FolderManager();
		ArrayList<PhotoBox> arphotoboxArrayList;
		try {
			arphotoboxArrayList = folderManager.readCurrentWorkingDirectoryPhotoBox();
			for(PhotoBox photobox1:arphotoboxArrayList)
			{
				for (PhotoBox photobox2:ElementStorage.getBoxList())
				{
					if (photobox2.getName().equals(photobox1.getName()))
					{
						
					}
				}
			}
		} catch (IllegalArgumentException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "ERROR", e.getStackTrace());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "ERROR", e.getStackTrace());
			e.printStackTrace();
		} catch (ChoosenFileNotAFolderException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "ERROR", e.getStackTrace());
			e.printStackTrace();
		} catch (CantCreateDirectoryException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "ERROR", e.getStackTrace());
			e.printStackTrace();
		}
		
	}

}
