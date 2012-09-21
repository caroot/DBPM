package de.htw.hundertwasser.view;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.core.DialogHandler;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * This Class shows the Startscreen Toolbar icons (open, rename and delete)
 * @author Fabian, Tim
 *
 */
public class StartScreenToolPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//Error Constants
	public static final String ERROR_TITLE = "StartScreenToolPanel Error";
	private static final String NO_ICON = "Could not find the Icon";
	private static final String NO_FONT = "Font not where it belongs";

	private static final String SOMETHING_FISHY = "Something bad happend. That shouldn't happen";
	
	public StartScreenToolPanel() {
		setLayout(new GridLayout(0, 3, 1, 5));
		setBackground(StartScreen.getBGColor());
		Icon addIcon;
		try {
			Font buttonFont = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont().deriveFont(14f);
			//------ Open Button ------//
			addIcon = RessourcenEnummeration.OEFFNEN.getIcon();
			JButton openButton = new JButton("Open", addIcon);
			openButton.setHorizontalTextPosition(SwingConstants.CENTER);
			openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			openButton.setFont(buttonFont);
			openButton.setToolTipText("Switch to Edit Mode");
			add(openButton);
			//------ rename Button -----//
			addIcon = RessourcenEnummeration.UMBENENNEN.getIcon();
			JButton renameButton = new JButton("Rename", addIcon);
			renameButton.setHorizontalTextPosition(SwingConstants.CENTER);
			renameButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			renameButton.setFont(buttonFont);
			renameButton.setToolTipText("Change the name");
			add(renameButton);
//			//------ Send Button ------//
//			addIcon = RessourcenEnummeration.SENDEN.getIcon();
//			JButton sendButton = new JButton("Send", addIcon);
//			sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
//			sendButton.setVerticalTextPosition(SwingConstants.BOTTOM);
//			sendButton.setFont(buttonFont);
//			add(sendButton);
			//------ Delete Button ------//
			addIcon = RessourcenEnummeration.LOESCHEN.getIcon();
			JButton deleteButton = new JButton("Delete", addIcon);
			deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			deleteButton.setFont(buttonFont);
			deleteButton.setToolTipText("Remove from hard disk");
			add(deleteButton);
			//------ All buttons in ------//
			JPanel emptyPanel = new JPanel(); // used to make an gap between the buttons and the bottom.
			emptyPanel.setBackground(StartScreen.getBGColor());
			add(emptyPanel);
			
			ActionListener openListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartScreenElement parentElement = ((StartScreenElement) getParent());
					 if (parentElement.getTyp() == StartScreenElement.ALBUM) {;
						 PhotoAlbumEditScreen albumScreen = new PhotoAlbumEditScreen((PhotoAlbum) parentElement.getElement()); //Loads and displays the Photalbum in an PAES.
						 albumScreen.setVisible(true);
					} else {
						PhotoBoxEditScreen photoScreen = new PhotoBoxEditScreen((PhotoBox) parentElement.getElement()); //Loads and displays the Photbox in an PBES.
						photoScreen.setVisible(true);
					}
				}
			};
			
			ActionListener renameListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newName = DialogHandler.inputDialog("Insert new name:", "rename");
					if(newName == null)
						return; //Cancel was pressed
					((StartScreenElement) getParent()).changeName(newName);
				}
			};
			
//			ActionListener sendListen = new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					//Send all the things, should be here...
//				}
//			};
			
			ActionListener deleteListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int delete = JOptionPane.showConfirmDialog(new JDialog(), "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(delete == 0){
						((StartScreenElement) getParent()).delete();
					}
				}
			};
			openButton.addActionListener(openListen);
			renameButton.addActionListener(renameListen);
//			sendButton.addActionListener(sendListen);
			deleteButton.addActionListener(deleteListen);
			
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, NO_ICON, ERROR_TITLE, ioe.getStackTrace().toString());
			ioe.printStackTrace();
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, NO_FONT, ERROR_TITLE, ffe.getStackTrace().toString());
			ffe.printStackTrace();
		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, SOMETHING_FISHY, ERROR_TITLE, onse.getStackTrace().toString());
			onse.printStackTrace();
		}
	}
}
