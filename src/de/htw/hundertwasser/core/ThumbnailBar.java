package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
	
	private JButton[] buttons = new JButton[16];
	private Font font;
	private Font fontB;
	private JToolBar toolBar;
	private JPanel panelThumbnails;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JScrollBar scrollBar;
	private JPanel displayThumbnails;
	private JButton button2;
	private JButton button4;
	private JButton button8;
	private JButton button16;

	
	public ThumbnailBar() {
		
		setFonts();
		for (int i=0; i<buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.WHITE);
		}
		
		toolBar = initialiseToolbar();		
		panelThumbnails = initialiseThumbnails();
		
		buttonLeft = new JButton();
		buttonLeft.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonLeft.setBackground(Color.WHITE);
		buttonLeft.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/thumbnail_arrows_left.png")));
		
		buttonRight = new JButton();
		buttonRight.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRight.setBackground(Color.WHITE);
		buttonRight.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/thumbnail_arrows_right.png")));

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		
		add(Box.createRigidArea(new Dimension(150, 0)));
		add(toolBar);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(buttonLeft);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(panelThumbnails);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(buttonRight);
		add(Box.createRigidArea(new Dimension(150, 0)));
		
		buttonRight.addActionListener(getButtonRightActionListener());
		
		buttonLeft.addActionListener(getButtonLeftActionListener());	
		
		buttons[15].setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/add_photo.png")));
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
		
		button2 = new JButton("2");
		button2.setBackground(Color.WHITE);
		button2.setFont(fontB);
		toolBar.add(button2);
		
		button4 = new JButton("4");
		button4.setBackground(Color.WHITE);
		button4.setFont(font);
		toolBar.add(button4);
		
		button8 = new JButton("8");
		button8.setBackground(Color.WHITE);
		button8.setFont(font);
		toolBar.add(button8);
		
		button16 = new JButton("16");
		button16.setBackground(Color.WHITE);
		button16.setFont(font);
		toolBar.add(button16);
		
		button2.addActionListener(getButton2ActionListener());
		
		button4.addActionListener(getButton4ActionListener());
		
		button8.addActionListener(getButton8ActionListener());
		
		button16.addActionListener(getButton16ActionListener());
		
		return toolBar;
	}
	
	/**
	 * 
	 */
	private JPanel initialiseThumbnails() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout(0, 10));
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		displayThumbnails = new JPanel();
		displayThumbnails.setBackground(Color.WHITE);
		displayThumbnails.setLayout(new GridLayout(1, 2, 10, 10));
		panel.add(displayThumbnails, BorderLayout.CENTER);
		
		scrollBar = new JScrollBar(Scrollbar.HORIZONTAL, 1, 8, 1, 16);
		scrollBar.setBackground(Color.WHITE);
		scrollBar.setUnitIncrement(scrollBar.getVisibleAmount());
		panel.add(scrollBar, BorderLayout.SOUTH);
		
		for (int i=0; i<15;i++) {
				buttons[i].setText(""+(i+1));
			if (i<2)
				displayThumbnails.add(buttons[i]);
		}
		displayThumbnails.add(buttons[15]);
		
		scrollBar.addAdjustmentListener(getScroolBarAdjustmentListener());
		
		return panel;
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getPlusButtonActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButtonLeftActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(scrollBar.getValue()-scrollBar.getUnitIncrement());				
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButtonRightActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(scrollBar.getValue()+scrollBar.getUnitIncrement());					
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButton2ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(1, 2, 10, 10));
				displayThumbnails.removeAll();
				displayThumbnails.add(buttons[0]);
				displayThumbnails.add(buttons[1]);
				displayThumbnails.add(buttons[15]);
				displayThumbnails.setSize(getMaximumSize());
				displayThumbnails.repaint();
				displayThumbnails.revalidate();
				button2.setFont(fontB);
				button4.setFont(font);
				button8.setFont(font);
				button16.setFont(font);
				scrollBar.setVisibleAmount(2);
				scrollBar.setUnitIncrement(2);					
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButton4ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(1, 4, 10, 10));
				displayThumbnails.removeAll();
				for (int i=0; i<3; i++) {
					displayThumbnails.add(buttons[i]);
				}
				displayThumbnails.add(buttons[15]);
				displayThumbnails.setSize(getMaximumSize());
				displayThumbnails.repaint();
				displayThumbnails.revalidate();
				button2.setFont(font);
				button4.setFont(fontB);
				button8.setFont(font);
				button16.setFont(font);
				scrollBar.setVisibleAmount(4);
				scrollBar.setUnitIncrement(4);				
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButton8ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(2, 4, 20, 5));
				displayThumbnails.removeAll();
				for (int i=0; i<7; i++) {
					displayThumbnails.add(buttons[i]);
				}
				displayThumbnails.add(buttons[15]);
				displayThumbnails.setSize(getMaximumSize());
				displayThumbnails.repaint();
				displayThumbnails.revalidate();
				button2.setFont(font);
				button4.setFont(font);
				button8.setFont(fontB);
				button16.setFont(font);
				scrollBar.setVisibleAmount(8);
				scrollBar.setUnitIncrement(8);				
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private ActionListener getButton16ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(2, 8, 2, 2));
				displayThumbnails.removeAll();
				for (int i=0; i<buttons.length; i++) {
					displayThumbnails.add(buttons[i]);
				}
				displayThumbnails.setSize(getMaximumSize());
				displayThumbnails.repaint();
				displayThumbnails.revalidate();
				button2.setFont(font);
				button4.setFont(font);
				button8.setFont(font);
				button16.setFont(fontB);
				scrollBar.setVisibleAmount(16);
				scrollBar.setUnitIncrement(16);				
			}
		};
	}
	
	/**
	 * 
	 * @return
	 */
	private AdjustmentListener getScroolBarAdjustmentListener() {
		return new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
