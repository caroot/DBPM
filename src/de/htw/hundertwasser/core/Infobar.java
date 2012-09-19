/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */


package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;



public class Infobar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 */
	
	private Photo photo;
//	private String absolutePath = "C:/Temp/universe.jpg";
	private String absolutePath = "C:/Users/Dominic/Pictures/pics/bild.jpg";

	
	public Infobar() {
		
		photo=new Photo("TestBild", absolutePath);
		photo.setComment(photo.getName());
		
//		setPreferredSize(new Dimension(250, 223));
//		setMaximumSize(new Dimension(250,200));
//		setMinimumSize(new Dimension(250,200));
		
		setBackground(Color.WHITE);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblInfo, "2, 2, 9, 1");
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblSize, "2, 6");
		
		JLabel lblSize_filled = new JLabel(new Long(getFileSize()).toString());
		lblSize_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSize_filled, "6, 6");
		
		JLabel lblKb = new JLabel("KB");
		add(lblKb, "8, 6");
		
		JLabel lblPixel = new JLabel("Pixel:");
		lblPixel.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblPixel, "2, 10");
		
		JLabel lblPixel_filled = new JLabel(getPixel());
		lblPixel_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPixel_filled, "6, 10");
		
		JLabel lblCreated = new JLabel("Created:");
		lblCreated.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblCreated, "2, 14");
		
		JLabel lblCreated_filled = new JLabel("");
		add(lblCreated_filled, "6, 14");
		
		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblComment, "2, 18");
		
		JLabel lblComment_filled = new JLabel(photo.getComment());
		lblComment_filled.setFont(new Font("Arial", Font.PLAIN, 12));
		lblComment_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblComment_filled, "6, 18");
	}	
	
	
	private int getPhotoHeight(){
		int height = 0;
		try {
			height = photo.getImage().getHeight();
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
		
//		System.out.println(height);
	return height;
}
	
	private int getPhotoWidth(){
		int width = 0;
		try {
			width = photo.getImage().getWidth();
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
//		System.out.println(width);
	return width;
	}
	
	private String getPixel(){
		
		StringBuffer sb = new StringBuffer();
		sb.append(new Integer(getPhotoHeight()).toString()).append(" x ").append(new Integer( getPhotoWidth()).toString());
		
		
		return sb.toString();
	}
	
	
	private long getFileSize(){
		
		File file = new File(photo.getPathToFile());
		
		return (file.length()/1000);
	}
}