package de.htw.hundertwasser.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * klasse die die einzelnen Tools für die Photoboxen und Alben azeigt (öffnen, umbenennen, senden und löschen)
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
			addIcon = RessourcenEnummeration.OEFFNEN.getIcon();
			JButton openButton = new JButton(addIcon);
			openButton.setBackground(StartScreen.getBGColor());
			add(openButton);
			addIcon = RessourcenEnummeration.UMBENENNEN.getIcon();
			JButton renameButton = new JButton(addIcon);
			renameButton.setBackground(StartScreen.getBGColor());
			add(renameButton);
			addIcon = RessourcenEnummeration.SENDEN.getIcon();
			JButton sendButton = new JButton(addIcon);
			sendButton.setBackground(StartScreen.getBGColor());
			add(sendButton);
			addIcon = RessourcenEnummeration.LOESCHEN.getIcon();
			JButton deleteButton = new JButton(addIcon);
			deleteButton.setBackground(StartScreen.getBGColor());
			add(deleteButton);
			
			ActionListener openListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO open Photobre... (geht ja noch nit so wirklich...)
				}
			};
			
			ActionListener renameListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newName = JOptionPane.showInputDialog("Bitte neuen Namen eingeben:", "");
					if(newName == null)
						return; //Dann wurde Abbrechen gedrückt.
					((StartScreenElement) getParent()).changeName(newName);
				}
			};
			
			ActionListener sendListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO Senden des gedöns
				}
			};
			
			ActionListener deleteListen = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int delete = JOptionPane.showConfirmDialog(new JDialog(), "Wollen sie wirklich loeschen?", "Loeschen", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(delete == 0){
						((StartScreenElement) getParent()).delete();
					}
				}
			};
			openButton.addActionListener(openListen);
			renameButton.addActionListener(renameListen);
			sendButton.addActionListener(sendListen);
			deleteButton.addActionListener(deleteListen);
			
		} catch (IOException e) {
			//TODO Ausgabe des Fehlers, iwo bre...
			e.printStackTrace();
		}
	}
}
