package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * This Class shows the Startscreen of DBPM
 * 
 * @author Fabian Hewer, Tim Schlosser
 * @version 21.9.'12
 */

public class StartScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	// Constants
	public static final String DBPM = "Dunkelbunt Photo Manager";
	public static final String PBOXES = "Your Photo Boxes";
	public static final String PALBUMS = "Your Photo Albums";
	// Errorconstants
	public static final String ERROR_TITLE = "Start Screen error Message";
	public static final String ICON_AWAY = "Oh no, the Squirrel icon is not where it should be.";
	public static final String SOMETHING_FISHY_LAF = "Ups, something bad happend with the Nimbus!";

	// Variables
	private static JFrame mainScreen;
	private static StartScreenSubPanel photoBoxes;
	private static StartScreenSubPanel photoAlbums;
	private static Color backgroundColor = Color.WHITE; // Background Color of
														// the Startscreen
	// TODO in PhotoBox/-Album einfuegen
	public static int noOfAlbums = 0;
	public static int noOfBoxes = 0;
	// TODO End
	private static String albumText = PALBUMS + " (" + noOfAlbums + ")";
	private static String boxText = PBOXES + " (" + noOfBoxes + ")";
	private static JLabel albumTextLabel;
	private static JLabel boxTextLabel;
	public static Dimension screenSize;
	public static Dimension textSize;
	public static Dimension subSystemSize;
	public static Dimension scrollSize;
	public static Dimension elementSize;

	/**
	 * Constructor
	 */
	public StartScreen() {
		super(DBPM);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If the close Button
														// iss pressed, the
														// programm terminates
		setSize(screenSize); // Sets Size to maximum Screen Size
		setExtendedState(MAXIMIZED_BOTH); // Window starts maximized
		setLayout(new GridLayout(0, 1, 0, 1)); // No of columns, Rows,
												// Gaps(columns), Gaps(row)
		try {
			setIconImage(RessourcenEnummeration.EICHHORN_ICON.getImage());
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(null, ICON_AWAY);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			// for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
			// {
			// System.out.println(info.getClassName());
			// }
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
					// "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
					);
		} catch (ClassNotFoundException cnfe) {
			ErrorMessageDialog.showMessage(null, SOMETHING_FISHY_LAF,
					ERROR_TITLE, cnfe.getStackTrace().toString());
		} catch (InstantiationException ie) {
			ErrorMessageDialog.showMessage(null, SOMETHING_FISHY_LAF,
					ERROR_TITLE, ie.getStackTrace().toString());
		} catch (IllegalAccessException iae) {
			ErrorMessageDialog.showMessage(null, SOMETHING_FISHY_LAF,
					ERROR_TITLE, iae.getStackTrace().toString());
		} catch (UnsupportedLookAndFeelException ulafe) {
			ErrorMessageDialog.showMessage(null, SOMETHING_FISHY_LAF,
					ERROR_TITLE, ulafe.getStackTrace().toString());
		}
		initialiseSizes();
		mainScreen = new StartScreen();
		mainScreen.add(initialisePhotoBoxes());
		mainScreen.add(initializePhotoAlbums());
		loadPreInitialized();
		mainScreen.setVisible(true);

	}

	/**
	 * This method loads saved Photoboxes and Photoalbums (hopefully)
	 */
	private static void loadPreInitialized() {
		// TODO Read FolderManger -> PhotoBoxes
		// TODO Read XMLParser -> PhotoAlbums

	}

	/**
	 * This Method initializes the Sub Panel for PhotoAlbums
	 */
	private static JPanel initializePhotoAlbums() {
		photoAlbums = new StartScreenSubPanel();
		photoAlbums.setBackground(getBGColor()); // Set Background color
		albumTextLabel = new JLabel(albumText, JLabel.CENTER); // Create Text
		albumTextLabel.setForeground(Color.BLACK); // Set Color of Text
		albumTextLabel.setPreferredSize(textSize);
		Font albumFont;
		try {
			albumFont = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont()
					.deriveFont(40f);
			albumTextLabel.setFont(albumFont);
			photoAlbums.add(albumTextLabel, BorderLayout.NORTH); // Text added
																	// to GUI
			JPanel albumMainPanel = new JPanel();
			photoAlbums.initialiseElements(albumMainPanel,
					StartScreenElement.ALBUM); // SubPanel created here

		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, onse.getMessage(),
					ERROR_TITLE, onse.getStackTrace().toString());
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE,
					ioe.getStackTrace().toString());
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, ffe.getMessage(), ERROR_TITLE,
					ffe.getStackTrace().toString());
		}
		photoAlbums.setPreferredSize(subSystemSize);
		return photoAlbums;
	}

	/**
	 * This method initializes the Sub Panel for PhotoBoxes
	 */
	private static JPanel initialisePhotoBoxes() {
		photoBoxes = new StartScreenSubPanel();
		photoBoxes.setBackground(getBGColor());
		boxTextLabel = new JLabel(boxText, JLabel.CENTER);
		boxTextLabel.setForeground(Color.BLACK);
		boxTextLabel.setPreferredSize(textSize);
		Font boxFont;

		try {
			boxFont = RessourcenEnummeration.FONT_CALIBRI_BOLD.getFont()
					.deriveFont(40f);
			boxTextLabel.setFont(boxFont);
			photoBoxes.add(boxTextLabel, BorderLayout.NORTH);
			JPanel boxMainPanel = new JPanel();
			photoBoxes.initialiseElements(boxMainPanel, StartScreenElement.BOX);

		} catch (OperationNotSupportedException onse) {
			ErrorMessageDialog.showMessage(null, onse.getMessage(),
					ERROR_TITLE, onse.getStackTrace().toString());
		} catch (IOException ioe) {
			ErrorMessageDialog.showMessage(null, ioe.getMessage(), ERROR_TITLE,
					ioe.getStackTrace().toString());
		} catch (FontFormatException ffe) {
			ErrorMessageDialog.showMessage(null, ffe.getMessage(), ERROR_TITLE,
					ffe.getStackTrace().toString());
		}
		return photoBoxes;
	}

	/**
	 * This method returns a set Color. Should be used to get a single
	 * background color for all screens
	 * 
	 * @return Color: Color, that is defined in BGColor
	 */
	public static Color getBGColor() {
		return backgroundColor;
	}

	/**
	 * This method sets the size of the StartScreen elements at the start of the
	 * program, depending on the screen size.
	 */
	public static void initialiseSizes() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		textSize = new Dimension(screenSize.width, 100);
		subSystemSize = new Dimension(screenSize.width * 8 / 10,
				screenSize.height / 2 - 20);
		scrollSize = new Dimension(subSystemSize.width * 3 / 4 - 20,
				subSystemSize.height - 90);
		elementSize = new Dimension(scrollSize.width / 3 - 7,
				scrollSize.height - 10);

	}

	/**
	 * this method changes the title text for the PhotoAlbums
	 */
	public static void retextAlbum() {
		albumText = PALBUMS + " (" + noOfAlbums + ")";
		albumTextLabel.setText(albumText);
		albumTextLabel.repaint();
	}

	/**
	 * this method changes the title text for the PhotoBoxes
	 */
	public static void retextBox() {
		boxText = PBOXES + " (" + noOfBoxes + ")";
		boxTextLabel.setText(boxText);
		boxTextLabel.repaint();
	}

	public static Dimension getScreenSize() {
		return screenSize;
	}

	public static Dimension getSubSystemSize() {
		return subSystemSize;
	}

	public static Dimension getScrollSize() {
		return scrollSize;
	}

	public static Dimension getElementSize() {
		return elementSize;
	}
}
