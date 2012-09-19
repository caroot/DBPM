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
		super(DBPM);
		initialiseSizes();
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Wenn man auf das X drückt wird das Programm beendet.
		setSize(screenSize);
//
//		setLocationRelativeTo(null); //Setzt das Fenster in die Mitte
//		setLayout( new GridLayout(0, 1, 0, 1)); //Anzahl der Spalten, Zeilen, Frewwwwwiraum(L/R), Freiraum(O/U)
	}
	
	public static void main(String[] args) {
		try {
//			for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels())
//			{
//				System.out.println(info.getClassName());
//			}
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(
			"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
//			"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
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
		
		PhotoAlbumEditScreen paes = new PhotoAlbumEditScreen();
//		paes.setSize(screenSize);
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



