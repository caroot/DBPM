package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.htw.hundertwasser.core.interfaces.ThumbNailBarObserver;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.view.EditScreen;
import de.htw.hundertwasser.view.PhotoAlbumFullScreen;
import de.htw.hundertwasser.view.PhotoBoxFullScreen;
import de.htw.test.MouseListener;

/**
 * Class that describes the Toolbar
 * 
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */
public class ToolBar extends JPanel implements ThumbNailBarObserver {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String ERROR_PRINTING = "An error occured while trying to print";
	private static final String CONFIRM = "Are you sure?";
	private static final String TODO = "Needs to be implemented";
	private static final String ERROR = "An Error occured";
	private static final String NO_PICTURE = "No picture given";
	private static final String NEW_NAME = "Enter new name: ";
	private static final String NAME_SUCCESS = "Name successfully changed";
	private static final String SUCCESS = "Success";
	private static final String ERROR_RENAME = "An Error occured while renaming";

	// Variables
	// private String name = "TestBild";
	private String absolutePath = "AGV.jpg";
	private EditScreen editScreen = null;

	private boolean inPhotoBox;

	private Photo photo;

	// private MouseListener msl;

	/**
	 * Constructor
	 * 
	 * @param editScreen
	 */
	public ToolBar(EditScreen editScreen) {
		this.setEditScreen(editScreen);

		photo = new Photo("TestBild", absolutePath);
		setBackground(Color.WHITE);
		// setUndecorated(true);
		setVisible(true);
		setToolTipText("Tools");

		// setPreferredSize(new Dimension(200, 400));
		// setMinimumSize(new Dimension(200, 400));

		FormLayout formLayout = new FormLayout(
				new ColumnSpec[] { FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.GROWING_BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(15dlu;default)"),
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
						FormFactory.DEFAULT_ROWSPEC, });
		formLayout.setHonorsVisibility(false);

		setLayout(formLayout);

		JLabel lblTools = new JLabel("Tools");
		lblTools.setBackground(Color.WHITE);
		lblTools.setFont(new Font("Arial", Font.BOLD, 14));
		lblTools.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTools, "3, 2");

