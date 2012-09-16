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



public class Infobar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 */
	private Photo photo;
	private String absolutePath = "C:/Users/Dominic/Pictures/pics/bild.jpg";
	
	
	public Infobar() {
		
		photo=new Photo("TestBild", absolutePath);
		photo.setComment(photo.getName());
		
		setPreferredSize(new Dimension(250,200));
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
		add(lblInfo, "4, 2, 3, 1");
		
		JLabel lblSize = new JLabel("Size:");
		add(lblSize, "2, 6");
		
		JTextField txtSize = new JTextField();
		txtSize.setBackground(Color.WHITE);
		txtSize.setEditable(false);
		add(txtSize, "6, 6, fill, default");
		txtSize.setColumns(10);
		
		JLabel lblPixel = new JLabel("Pixel:");
		add(lblPixel, "2, 10");
		
		JTextField txtPixel = new JTextField();
		txtPixel.setBackground(Color.WHITE);
		txtPixel.setEditable(false);
		add(txtPixel, "6, 10, fill, default");
		txtPixel.setColumns(10);
		
		JLabel lblCreated = new JLabel("Created:");
		add(lblCreated, "2, 14");
		
		JTextField txtCreated = new JTextField();
		txtCreated.setBackground(Color.WHITE);
		txtCreated.setEditable(false);
		add(txtCreated, "6, 14, fill, default");
		txtCreated.setColumns(10);
		
		JLabel lblComment = new JLabel("Comment:");
		add(lblComment, "2, 18");
		
		JTextField txtComment = new JTextField(photo.getComment());
		txtComment.setBackground(Color.WHITE);
		txtComment.setEditable(false);
		add(txtComment, "6, 18, fill, default");
		txtComment.setColumns(5);
	}	
	
}
