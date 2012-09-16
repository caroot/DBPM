package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.view.PhotoAlbumFullScreen;

public class ThumbnailBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons = new JButton[15];
	private Font font;
	private Font fontB;
	private JToolBar toolBar;
	private JPanel panelThumbnails;
	private JButton buttonLeft;
	private JButton buttonRight;

	
	public ThumbnailBar() {
		
		setFonts();
		for (int i=0; i<buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.WHITE);
		}
		
		toolBar = initialiseToolbar();		
		panelThumbnails = initaliseThumbnails();
		
		buttonLeft = new JButton();
		buttonLeft.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonLeft.setBackground(Color.WHITE);
		buttonLeft.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/thumbnail_arrows_left.png")));
		
		buttonRight = new JButton();
		buttonRight.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRight.setBackground(Color.WHITE);
		buttonRight.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/thumbnail_arrows_right.png")));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.WHITE);
		
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(toolBar);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(buttonLeft);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(panelThumbnails);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(buttonRight);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
	}
	
	/**
	 * 
	 */
	private void setFonts() {
		try {
			font = RessourcenEnummeration.FONT_CALIBRI.getFont().deriveFont(14f);
			fontB = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont().deriveFont(14f);
		} catch (OperationNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private JToolBar initialiseToolbar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setFloatable(false);
		toolBar.setBorderPainted(false);
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JLabel label = new JLabel("Thumbnails");
		label.setBackground(Color.WHITE);
		label.setFont(fontB);
		toolBar.add(label);
		
		JButton button2 = new JButton("2");
		button2.setBackground(Color.WHITE);
		button2.setFont(font);
		toolBar.add(button2);
		
		JButton button4 = new JButton("4");
		button4.setBackground(Color.WHITE);
		button4.setFont(font);
		toolBar.add(button4);
		
		JButton button8 = new JButton("8");
		button8.setBackground(Color.WHITE);
		button8.setFont(fontB);
		toolBar.add(button8);
		
		JButton button16 = new JButton("16");
		button16.setBackground(Color.WHITE);
		button16.setFont(font);
		toolBar.add(button16);
		
		return toolBar;
	}
	
	/**
	 * 
	 */
	private JPanel initaliseThumbnails() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout(0, 10));
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(new GridLayout(2, 4, 10, 10));
		panel.add(panel2, BorderLayout.CENTER);
		
		JScrollBar scrollBar = new JScrollBar(Scrollbar.HORIZONTAL, 1, 8, 1, 16);
		scrollBar.setBackground(Color.WHITE);
		scrollBar.setUnitIncrement(scrollBar.getVisibleAmount());
		panel.add(scrollBar, BorderLayout.SOUTH);
		
		for (int i=0; i<8;i++) {
			if (i!=7)
				buttons[i].setText(""+(i+1));
			else
				buttons[i].setText("+");
			panel2.add(buttons[i]);
		}
		
		return panel;
	}
}
