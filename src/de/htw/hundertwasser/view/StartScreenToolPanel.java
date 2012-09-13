package de.htw.hundertwasser.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * klasse die die einzelnen Tools f�r die Photoboxen und Alben azeigt (�ffnen, umbenennen, senden und l�schen)
 * @author Fabian
 *
 */
public class StartScreenToolPanel extends JPanel {
	
	//Variablen
	JPanel subElements = null;
	
	public StartScreenToolPanel() {
		setLayout(new GridLayout(0, 4, 1, 5));
		setBackground(StartScreen.getBGColor());
		Icon addIcon;
		try {
			Font buttonFont = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont().deriveFont(14f);
			addIcon = RessourcenEnummeration.OEFFNEN.getIcon();
			JButton openButton = new JButton("Open", addIcon);
			openButton.setHorizontalTextPosition(SwingConstants.CENTER);
			openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			openButton.setFont(buttonFont);
			openButton.setBackground(StartScreen.getBGColor());
			add(openButton);
			addIcon = RessourcenEnummeration.UMBENENNEN.getIcon();
			JButton renameButton = new JButton("Rename", addIcon);
			renameButton.setHorizontalTextPosition(SwingConstants.CENTER);
			renameButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			renameButton.setFont(buttonFont);
			renameButton.setBackground(StartScreen.getBGColor());
			add(renameButton);
			addIcon = RessourcenEnummeration.SENDEN.getIcon();
			JButton sendButton = new JButton("Send", addIcon);
			sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
			sendButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			sendButton.setFont(buttonFont);
			sendButton.setBackground(StartScreen.getBGColor());
			add(sendButton);
			addIcon = RessourcenEnummeration.LOESCHEN.getIcon();
			JButton deleteButton = new JButton("Delete", addIcon);
			deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			deleteButton.setFont(buttonFont);
			deleteButton.setBackground(StartScreen.getBGColor());
			add(deleteButton);
			
			ActionListener openListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO open Photobre... (geht ja noch nit so wirklich...)/TODO open Photobre... (geht ja noch nit so wirklich...)
				}
			};
			
			ActionListener renameListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newName = JOptionPane.showInputDialog(null, "Insert new name:", "rename", JOptionPane.QUESTION_MESSAGE);
					if(newName == null)
						return; //Dann wurde Abbrechen gedr�ckt.
					((StartScreenElement) getParent()).changeName(newName);
				}
			};
			
			ActionListener sendListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO Senden des ged�ns
				}
			};
			
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
			sendButton.addActionListener(sendListen);
			deleteButton.addActionListener(deleteListen);
			
		} catch (IOException ioe) {
			//TODO Ausgabe des Fehlers, iwo bre...
			ioe.printStackTrace();
		} catch (FontFormatException ffe) {
			//TODO Ausgabe des Fehlers, iwo bre...
			ffe.printStackTrace();
		} catch (OperationNotSupportedException onse) {
			//TODO Ausgabe des Fehlers, iwo bre...
			onse.printStackTrace();
		}
	}
}
