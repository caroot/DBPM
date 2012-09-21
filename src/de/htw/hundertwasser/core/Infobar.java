/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 20.09.12
 */

package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
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

import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

/*
 * Class that creates the Infobar
 */
public class Infobar extends JPanel {

	// Constants
	private static final long serialVersionUID = 1L;
	// Variables
	private Photo photo;
	// private String absolutePath = "C:/Temp/universe.jpg";
	private String absolutePath = "AGV.jpg";

	/*
	 * Constructor
	 */
	public Infobar() {

		photo = new Photo("12345678", absolutePath);
		photo.setComment(photo.getName());

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

		JLabel label_1 = new JLabel("             -------------------------");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, "2, 3, 11, 2, center, default");

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblSize, "2, 6");

		JLabel lblSize_filled = new JLabel(new Long(getFileSize()).toString());
		lblSize_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSize_filled, "4, 6, 5, 1, center, default");

		JLabel lblKb = new JLabel("KB");
		add(lblKb, "10, 6");

		JLabel lblPixel = new JLabel("Pixel:");
		lblPixel.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblPixel, "2, 8");

		JLabel lblPixel_filled = new JLabel(getPixel());
		lblPixel_filled.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPixel_filled, "6, 8, 6, 1, center, default");

		JLabel lblCreated = new JLabel("Created:");
		lblCreated.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblCreated, "2, 10");

		JLabel lblCreated_filled = new JLabel(formatDate());
		add(lblCreated_filled, "6, 10, 5, 1, center, default");

		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Arial", Font.BOLD, 12));
		add(lblComment, "2, 12");

		JLabel lblComment_filled = new JLabel(photo.getComment());
		lblComment_filled.setFont(new Font("Arial", Font.PLAIN, 12));
		lblComment_filled.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblComment_filled, "6, 12, 5, 1, center, default");

		JLabel label = new JLabel("");
		add(label, "2, 14, 10, 3");
	}

	/*
	 * Function that returns the Photo height
	 * 
	 * @return Height of the photo
	 */
	private int getPhotoHeight() {
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

		// System.out.println(height);
		return height;
	}

	/*
	 * Function that returns the Photo width
	 * 
	 * @return Width of the photo
	 */
	private int getPhotoWidth() {
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
		// System.out.println(width);
		return width;
	}

	/*
	 * Function that returns the Photo display size
	 *  @return display size of the photo
	 */
	private String getPixel() {

		StringBuffer sb = new StringBuffer();
		sb.append(new Integer(getPhotoHeight()).toString()).append(" x ")
				.append(new Integer(getPhotoWidth()).toString());

		return sb.toString();
	}

	/*
	 * Function that returns the Photo size
	 *  @return Size of the photo
	 */
	private long getFileSize() {

		File file = new File(photo.getPathToFile());

		return (file.length() / 1000);
	}

	/*
	 * Function that returns the Photo date
	 *  @return Date of the photo
	 */
	private Date getModifiedDate() {

		File file = new File(photo.getPathToFile());
		long tmp = file.lastModified();

		Date modified = new Date(tmp);

		return modified;
	}

	/*
	 * Function that formats the Date
	 * @return Date 
	 */
	private String formatDate() {
		DateFormat formatter;
		formatter = new SimpleDateFormat("dd.MM.yy");

		String s = formatter.format(getModifiedDate());

		return s;
	}
}