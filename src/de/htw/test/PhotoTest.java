package de.htw.test;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import de.htw.hundertwasser.core.ImageViewer;
import de.htw.hundertwasser.core.Photo;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

public class PhotoTest {
public static void main(String[] args) {
	Photo photo = new Photo("Test", "Bildschirmfoto vom 2012-08-10 07:17:42.png");   
	  BufferedImage img = null;
	  try {
	   img = photo.getImage();
	  } catch (FileNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (InsufficientPrivilegesException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  try {  
		  JFrame myFrame = new JFrame();
		  ImageViewer iv = new ImageViewer();
		  myFrame.setLayout(new BorderLayout());
		  myFrame.add(iv,BorderLayout.CENTER);
		  iv.setImage(img);
		  myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  myFrame.setVisible(true);
	   }
	  catch (Exception e)
	  {
		  
	   System.out.println("NULL");
	  }
}
	
	
}
