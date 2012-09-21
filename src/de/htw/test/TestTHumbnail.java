package de.htw.test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import de.htw.hundertwasser.core.ThumbnailBar;

public class TestTHumbnail extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestTHumbnail()
	{
		
		setLayout(new BorderLayout());
		add(new ThumbnailBar(),BorderLayout.CENTER);
		setPreferredSize(new Dimension(640,480));
	}
	
	public static void main(String[] args) {
		TestTHumbnail tm = new TestTHumbnail();
		tm.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tm.setVisible(true);
		
	}
}
