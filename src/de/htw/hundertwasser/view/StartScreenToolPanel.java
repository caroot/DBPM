package de.htw.hundertwasser.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.core.DialogHandler;

/**
 * klasse die die einzelnen Tools f�r die Photoboxen und Alben azeigt (�ffnen, umbenennen, senden und l�schen)
 * @author Fabian
 *
 */
public class StartScreenToolPanel extends JPanel {
	
	//Variablen
	JPanel subElements = null;
	
	public StartScreenToolPanel() {
		setLayout(new GridLayout(0, 3, 1, 5));
		setBackground(StartScreen.getBGColor());
		Icon addIcon;
		try {
			Font buttonFont = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont().deriveFont(14f);
//			Font buttonFont = new Font("Arial", 0, 10);
			//------ Open Button ------//
			addIcon = RessourcenEnummeration.OEFFNEN.getIcon();
			JButton openButton = new JButton("Open", addIcon);
			openButton.setHorizontalTextPosition(SwingConstants.CENTER);
			openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			openButton.setFont(buttonFont);
//			openButton.setBackground(StartScreen.getBGColor());
			add(openButton);
			//------ rename Button -----//
			addIcon = RessourcenEnummeration.UMBENENNEN.getIcon();
			JButton renameButton = new JButton("Rename", addIcon);
			renameButton.setHorizontalTextPosition(SwingConstants.CENTER);
			renameButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			renameButton.setFont(buttonFont);
//			renameButton.setBackground(StartScreen.getBGColor());
			add(renameButton);
//			//------ Send Button ------//
//			addIcon = RessourcenEnummeration.SENDEN.getIcon();
//			JButton sendButton = new JButton("Send", addIcon);
//			sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
//			sendButton.setVerticalTextPosition(SwingConstants.BOTTOM);
//			sendButton.setFont(buttonFont);
//			sendButton.setBackground(StartScreen.getBGColor());
//			add(sendButton);
			//------ Delete Button ------//
//			URL path = ClassLoader.getSystemResource("de/htw/test/380.gif");
//			System.out.println(path);
//			addIcon = new ImageIcon(path);
			addIcon = RessourcenEnummeration.LOESCHEN.getIcon();
			JButton deleteButton = new JButton("Delete", addIcon);
			deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			deleteButton.setFont(buttonFont);
//			deleteButton.setBackground(StartScreen.getBGColor());
			add(deleteButton);
			//------ All buttons in ------//
			JPanel emptyPanel = new JPanel(); // used to make an gap between the buttons and the bottom.s
			emptyPanel.setBackground(StartScreen.getBGColor());
			add(emptyPanel);
			
			ActionListener openListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartScreenElement parentElement = ((StartScreenElement) getParent());
					 if (parentElement.getTyp() == StartScreenElement.ALBUM) { //TODO Open PAES
//						new PhotoAlbumEditScreen((PhotoAlbum) parentElement.getElement());
					} else { //TODO Open PBES
//						new PhotoBoxEditScreen((PhotoBox) parentElement.getElement());
					}
					//TODO open Photobre... (geht ja noch nit so wirklich...)/TODO open Photobre... (geht ja noch nit so wirklich...)
				}
			};
			
			ActionListener renameListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newName = DialogHandler.inputDialog("Insert new name:", "rename");
					if(newName == null)
						return; //Dann wurde Abbrechen gedr�ckt.
					((StartScreenElement) getParent()).changeName(newName);
				}
			};
			
//			ActionListener sendListen = new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					//TODO Senden des ged�ns
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
			ErrorMessageDialog.showMessage(null, "Could not find the Icon", "Oh no", ioe.getStackTrace().toString());
			ioe.printStackTrace();
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, "Font not where it belongs", "Oh no", ffe.getStackTrace().toString());
			ffe.printStackTrace();
		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, "Something bad happend", "Oh no", onse.getStackTrace().toString());
			onse.printStackTrace();
		}
	}
}