		JLabel label = new JLabel("-------------------------");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, "3, 3, 1, 2");

		JButton btnRename = new JButton(" Rename       ");
		btnRename.setBackground(Color.WHITE);
		btnRename.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRename.setToolTipText("Rename");
		btnRename.setIcon(new ImageIcon(ToolBar.class
				.getResource("/de/htw/hundertwasser/res/rename.png")));
		add(btnRename, "3, 6, fill, center");
		btnRename.addActionListener(getRenameListener());
		// btnRename.setSize(10, 20);

		JButton btnZoom = new JButton(" Zoom           ");
		btnZoom.setBackground(Color.WHITE);
		btnZoom.setFont(new Font("Arial", Font.PLAIN, 12));
		btnZoom.setIcon(new ImageIcon(ToolBar.class
				.getResource("/de/htw/hundertwasser/res/tool_zoom_clean.png")));
		btnZoom.setToolTipText("Zoom");
		add(btnZoom, "3, 8, fill, center");
		btnZoom.addActionListener(getZoomListner());
		// btnZoom.setSize(10, 20);

		JButton btnCut = new JButton("Cut               ");
		btnCut.setBackground(Color.WHITE);
		btnCut.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCut.setIcon(new ImageIcon(ToolBar.class
				.getResource("/de/htw/hundertwasser/res/tool_cut_clean.png")));
		btnCut.setToolTipText("Cut");
		add(btnCut, "3, 10, fill, center");
		btnCut.addActionListener(getCutListener());
		// btnCut.setSize(10, 20);

		JButton btnDelete = new JButton("Delete          ");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setIcon(new ImageIcon(ToolBar.class
				.getResource("/de/htw/hundertwasser/res/delete.png")));
		btnDelete.setToolTipText("Delete");
		add(btnDelete, "3, 12, fill, center");
		btnDelete.addActionListener(getDeleteListener());
		// btnDelete.setSize(10, 20);

		JButton btnPrint = new JButton("Print            ");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setIcon(new ImageIcon(ToolBar.class
				.getResource("/de/htw/hundertwasser/res/tool_print_clean.png")));
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPrint.setToolTipText("Print");
		add(btnPrint, "3, 14, fill, center");
		btnPrint.addActionListener(getPrintListener());
		// btnPrint.setSize(10, 20);

		JButton btnFullscreen = new JButton("Fullscreen");
		btnFullscreen.setBackground(Color.WHITE);
		btnFullscreen
				.setIcon(new ImageIcon(
						ToolBar.class
								.getResource("/de/htw/hundertwasser/res/tool_fullscreen_clean.png")));
		btnFullscreen.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFullscreen.setToolTipText("Fullscreen");
		add(btnFullscreen, "3, 16, fill, center");
		btnFullscreen.addActionListener(getFullScreenListener());
		// btnFullscreen.setSize(10, 20);

		JButton btnBlackwhite = new JButton("  Black/White");
		btnBlackwhite.setBackground(Color.WHITE);
		btnBlackwhite
				.setIcon(new ImageIcon(
						ToolBar.class
								.getResource("/de/htw/hundertwasser/res/tool_blackwhite_clean.png")));
		btnBlackwhite.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBlackwhite.setToolTipText("Black/White");
		add(btnBlackwhite, "3, 18, fill, center");
		btnBlackwhite.addActionListener(getBlackAndWhiteListener());
		// btnBlackwhite.setSize(10, 20);

	}

	public ActionListener getRenameListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String newName = JOptionPane.showInputDialog(null,
							NEW_NAME, "rename", JOptionPane.QUESTION_MESSAGE);

					if (newName != null) {
						if (photo != null) {

							// System.out.println(photo.getName());
							photo.setName(newName);

							JOptionPane.showMessageDialog(null, NAME_SUCCESS,
									SUCCESS, JOptionPane.INFORMATION_MESSAGE);
							// System.out.println(photo.getName());
						} else {
							JOptionPane.showMessageDialog(null, NO_PICTURE,
									ERROR, JOptionPane.OK_OPTION);
						}
					}

				} catch (IllegalArgumentException illegalArg) {

					ErrorMessageDialog.showMessage(null, ERROR_RENAME, ERROR,
							illegalArg.getStackTrace());

				}

			}

		};
	}

	private ActionListener getZoomListner() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, TODO, "Zoom",
						JOptionPane.OK_OPTION);

				// TODO: Zoom Implementierung
			}
		};
	}

	/**
	 * 
	 * @return
	 */
	private ActionListener getCutListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, TODO, "Cut",
						JOptionPane.OK_OPTION);

				// msl = new MouseListener();

			}

		};
	}

	/**
	 * 
	 * @return
	 */
	private ActionListener getDeleteListener() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, CONFIRM,
						"Confirm Delete", JOptionPane.YES_NO_OPTION);

				// if (result == JOptionPane.YES_OPTION) {
				// ElementStorage.NO_LIST.charAt(2);
				// }
			}

		};
	}

	/**
	 * 
	 * @return
	 */
	private ActionListener getPrintListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					print();
				} catch (PrinterException printexc) {

					ErrorMessageDialog.showMessage(null, ERROR_PRINTING, ERROR,
							printexc.getStackTrace());

				} catch (FileNotFoundException fileexc) {
					ErrorMessageDialog.showMessage(null, ERROR_PRINTING, ERROR,
							fileexc.getStackTrace());
				} catch (IOException ioexc) {
					ErrorMessageDialog.showMessage(null, ERROR_PRINTING, ERROR,
							ioexc.getStackTrace());

				} catch (InsufficientPrivilegesException privexc) {
					ErrorMessageDialog.showMessage(null, ERROR_PRINTING, ERROR,
							privexc.getStackTrace());

				} catch (NullPointerException nullpt) {
					ErrorMessageDialog.showMessage(null, ERROR_PRINTING, ERROR,
							nullpt.getStackTrace());

				}
			}
		};

	}

	/**
	 * 
	 * @return
	 */
	private Component getComponent() {
		return this;
	}

	private ActionListener getFullScreenListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (inPhotoBox == true) {
					PhotoBoxFullScreen pbfs = new PhotoBoxFullScreen(
							(BufferedImage) editScreen.getImgViewer()
									.getImage());
					pbfs.repaint();
				} else {
					PhotoAlbumFullScreen pafs;
					pafs = new PhotoAlbumFullScreen((BufferedImage) editScreen
							.getImgViewer().getImage());
					pafs.repaint();
				}

			}

		};

	}

	/**
	 * 
	 * @return
	 */
	private ActionListener getBlackAndWhiteListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, TODO, "Black/White",
						JOptionPane.OK_OPTION);
			}

		};
	}

	/**
	 * 
	 * @throws PrinterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InsufficientPrivilegesException
	 */
	private void print() throws PrinterException, FileNotFoundException,
			IOException, InsufficientPrivilegesException {

		// Toolkit tk = Toolkit.getDefaultToolkit();
		// PrintJob printOrder = tk.getPrintJob( new Frame(), "print", null );

		PrinterJob pjob = PrinterJob.getPrinterJob();

		if (pjob != null) {

			// String jobName = "DBPM Printjob: " + photo.getName();

			// pjob.setJobName(jobName);
			if (pjob.printDialog() == false) {
				return;
			} else {
					JOptionPane.showMessageDialog(null, TODO, "Black/White",
							JOptionPane.OK_OPTION);
				}
			}
			// pjob.setPrintable(new Printable() {
			//
			// @Override
			// public int print(Graphics graphics, PageFormat pageFormat,
			// int pageIndex) throws PrinterException {
			// // TODO Auto-generated method stub
			// return 0;
			// }
			// });
			//
			// pjob.print();
	}

	@Override
	public void setPhoto(Photo photo) {
		// TODO Auto-generated method stub
		this.photo = photo;
	}

	public EditScreen getEditScreen() {
		return editScreen;
	}

	public void setEditScreen(EditScreen editScreen) {
		this.editScreen = editScreen;
	}

}