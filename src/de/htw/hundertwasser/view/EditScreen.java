package de.htw.hundertwasser.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	// Variablen
	public static JFrame editScreen;
	public static Dimension screenSize;
	public static Dimension subSystemSize;
	public static Dimension scrollSize;
	public static Dimension elementSize;

	public EditScreen() {

		setVisible(true);
		setSize(screenSize);
		setResizable(true);
		setTitle(DBPM);
		addWindowListener(this);

		JButton nextButton = new JButton("NEXT");
		nextButton.setBounds(140, 279, 60, 35);
		getContentPane().add(nextButton);

		JButton btnNewButton_1 = new JButton("LAST");
		btnNewButton_1.setBounds(140, 279, 40, 35);
		getContentPane().add(btnNewButton_1);

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
		initialiseSizes();
		editScreen = new EditScreen();
		// PBES = new PhotoBoxEditScreen(); //Initialisierung.
		// JSplitPane subPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		// subPane.setDividerLocation(768/2-50);
		// mainScreen.add(subPane);
		// subPane.add(initialisePhotoBoxes());
		// subPane.add(initialisePhotoAlbums());

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
