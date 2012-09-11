package de.htw.hundertwasser.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.res.RessourcenEnummeration.*;

/**
 * Klasse die den StartScreen des DBPM anzeigt.
 * 
 * @author Fabian
 * @version 9.9.12
 */

public class StartScreen extends JFrame{
	//Konstanten
	public static final String DBPM = "Dunkelbunt Photo Manager";
	public static final String PBOXES = "Your Photo Boxes";
	public static final String PALBUMS = "Your Photo Albums";
	
	//Variablen
	private static JFrame mainScreen;
	private static Dimension screen;
	private static StartScreenSubPanel photoBoxes;
	private static StartScreenSubPanel photoAlbums;
	private static Color backgroundColor = Color.WHITE; //Hintergrundfarbe des StartScreens
	
	public StartScreen() {
		super(DBPM);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Wenn man auf das X drückt wird das Programm beendet.
		setSize(getScreen());
		setLocationRelativeTo(null); //Setzt das Fenster in die Mitte
		setLayout( new GridLayout(0, 1, 0, 3)); //Anzahl der Spalten, Zeilen, Freiraum(L/R), Freiraum(O/U)
	}
	
	public static void main(String[] args) {
		try {
//			for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
//			{
//				System.out.println(info.getClassName());
//			}
////			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainScreen = new StartScreen(); //Initialisierung.
		initialisePhotoBoxes();
		initialisePhotoAlbums();
		mainScreen.setVisible(true);
		
	}
	
	/**
	 * Methode die das SubPanel für die Photoalben erstellt.
	 */
	private static void initialisePhotoAlbums() {
		photoAlbums = new StartScreenSubPanel();
		photoAlbums.setBackground(getBGColor());  //Hintergrundfarbe einstellen
		JLabel albumText = new JLabel(PALBUMS, JLabel.CENTER); //Text erstellen
		albumText.setForeground(Color.BLACK); //Textfarbe einstellen
		albumText.setFont(new Font("Serif", 0, 40)); //Hier wird schriftart und größe bestimmt... Globalisieren?
		photoAlbums.add(albumText, BorderLayout.NORTH);  //Text in das GUI einfügen
		JPanel albumMainPanel = new JPanel();
		photoAlbums.initialiseElements(albumMainPanel, StartScreenElement.ALBUM); //SubPanel wird hier erstellt
		mainScreen.add(photoAlbums);
	}

	/**
	 * Methode die das SubPanel für die Photoboxen erstellt.
	 */
	private static void initialisePhotoBoxes() {
		photoBoxes = new StartScreenSubPanel();
		photoBoxes.setBackground(getBGColor());
		JLabel boxText = new JLabel(PBOXES, JLabel.CENTER);
		boxText.setForeground(Color.BLACK);
		boxText.setFont(new Font("Serif", 0, 40)); //Hier wird schriftart und größe bestimmt... Globalisieren?
		photoBoxes.add(boxText, BorderLayout.NORTH);
		JPanel boxMainPanel = new JPanel();
		photoBoxes.initialiseElements(boxMainPanel, StartScreenElement.BOX);
		mainScreen.add(photoBoxes);
		
	}
	
	/**
	 * Methode die die Bildschirmauflösung zurückgibt.
	 * @return
	 */
	public static Dimension getScreen() {
		if(screen == null)
			screen = Toolkit.getDefaultToolkit().getScreenSize();
		return screen;
	}
	
	/**
	 * Methode die eine Farbe zurückliefert.
	 * sollte benutztwerdne um eine einheitliche Hintergrundfarbe zu verwenden.
	 * @return Color: Farbe, die in backGroundColor steht
	 */
	public static Color getBGColor() {
		return backgroundColor;
	}
}



