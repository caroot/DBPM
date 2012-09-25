package de.htw.hundertwasser.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * The First Screen whitin the Application
 * it will shown up at startup.
 * @author daniel
 *
 */
public class SplashScreenView extends JFrame{

	private JLabel photoBoxLoad = null;
	private JLabel photoAlbumLoad = null;
	private JLabel integrityCheck = null;
	private JLabel runApplication = null;
	private JLabel information = null;
	private JProgressBar progressBar = null;
	
	public SplashScreenView()
	{
		photoBoxLoad = new JLabel();
		photoAlbumLoad = new JLabel();
		integrityCheck = new JLabel();
		information = new JLabel();
		progressBar = new JProgressBar();
	}
}
