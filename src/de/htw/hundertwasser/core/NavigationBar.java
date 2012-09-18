package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JScrollBar;

public class NavigationBar extends JFrame{
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	
	
	public NavigationBar() {
		getContentPane().setFont(new Font("Calibri", Font.PLAIN, 14));
		
		setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));	
		
		setUndecorated(true);
		setState(JFrame.MAXIMIZED_BOTH);
		setTitle("NavigationBar");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 144, 408);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		/**
		 *  Photobox panel ----------------------------------------------------------------------------------------------
		 */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 130, 162);
		panel.add(scrollPane);
		
		JLabel lblYourPhotoBoxes = new JLabel("      Your Photo Boxes");
		lblYourPhotoBoxes.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblYourPhotoBoxes.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(lblYourPhotoBoxes);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_2);

		JButton btnNewButton = new JButton("+New PhotoBox");
		btnNewButton.setAction(action);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnNewButton.setBounds(16, 173, 110, 27);
		panel.add(btnNewButton);
		
		/**
		 * 	Photo album panel ----------------------------------------------------------------------------------------------
		 */
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 204, 130, 162);
		panel.add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("      Your Photo Albums");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel.setBackground(Color.WHITE);
		scrollPane_1.setColumnHeaderView(lblNewLabel);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane_3);
		
	    JButton btnPhotobox = new JButton("+ New PhotoAlbum");
	    btnPhotobox.setAction(action_1);
	    btnPhotobox.setFont(new Font("Calibri", Font.PLAIN, 10));
	    btnPhotobox.setBounds(16, 375, 110, 27);
	    panel.add(btnPhotobox);
	}
		
	/**
	 * action of the two buttons used in the bar
	 * 
	 * @author johannesschramm
	 *
	 */
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "+ new Photobox");
			putValue(SHORT_DESCRIPTION, "add PhotoBox");
		}
		public void actionPerformed(ActionEvent e) {
			// Aufruf des Photobox Konstruktors
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "+ new Photoalbum");
			putValue(SHORT_DESCRIPTION, "add Photoalbum");
		}
		public void actionPerformed(ActionEvent e) {
			// Aufruf des Photoalbum Konstruktors
		}
	}
}
