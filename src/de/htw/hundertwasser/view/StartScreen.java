package de.htw.hundertwasser.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.res.RessourcenEnummeration.*;

/**
 * Klasse die den StartScreen des DBPM anzeigt.
 * 
 * @author Fabian
 * @version 7.9.12
 */

public class StartScreen extends JFrame{
	//Konstanten
	public static final String DBPM = "Dunkelbunt Photo Manager";
	public static final String PBOXES = "Your Photo Boxes";
	public static final String PALBUMS = "Your Photo Albums";
	
	//Variablen
	private static JFrame mainScreen;
	private static Dimension screen ;
	private static SubSystemChoosingPanel photoBoxes;
	private static SubSystemChoosingPanel photoAlbums;
	
	public StartScreen() {
		super(DBPM);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Wenn man auf das X drückt wird das Programm beendet.
		setSize(getScreen());
		setLocationRelativeTo(null); //Setzt das Fenster in die Mitte
		setLayout( new GridLayout(0, 1, 0, 3)); //Anzahl der Spalten, Zeilen, Freiraum(L/R), Freiraum(O/U)
	}
	
	public static void main(String[] args) {
		mainScreen = new StartScreen(); //Initialisierung.
		initialisePhotoBoxes();
		initialisePhotoAlbums();
		mainScreen.setVisible(true);
	}
	
	private static void initialisePhotoAlbums() {
		photoAlbums = new SubSystemChoosingPanel();
		photoAlbums.setBackground(Color.WHITE);
		JLabel albumText = new JLabel(PALBUMS, JLabel.CENTER);
		albumText.setForeground(Color.BLACK);
		albumText.setFont(new Font("Serif", 0, 40)); //Hier wird schriftart und größe bestimmt... Globalisieren?
		photoAlbums.add(albumText, BorderLayout.NORTH);
		JPanel albumMainPanel = new JPanel();
		photoAlbums.addMainElementPanel(albumMainPanel, ElementPanel.ALBUM);
		mainScreen.add(new JScrollPane(photoAlbums));
		
	}

	private static void initialisePhotoBoxes() {
		photoBoxes = new SubSystemChoosingPanel();
		photoBoxes.setBackground(Color.WHITE);
		JLabel boxText = new JLabel(PBOXES, JLabel.CENTER);
		boxText.setForeground(Color.BLACK);
		boxText.setFont(new Font("Serif", 0, 40)); //Hier wird schriftart und größe bestimmt... Globalisieren?
		photoBoxes.add(boxText, BorderLayout.NORTH);
		JPanel boxMainPanel = new JPanel();
		photoBoxes.addMainElementPanel(boxMainPanel, ElementPanel.BOX);
		mainScreen.add(new JScrollPane(photoBoxes));
		
	}
	
	public static String getIconPath(String name) {
		String path ="de/htw/hundertwasser/res/"+name;
		return path;
	}

	public static Dimension getScreen() {
		if(screen == null)
			screen = Toolkit.getDefaultToolkit().getScreenSize();
		return screen;
	}
}

class SubSystemChoosingPanel extends JPanel {
	
	//Variablen
	JPanel mainElements = null;
	
	public SubSystemChoosingPanel() {
		setLayout(new BorderLayout(5, 5));
	}
	
	public void addMainElementPanel(JPanel mainPanel, int typ) {
		if(mainElements != null)
			return;
		mainElements = mainPanel;
		mainElements.setLayout(new GridLayout(0, 4, 1, 1));
//		mainElements.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainElements.setBackground(Color.WHITE);
		add(mainElements);
		if(typ == ElementPanel.ALBUM)
			mainElements.add(new ElementPanel(ElementPanel.ALBUM, ElementPanel.ADDITION, mainElements));
		else
			mainElements.add(new ElementPanel(ElementPanel.BOX, ElementPanel.ADDITION, mainElements));
	}
}

class StartScreenToolPanel extends JPanel {
	
	//Variablen
	JPanel mainElements = null;
	
