package de.htw.test;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.htw.hundertwasser.view.PhotoAlbumEditScreen;


public class paes extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Konstanten
	public static final String DBPM = "Dunkelbunt Photo Manager";
	public static Dimension screenSize;
	public static Dimension textSize;
	public static Dimension subSystemSize;
	public static Dimension scrollSize;
	public static Dimension elementSize; 

	
	
	public paes() {
//		super(DBPM);
//		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//		initialiseSizes();
//		setBackground(Color.BLACK);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Wenn man auf das X drückt wird das Programm beendet.
		

//		setLocationRelativeTo(null); //Setzt das Fenster in die Mitte
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
			"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
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
//		System.out.println(screenSize);
		PhotoAlbumEditScreen paes = new PhotoAlbumEditScreen();
//		paes.setLocationRelativeTo(null);
//		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println(screenSize);
//		paes.setPreferredSize(screenSize);
		
//		paes.setBackground(Color.BLACK);
		paes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Wenn man auf das X drückt wird das Programm beendet.
		paes.setVisible(true);
	}
	
	
	public static void initialiseSizes() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		textSize = new Dimension(screenSize.width, 100);
		subSystemSize = new Dimension( screenSize.width*8/10, screenSize.height/2-20);
		scrollSize = new Dimension(subSystemSize.width*3/4-20, subSystemSize.height-111);
		elementSize = new Dimension(scrollSize.width/3-7, scrollSize.height-10);
		
	}




}



