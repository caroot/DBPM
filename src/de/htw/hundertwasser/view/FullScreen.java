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

public class FullScreen extends JFrame {
	
	ImageViewer iv = new ImageViewer();
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	
	// Variablen zum übergeben der 
	
	public boolean actionListener0 = false;
	public boolean actionListener1 = false;
	public boolean actionListener2 = false;
	
	private static int MAX_WIDTH=640;
	private static int MAX_HEIGHT=480;
	
	public FullScreen() {

		
		setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));	
		
		setUndecorated(true);
		setState(JFrame.MAXIMIZED_BOTH);
		setTitle("Fullscreen");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(iv, BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("");
		panel_1.add(btnNewButton);
		btnNewButton.setAction(action);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setIcon(new ImageIcon(FullScreen.class.getResource("/de/htw/hundertwasser/res/fs_arrow_left_small.png")));
		
		JButton btnNewButton_1 = new JButton("");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setSelectedIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen_small.png")));
		btnNewButton_1.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen_small.png")));
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(btnNewButton_2);
		btnNewButton_2.setAction(action_2);
		btnNewButton_2.setIcon(new ImageIcon(FullScreen.class.getResource("/de/htw/hundertwasser/res/fs_arrow_right_small.png")));
		btnNewButton_2.setBackground(SystemColor.activeCaption);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Click on left arrow");
		}
		public void actionPerformed(ActionEvent e) {
			actionListener0 = true;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "CLcik on the minimize button");
		}
		public void actionPerformed(ActionEvent e) {
			actionListener1 = true;
			dispose();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			actionListener2 = true;
		}
	}

	
	public boolean getActionListener0(){
		return actionListener0;
		}
	
	public boolean getActionListener1(){
		return actionListener0;
	    }
	
	public boolean getActionListener2(){
			return actionListener2;
		}
 
 
	
	public static void main(String[] args) {
		FullScreen fs = new FullScreen();
		fs.setVisible(true);
	}
}

