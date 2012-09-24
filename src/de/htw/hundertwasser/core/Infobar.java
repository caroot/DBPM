/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 20.09.12
 */

package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.htw.hundertwasser.backend.ImageManagerSize;
import de.htw.hundertwasser.core.interfaces.ThumbNailBarObserver;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;

/**
 * Class that creates the Infobar
 */
public class Infobar extends JPanel implements ThumbNailBarObserver {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final long KBMINSIZE = 1024;
	private static final long MBMINSIZE = 1048576;
	private static final long GBMINSIZE = 1073741824;

	// Variables
	private Photo photo;
	// private String absolutePath = "C:/Temp/universe.jpg";
//	private String absolutePath = "Motivations Bild v2.jpg";

	private JLabel lblComment_filled;
	private JLabel lblPixel_filled;
	private JLabel lblSize_filled;
	private JLabel lblCreated_filled;
	private JLabel lblKb;
	private JLabel lblName_filled;
	private BufferedImage img;

	/**
	 * Constructor
	 */
	public Infobar() {

//		photo = new Photo("Motivation!", absolutePath);
//		photo.setComment(":)");

		// setPreferredSize(new Dimension(250, 223));
		// setMaximumSize(new Dimension(250,200));
		// setMinimumSize(new Dimension(250,200));

		setBackground(Color.WHITE);
		setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(7dlu;default)"),
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
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblInfo = new JLabel("          Info");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblInfo, "2, 2, 11, 1");

		JLabel lblseparator = new JLabel("             -------------------------");
		lblseparator.setFont(new Font("Arial", Font.PLAIN, 12));
		lblseparator.setVerticalAlignment(SwingConstants.TOP);
		lblseparator.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblseparator, "2, 3, 11, 2, center, default");

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblName, "2, 6");

		lblName_filled = new JLabel("");
		lblName_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName_filled, "8, 6");

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblSize, "2, 8");

		lblSize_filled = new JLabel("");
		lblSize_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSize_filled, "8, 8, center, default");

		lblKb = new JLabel("KB");
		add(lblKb, "10, 8");

		JLabel lblPixel = new JLabel("Pixel:");
		lblPixel.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblPixel, "2, 10");

		lblPixel_filled = new JLabel();
		lblPixel_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPixel_filled, "8, 10, center, default");

		JLabel lblCreated = new JLabel("Created:");
		lblCreated.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblCreated, "2, 12");

		lblCreated_filled = new JLabel("");
		add(lblCreated_filled, "8, 12, center, default");

		lblComment_filled = new JLabel();
		lblComment_filled.setFont(new Font("Arial", Font.PLAIN, 12));
		lblComment_filled.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblComment_filled, "6, 14, 5, 1, center, default");

		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblComment, "2, 14");

		JLabel label = new JLabel("");
		add(label, "2, 16, 10, 1");
//		setPhoto(photo);
	}

	/**
	 * Function that returns the Photo height
	 * 
	 * @return Height of the photo
	 */
	private int getPhotoHeight(Photo photo) {
		int height = 0;
		try {
			height = photo.getImage().getHeight();
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		} catch (InsufficientPrivilegesException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		}

		// System.out.println(height);
		return height;
	}

	/**
	 * Function that returns the Photo width
	 * 
	 * @return Width of the photo
	 */
	private int getPhotoWidth(Photo photo) {
		int width = 0;
		try {
			width = photo.getImage().getWidth();
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		} catch (InsufficientPrivilegesException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		}
		// System.out.println(width);
		return width;
	}

	/**
	 * Function that returns the Photo display size
	 * 
	 * @return display size of the photo
	 */
	private String getPixel(Photo photo) {

		StringBuffer sb = new StringBuffer();
		sb.append(new Integer(getPhotoHeight(photo)).toString()).append(" x ")
				.append(new Integer(getPhotoWidth(photo)).toString());

		return sb.toString();
	}

	/**
	 * Function that returns the Photo size
	 * 
	 * @return Size of the photo
	 * @throws FileNotFoundException
	 *             ,IllegalArgumentException
	 */
	private double getFileSize(Photo photo) throws FileNotFoundException,
			IllegalArgumentException {

		double size = photo.getSize(ImageManagerSize.BYTE);
		if (size <= KBMINSIZE) {
			lblKb.setText(ImageManagerSize.BYTE.getSizeName());
		}
		if (size > KBMINSIZE && size <= MBMINSIZE) {
			lblKb.setText(ImageManagerSize.KILOBYTE.getSizeName());
			return (photo.getSize(ImageManagerSize.KILOBYTE));
		}
		if (size > MBMINSIZE && size <= GBMINSIZE) {
			lblKb.setText(ImageManagerSize.MEGABYTE.getSizeName());
			return (photo.getSize(ImageManagerSize.MEGABYTE));
		}
		lblKb.setText(ImageManagerSize.GIGABYTE.getSizeName());
		return (photo.getSize(ImageManagerSize.GIGABYTE));
	}

	/**
	 * Function that returns the Photo date
	 * 
	 * @return Date of the photo
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	private Date getModifiedDate(Photo photo) throws FileNotFoundException,
			IllegalArgumentException {
		return photo.getLastModifiedDate();
	}

	/**
	 * Function that formats the Date
	 * 
	 * @return Date
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	private String formatDate(Photo photo) throws FileNotFoundException,
			IllegalArgumentException {
		DateFormat formatter;
		formatter = new SimpleDateFormat("dd.MM.yy");

		String s = formatter.format(getModifiedDate(photo));

		return s;
	}

	/**
	 * Function that returns the Component
	 * @return
	 */
	private Component getComponent() {
		return this;
	}

	/**
	 * 
	 */
	@Override
	public void setPhoto(Photo photo) {

		try {
//			img = photo.getImage();
			lblCreated_filled.setText(formatDate(photo));
			lblName_filled.setText(photo.getName());
			lblSize_filled.setText(String.valueOf(getFileSize(photo)));
			lblPixel_filled.setText(getPixel(photo));
			lblComment_filled.setText(photo.getComment());
			
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(getComponent(), e.getMessage(),
					"Error", e.getStackTrace().toString());
			e.printStackTrace();
		}

	}
}