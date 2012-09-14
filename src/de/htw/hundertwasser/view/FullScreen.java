public package de.htw.hundertwasser.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htw.hundertwasser.core.ImageViewer;

/**
 * Klasse die zum anzeigen des ausgewählten Photos im Fullscreenmodus zu zeigen
 * @author johannesschramm
 *
 */

public abstract class FullScreen extends JFrame {
	
	ImageViewer iv = new ImageViewer();
	
	// boolean variables
	
	public boolean actionListener0 = false;
	public boolean actionListener1 = false;
	public boolean actionListener2 = false;
	
	private static int MAX_WIDTH=640;
	private static int MAX_HEIGHT=480;
	
	// protected buttons
	
	protected JButton leftArrow;
	protected JButton rightArrow;
	protected JButton exitButton;
	private final Action action = new SwingAction();
	
	public FullScreen() {

		// set settings ViewerPanel
		
		setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));	
		
		setUndecorated(true);
		setState(JFrame.MAXIMIZED_BOTH);
		setTitle("Fullscreen");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel Viewerpanel = new JPanel();
		Viewerpanel.setLayout(new BorderLayout());
		Viewerpanel.add(iv, BorderLayout.CENTER);
		Viewerpanel.setBackground(Color.WHITE);
		Viewerpanel.setForeground(Color.WHITE);
		getContentPane().add(Viewerpanel, BorderLayout.CENTER);
		
		// set settings Toolbarpanel
		
		JPanel Toolbarpanel = new JPanel();
		Toolbarpanel.setBackground(Color.WHITE);
		getContentPane().add(Toolbarpanel, BorderLayout.SOUTH);
		
		
		// left arrow button
		
		leftArrow= new JButton("");
		Toolbarpanel.add(leftArrow);
		leftArrow.setBackground(Color.WHITE);
		leftArrow.setIcon(new ImageIcon(FullScreen.class.getResource("/de/htw/hundertwasser/res/fs_arrow_left_small.png")));
		
		// exitbutton
		
		exitButton = new JButton("");
		Toolbarpanel.add(exitButton);
		exitButton.setSelectedIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen_small.png")));
		exitButton.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen_small.png")));
		exitButton.setBackground(Color.WHITE);
		
		// right arrow button
		
		rightArrow = new JButton("");
		rightArrow.setVerticalAlignment(SwingConstants.BOTTOM);
		Toolbarpanel.add(rightArrow);
		rightArrow.setIcon(new ImageIcon(FullScreen.class.getResource("/de/htw/hundertwasser/res/fs_arrow_right_small.png")));
		rightArrow.setBackground(Color.WHITE);
	}
	
		// abstract delivery methods

	public abstract ActionListener getExitButtonListener();
	
	public abstract ActionListener getRightArrowListener();
	
	public abstract ActionListener getLeftArrowListener();
	

        // main method
	
	//public static void main(String[] args) {
		//FullScreen fs = new FullScreen();
		//fs.setVisible(true);
	//}
	
		
	
}

