package de.htw.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.htw.hundertwasser.core.ImageViewer;
import de.htw.hundertwasser.res.RessourcenEnummeration;
/**
 * Beispiel zum Laden eines Bildes
 * @author daniel
 *
 */
public class TestView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestView()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageViewer iv = new ImageViewer();
		try
		{
		//SOO
			iv.setImage(RessourcenEnummeration.getImage(RessourcenEnummeration.ALBUM));
		//ODER SOO
			setIconImage(RessourcenEnummeration.PHOTOBOX.getImage());
			
			setPreferredSize(new Dimension(640,480));
		setMaximumSize(new Dimension(640,480));
		setMinimumSize(new Dimension(640, 480));
		setLayout(new BorderLayout());
		add(iv,BorderLayout.CENTER);
		}
		catch (IllegalArgumentException ex)
		{
			JOptionPane.showConfirmDialog(this, ex.getLocalizedMessage());
			
		}
		catch(IOException iex)
		{
			JOptionPane.showConfirmDialog(this, iex.getLocalizedMessage());
		}
	}
	
	public static void main(String[] args) {
		TestView tv = new TestView();
		tv.setVisible(true);
	}
	
}
