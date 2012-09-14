package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

/**
 * Diese Klasse EditScreen BITTE HIER DATEN VON RAMIS EINTRAGEN!!!!!!!!!!!!
 * 
 * @author RAMIS SABRI
 * 
 */
public class EditScreen extends JFrame implements WindowListener {

	// Konstanten
	private static final long serialVersionUID = 1L;
	public static final String DBPM = "Dunkelbunt Photo Manager";
	private static final int MAXWIDTH = 1024;
	private static final int MAXHEIGHT = 768;
	
	
	// Variablen
	protected static Dimension screenSize;
	protected static Dimension subSystemSize;
	protected static Dimension scrollSize;
	protected static Dimension elementSize;
	protected JPanel centralPanel; //Central-Element
	protected JPanel controlPanel; //Control-Element
	protected JPanel attractivePanel;
	protected NavigationBar navigationBar;
	protected ThumbnailBar thumbnailBar;
	protected JLabel title;
	protected JButton previous;
	protected JButton next;
	protected ToolBar toolBar;
	protected Infobar infoBar;
	

	public EditScreen() {

		setVisible(true);
		setPreferredSize(new Dimension(MAXWIDTH, MAXHEIGHT));
		setMinimumSize(new Dimension(MAXWIDTH, MAXHEIGHT));
		setResizable(true);
		setTitle(DBPM);
		addWindowListener(this);
		
		//Create Elements
		title = new JLabel(DBPM);
		navigationBar = new NavigationBar();
		infoBar = new Infobar();
		toolBar = new ToolBar();
		//TODO:Kucke nach ausehen
		controlPanel = new JPanel(new BorderLayout(10,10));
		centralPanel = new JPanel(new BorderLayout(10,10));
		attractivePanel = new JPanel(new BorderLayout(10,10));
		
		// Buttons erzeugen
	 next = new JButton("Left");
		 previous = new JButton("Right");
		
		setLayout(new BorderLayout(10,10));
		add(title,BorderLayout.NORTH);
//		add(navigationBar,BorderLayout.WEST);
		//Thumbnailbar muss noch implementiert werden
		//add(thumbnailbar,BorderLayout.SOUTH);
		add(controlPanel,BorderLayout.EAST);
		add(centralPanel,BorderLayout.CENTER);
		
		
		// ContentPane haelt standardmaeßig ein BorderLayout
        // Hinzufuegen der Buttons zum Content Pane des JFrames
        centralPanel.add(previous, BorderLayout.WEST);
        centralPanel.add(next, BorderLayout.EAST);
        centralPanel.add(attractivePanel,BorderLayout.CENTER);
        controlPanel.add(infoBar,BorderLayout.NORTH);
        controlPanel.add(toolBar,BorderLayout.SOUTH);
        pack();
        
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
//		initialiseSizes();
		EditScreen ed =  new EditScreen();
		ed.setVisible(true);
		ed.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Screengröße ermitteln und Window nach der Größe anpassen
	 */
	public static void initialiseSizes() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		subSystemSize = new Dimension(screenSize.width, screenSize.height / 2);
		scrollSize = new Dimension(subSystemSize.width * 3 / 4 - 20,
				subSystemSize.height - 81);
		System.out.println(scrollSize);
		elementSize = new Dimension(scrollSize.width / 3 - 6,
				scrollSize.height - 22);

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
