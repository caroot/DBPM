/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */


package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Font;



public class Infobar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 */
	
	private Photo photo;
	private String absolutePath = "C:/Temp/universe.jpg";

	
	public Infobar() {
		
		photo=new Photo("TestBild", absolutePath);
		photo.setComment(photo.getName());
		
		setPreferredSize(new Dimension(250, 223));
		setMaximumSize(new Dimension(250,200));
		setMinimumSize(new Dimension(250,200));
		
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
		
		JLabel lblInfo_1 = new JLabel("Info");
		lblInfo_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblInfo_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInfo_1, "2, 4, 9, 1");
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblSize, "2, 6");
		
		JLabel lblSize_filled = new JLabel("");
		add(lblSize_filled, "6, 6");
		
		JLabel lblPixel = new JLabel("Pixel:");
		lblPixel.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblPixel, "2, 10");
		
		JLabel lblPixel_filled = new JLabel("");
		add(lblPixel_filled, "6, 10");
		
		JLabel lblCreated = new JLabel("Created:");
		lblCreated.setFont(new Font("Calibri", Font.BOLD, 12));
		add(lblCreated, "2, 14");
		
		JLabel lblCreated_filled = new JLabel("");
		add(lblCreated_filled, "6, 14");
		
		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Calibri", Font.BOLD, 12));
		add(lblComment, "2, 18");
		
		JLabel lblComment_filled = new JLabel("");
		add(lblComment_filled, "6, 18");
	}	
	
}
