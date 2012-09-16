package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	protected JPanel centralPanel; // Central-Element
	protected JPanel controlPanel; // Control-Element
	protected JPanel attractivePanel;
	protected NavigationBar navigationBar;
	protected ThumbnailBar thumbnailBar;
	protected JLabel title;
	protected JButton previous;
	protected JButton next;
	protected ToolBar toolBar;
	protected Infobar infoBar;

	public EditScreen() {

		
		setPreferredSize(new Dimension(MAXWIDTH, MAXHEIGHT));
		setMinimumSize(new Dimension(MAXWIDTH, MAXHEIGHT));
		setResizable(true);
		setTitle(DBPM);
		addWindowListener(this);

		// Create Elements
		title = new JLabel(DBPM, JLabel.CENTER);
		navigationBar = new NavigationBar();
		infoBar = new Infobar();
		toolBar = new ToolBar();
		thumbnailBar = new ThumbnailBar();
		
		
		
		
		
		// TODO:Kucke nach ausehen

		// Panel, die die Infobar und Toolbar beinhaltet
		controlPanel = new JPanel(new BorderLayout(10, 10));
		// Panels, die den PictureViewer beinhaltet
		centralPanel = new JPanel(new BorderLayout(10, 10));
		attractivePanel = new JPanel(new BorderLayout(10, 10));
		
		

		// Buttons
		next = new JButton();
		previous = new JButton();
		
		// Buttons mit Pfeile versehen
		previous.setIcon(new ImageIcon(EditScreen.class.getResource("/de/htw/hundertwasser/res/arrow_left.png")));
		
		next.setIcon(new ImageIcon(EditScreen.class.getResource("/de/htw/hundertwasser/res/arrow_right.png")));
		

		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().add(title, BorderLayout.NORTH);
		// add(navigationBar,BorderLayout.WEST);
		// Thumbnailbar muss noch implementiert werden
		// add(thumbnailbar,BorderLayout.SOUTH);
		getContentPane().add(controlPanel, BorderLayout.EAST);
		getContentPane().add(centralPanel, BorderLayout.CENTER);
		
		

		// ContentPane haelt standardmaeﬂig ein BorderLayout
		// Hinzufuegen der Buttons zum Content Pane des JFrames
		centralPanel.add(previous, BorderLayout.WEST);
		centralPanel.add(next, BorderLayout.EAST);
		centralPanel.add(attractivePanel, BorderLayout.CENTER);
		controlPanel.add(infoBar, BorderLayout.NORTH);
		controlPanel.add(toolBar, BorderLayout.SOUTH);
		pack();
		
		

	}

	
	private ImageIcon createImage(String string) {
		// TODO Auto-generated method stub
		return null;
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
		EditScreen ed = new EditScreen();
		ed.setVisible(true);
		ed.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
