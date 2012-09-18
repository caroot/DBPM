package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.htw.hundertwasser.core.Infobar;
import de.htw.hundertwasser.core.NavigationBar;
import de.htw.hundertwasser.core.ThumbnailBar;
import de.htw.hundertwasser.core.ToolBar;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Klasse die den EditScreen anzeigt
 * 
 * @author RAMIS SABRI
 * 
 */
public class EditScreen extends JFrame implements WindowListener {

	// Konstanten
	private static final long serialVersionUID = 1L;
	public static final String DBPM = "Dunkelbunt Photo Manager";

	// Variablen
	protected static Dimension screenSize;
	protected static Dimension subSystemSize;
	protected static Dimension scrollSize;
	protected static Dimension elementSize;
	protected static Dimension textSize;
	protected JPanel centralPanel; // Central-Element
	protected JPanel controlPanel; // Control-Element
	protected JPanel attractivePanel;
	protected JPanel buttonPanelleft;
	protected JPanel buttonPanelright;
	protected NavigationBar navigationBar;
	protected ThumbnailBar thumbnailBar;
	protected JButton previous;
	protected JButton next;
	protected ToolBar toolBar;
	protected Infobar infoBar;
	private JLabel middle;

	public EditScreen() {

		setPreferredSize(screenSize);
		setResizable(true);		
		setTitle(DBPM);
		addWindowListener(this);

		// Create Elements

		navigationBar = new NavigationBar();	 // Navigation  Links
		infoBar = new Infobar();				 // Information Rechts Oben
		toolBar = new ToolBar();				 // Toolbar		Rechts Unten
		thumbnailBar = new ThumbnailBar();		 // Thumbnail	Unten 

//		title = new JLabel(DBPM, JLabel.CENTER);
		navigationBar = new NavigationBar();
		infoBar = new Infobar();
		toolBar = new ToolBar();
		thumbnailBar = new ThumbnailBar();
		
		
		
		
		
		
		// Panel, die den PictureViewer beinhaltet
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout(10, 10)); // und LayoutManager hinzufügen
		// Panel, der die NavigationBar beinhaltet
		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout(10, 10)); // und LayoutManager hinzufügen
		// Panels, die die Buttons beinhaltet
		buttonPanelleft = new JPanel(new GridLayout(10,0));
		buttonPanelright= new JPanel(new GridLayout(10,0));
		
		
			
		// Hinzufügen von INFOBAR und TOOLBAR in den CONTROLPANEL
		controlPanel.add(infoBar, BorderLayout.NORTH);
		controlPanel.add(toolBar, BorderLayout.CENTER);
		// Hinzufügen der beiden Panels für Buttons
		centralPanel.add(buttonPanelleft, BorderLayout.EAST);
		centralPanel.add(buttonPanelright, BorderLayout.WEST);
		middle = new JLabel("New label");
		centralPanel.add(middle, BorderLayout.CENTER);
		middle.setBackground(Color.BLACK);
		middle.setIcon(new ImageIcon(EditScreen.class.getResource("/de/htw/hundertwasser/res/delete.png")));
		middle.setBounds(40, 40, 230, 123);
		// Jeder Richtung etwas zuweisen
		getContentPane().setLayout(new BorderLayout(10, 10));

		//add(navigationBar,BorderLayout.WEST);

//		getContentPane().add(title, BorderLayout.NORTH);
		// add(navigationBar,BorderLayout.WEST);
		// Thumbnailbar muss noch implementiert werden
		// add(thumbnailbar,BorderLayout.SOUTH);
		getContentPane().add(controlPanel, BorderLayout.EAST);
		getContentPane().add(centralPanel, BorderLayout.CENTER);



		
		//attractivePanel = new JPanel(new BorderLayout(10, 10));
		
				
		// Button NEXT ( Rechts )
		next = new JButton();
		next.setToolTipText("Next");
		buttonPanelleft.add(new JPanel());
		buttonPanelleft.add(new JPanel());
		buttonPanelleft.add(new JPanel());
		buttonPanelleft.add(new JPanel());
		buttonPanelleft.add(next);
		//centralPanel.add(next, BorderLayout.EAST); // auf Panel hinzufügen	
		// Button mit Pfeil versehen
		next.setIcon(new ImageIcon(EditScreen.class.getResource("/de/htw/hundertwasser/res/arrow_right.png")));
		
		
		// Button Previous ( Links )
		previous = new JButton();
		previous.setToolTipText("Back");
		buttonPanelright.add(new JPanel());
		buttonPanelright.add(new JPanel());
		buttonPanelright.add(new JPanel());
		buttonPanelright.add(new JPanel());
		buttonPanelright.add(previous);
		//centralPanel.add(previous, BorderLayout.WEST); // auf Panel hinzufügen
		// Button mit Pfeil versehen
		previous.setIcon(new ImageIcon(EditScreen.class.getResource("/de/htw/hundertwasser/res/arrow_left.png")));
		
		
		
		
		
		

		// ContentPane haelt standardmaeßig ein BorderLayout
		// Hinzufuegen der Buttons zum Content Pane des JFrames
		centralPanel.add(previous, BorderLayout.WEST);
		centralPanel.add(next, BorderLayout.EAST);
		//centralPanel.add(attractivePanel, BorderLayout.CENTER);
		controlPanel.add(infoBar, BorderLayout.NORTH);
		controlPanel.add(toolBar, BorderLayout.CENTER);

		pack();

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
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
		initialiseSizes();
		EditScreen ed = new EditScreen();
		ed.setVisible(true);
		ed.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*
	 * Methode um die Displaygröße auszurechen
	 */
	public static void initialiseSizes() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	}

	/*
	 * Methoden um die Displaygröße herauszufinden
	 * 
	 * @return screenSize
	 */
	public static Dimension getScreenSize() {
		return screenSize;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		dispose();

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