	public StartScreenToolPanel() {
		setLayout(new GridLayout(0, 4, 1, 5));
		setBackground(Color.WHITE);
		URL buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.OEFFNEN.getName()));
		Icon addIcon = new ImageIcon(buttonImg);
		JLabel openButton = new JLabel(addIcon);
		openButton.setBackground(Color.WHITE);
		add(openButton);
		buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.UMBENENNEN.getName()));
		addIcon = new ImageIcon(buttonImg);
		JLabel renameButton = new JLabel(addIcon);
		renameButton.setBackground(Color.WHITE);
		add(renameButton);
		buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.SENDEN.getName()));
		addIcon = new ImageIcon(buttonImg);
		JLabel sendButton = new JLabel(addIcon);
		sendButton.setBackground(Color.WHITE);
		add(sendButton);
		buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.LOESCHEN.getName()));
		addIcon = new ImageIcon(buttonImg);
		JLabel deleteButton = new JLabel(addIcon);
		deleteButton.setBackground(Color.WHITE);
		add(deleteButton);
	}
	
	public void addMainElementPanel(JPanel mainPanel, int typ) {
		if(mainElements != null)
			return;
		mainElements = mainPanel;
//		mainElements.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainElements.setBackground(Color.WHITE);
		add(mainElements);
		if(typ == ElementPanel.ALBUM)
			mainElements.add(new ElementPanel(ElementPanel.ALBUM, ElementPanel.ADDITION, mainElements));
		else
			mainElements.add(new ElementPanel(ElementPanel.BOX, ElementPanel.ADDITION, mainElements));
	}
}

 class ElementPanel extends JPanel {
	//Konstanten
	public static final int ELEMENT = 0;
	public static final int ADDITION = 1;
	public static final int ALBUM = 0;
	public static final int BOX = 1;
	public static final String ALBUMSTR = "Album";
	public static final String BOXSTR = "Box";
	
	//variablen
	private URL buttonImg = null;
	private int elementTyp;
	private JPanel parentPanel = null;
	
	public ElementPanel() {
		super();
	}
	
	public ElementPanel(int elementTyp, int panelTyp, JPanel parentPanel) {
		this.elementTyp = elementTyp;
		if(panelTyp == ADDITION)
			makeAdditionButton();
		if(panelTyp == ELEMENT)
			makeElementButton();
		this.parentPanel = parentPanel;
		setLayout(new GridLayout(2, 1, 1, 0));
		setBackground(new Color(0,0,0,0)); //Hintergrund Schwarz Transperent.
		repaint();
	}

	private void makeElementButton() {
		setSize(100,200);
		JButton button;
		if(elementTyp == ALBUM) {
			buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.PHOTOALBUM.getName()));
			Icon elementIcon = new ImageIcon(buttonImg);
			button = new JButton(elementIcon);
		} else { 
			buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.PHOTOBOX.getName()));
			Icon elementIcon = new ImageIcon(buttonImg);
			button = new JButton(elementIcon);
		}
		ActionListener addListen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openToolPanel();
			}
		};
		button.addActionListener(addListen);
		button.setBackground(Color.WHITE);
		add(button);
	}

	private void makeAdditionButton() {		
		setSize(100,200);
		if(elementTyp == ALBUM) {
			buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.PHOTOALBUM_HINZUFUEGEN.getName()));		
		} else {
			System.out.println(StartScreen.getIconPath("open"));
			buttonImg = ClassLoader.getSystemResource(StartScreen.getIconPath(RessourcenEnummeration.PHOTOBOX_HINZUFUEGEN.getName()));
		}
		Icon addIcon = new ImageIcon(buttonImg);
		JButton addButton = new JButton(addIcon);
		addButton.setBackground(Color.WHITE);
		
		ActionListener addListen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.add(new ElementPanel(elementTyp, ELEMENT, parentPanel));
				parentPanel.repaint();
			}
		};
		addButton.addActionListener(addListen);
		add(addButton);
	}
	
	private void openToolPanel() {
		add(new StartScreenToolPanel());
	}

}



