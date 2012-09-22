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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

import de.htw.hundertwasser.backend.ImageManager;
import de.htw.hundertwasser.core.interfaces.NavBarPhotoBoxObserver;
import de.htw.hundertwasser.core.interfaces.ThumbNailBarObservable;
import de.htw.hundertwasser.core.interfaces.ThumbNailBarObserver;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;
import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.view.PhotoAlbumFullScreen;
/**
 * 
 * @author Steffen
 *
 */
public class ThumbnailBar extends JPanel implements ThumbNailBarObservable, 
													NavBarPhotoBoxObserver {

	// Constants
	private static final long serialVersionUID = 1L;	
	private JButton[] buttons;
	// Variables
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
	
	private ArrayList<ThumbNailBarObserver>	observerList;
	private ImageManager im;
	private Photo photo;
	
	private int countPhotos;
	private File[] photos;
	private int countButtons;


	/*
	 * Constructor
	 */
	public ThumbnailBar() {
		
		setFonts();
		countPhotos = 15;//TODO nur zum testen!!!!
		buttons = new JButton[countPhotos+1];
		
		for (int i=0; i<buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.WHITE);
		}
		
		for (int i=0; i<buttons.length-1;i++) {
			buttons[i].addActionListener(getImageButtonsActionListener());
		}
		
		for (int i=1; i<buttons.length-1; i++) {
			buttons[i].setText(""+(i+1));
		}
		buttons[buttons.length-1].addActionListener(getPlusButtonActionListener());
		buttons[buttons.length-1].setToolTipText("Add a photo");
		buttons[buttons.length-1].setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/add_photo.png")));
		
		im = new ImageManager();
		photo = new Photo("tits", "Motivations Bild v2.jpg");
		photo.setComment("tits");
		
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
		
		observerList = new ArrayList<ThumbNailBarObserver>();	
	
	}
	
	/**
	 * Function to set the Font
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
	 * Function to initialise the Toolbar
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
		button2.setToolTipText("");
		toolBar.add(button2);
		
		button4 = new JButton("4");
		button4.setBackground(Color.WHITE);
		button4.setFont(font);
		button4.setToolTipText("");
		toolBar.add(button4);
		
		button8 = new JButton("8");
		button8.setBackground(Color.WHITE);
		button8.setFont(font);
		button8.setToolTipText("");
		toolBar.add(button8);
		
		button16 = new JButton("16");
		button16.setBackground(Color.WHITE);
		button16.setFont(font);
		button16.setToolTipText("");
		toolBar.add(button16);
		
		button2.addActionListener(getButton2ActionListener());
		
		button4.addActionListener(getButton4ActionListener());
		
		button8.addActionListener(getButton8ActionListener());
		
		button16.addActionListener(getButton16ActionListener());
		
		return toolBar;
	}
	
	/**
	 * Function to initialise the Thumbnails
	 */
	private JPanel initialiseThumbnails() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout(0, 5));
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		displayThumbnails = new JPanel();
		displayThumbnails.setBackground(Color.WHITE);
		displayThumbnails.setLayout(new GridLayout(1, 2, 10, 10));
		panel.add(displayThumbnails, BorderLayout.CENTER);
		int extent;
		if (countPhotos>=1)
			extent = 2;
		else
			extent = countPhotos;
		scrollBar = new JScrollBar(Scrollbar.HORIZONTAL, 0, extent, 0, countPhotos+1);
		scrollBar.setBackground(Color.WHITE);
		scrollBar.setUnitIncrement(scrollBar.getVisibleAmount());
		scrollBar.setBlockIncrement(scrollBar.getVisibleAmount());
		panel.add(scrollBar, BorderLayout.SOUTH);
		
		try {
			buttons[0].setIcon(new ImageIcon(im.getThumNailImage("Motivations Bild v2.jpg", 32, 32)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientPrivilegesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayThumbnails.add(buttons[0]);
		displayThumbnails.add(buttons[1]);
		displayThumbnails.add(buttons[buttons.length-1]);
		countButtons = 2;
		
		scrollBar.addAdjustmentListener(getScroolBarAdjustmentListener());
		
		return panel;
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getPlusButtonActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = DialogHandler.chooseSource();
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getButtonLeftActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollBar.setValue(scrollBar.getValue()-scrollBar.getUnitIncrement());
				setButtons();
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
				setButtons();
			}
		};
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getButton2ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(1, 2, 10, 10));
				countButtons = 2;
				setButtons();
				button2.setFont(fontB);
				button4.setFont(font);
				button8.setFont(font);
				button16.setFont(font);
				scrollBar.setVisibleAmount(2);
				scrollBar.setUnitIncrement(2);
				scrollBar.setBlockIncrement(2);
			}
		};
	}

	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getButton4ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(1, 4, 10, 10));
				countButtons = 3;
				setButtons();
				button2.setFont(font);
				button4.setFont(fontB);
				button8.setFont(font);
				button16.setFont(font);
				scrollBar.setVisibleAmount(4); //3 anstatt 4
				scrollBar.setUnitIncrement(3); //3 anstatt 4	
				scrollBar.setBlockIncrement(3); //3 anstat 4
			}
		};
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getButton8ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(1, 8, 10, 10));
				countButtons = 7;
				setButtons();
				button2.setFont(font);
				button4.setFont(font);
				button8.setFont(fontB);
				button16.setFont(font);
				scrollBar.setVisibleAmount(8);
				scrollBar.setUnitIncrement(7);	
				scrollBar.setBlockIncrement(7);
			}
		};
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private ActionListener getButton16ActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayThumbnails.setLayout(new GridLayout(2, 8, 2, 2));
				countButtons = 15;
				setButtons();
				button2.setFont(font);
				button4.setFont(font);
				button8.setFont(font);
				button16.setFont(fontB);
				scrollBar.setVisibleAmount(16);
				scrollBar.setUnitIncrement(15);	
				scrollBar.setBlockIncrement(15);
			}
		};
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return action performed
	 */
	private AdjustmentListener getScroolBarAdjustmentListener() {
		return new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				// TODO Auto-generated method stub
				setButtons();
			}
		};		
	}
	
	/**
	 * Function to get the Action
	 * 
	 * @return
	 */
	private ActionListener getImageButtonsActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sendMessage(photo);				
			}
		};
	}

	/**
	 * Adds a ThumbNailBarObserver to the observerList
	 * 
	 * @param	observer that shall be added to the observerList
	 */
	@Override
	public void addThumbNailBarObserver(ThumbNailBarObserver observer) {
		observerList.add(observer);
		
	}

	/**
	 * Removes a ThumbNailBarObserver from the observerList
	 * 
	 *  @param	observer that shall be removed from the observerList
	 */
	@Override
	public void removeThumbNailBarObserver(ThumbNailBarObserver observer) {
		observerList.remove(observer);
		
	}

	/**
	 * Sends a message to all observer in the observerList
	 * 
	 * @param	photo the chosen photo
	 */
	@Override
	public void sendMessage(Photo photo) {
		for(ThumbNailBarObserver observer: observerList) {
			observer.setPhoto(photo);
		}		
	}

	/**
	 * 
	 */
	@Override
	public void receivePhotoBox(PhotoBox photobox) {
		// TODO Auto-generated method stub
		countPhotos = photobox.getCount();
		String pathToBox = photobox.getAbsolutePathToFiles();
		try {
		photos = im.getImagesListInFolder(pathToBox);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChoosenFileNotAFolderException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void setButtons() {
		displayThumbnails.removeAll();
		int init = scrollBar.getValue();
		int max = init+countButtons;	
		if (max > countPhotos) {
			init = countPhotos-countButtons;
			max = init+countButtons;
		}
		for(int i=init;i<max;i++) {
			displayThumbnails.add(buttons[i]);
		}
		displayThumbnails.add(buttons[buttons.length-1]);
		displayThumbnails.setSize(getMaximumSize());
		displayThumbnails.repaint();
		displayThumbnails.revalidate();
	}
}
