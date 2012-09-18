package de.htw.hundertwasser.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements Serializable{
	private Image img=null;
	private String text = null;
	private boolean isActive = false;
	private Image baseImage = null;
	
	public ImagePanel(){
		super();
	}
	
	public ImagePanel(URL imagePath){
		this();
		setBackground(Color.WHITE); //Hintergrund Schwarz Transperent.
		setVisible(true);
		setImage(imagePath);
	}
	
	public void setImage(Image img){
		this.img=img;
		baseImage = img;
		repaint();
	}
	
	public void setImage(URL imagePath) {
		Image pic = Toolkit.getDefaultToolkit().getImage(imagePath);
		setImage(pic);	
	}
	 
}
