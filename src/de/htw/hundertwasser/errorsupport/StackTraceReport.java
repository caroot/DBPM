package de.htw.hundertwasser.errorsupport;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DateFormatter;

import de.htw.hundertwasser.backend.FolderManager;
import de.htw.hundertwasser.core.ImageViewer;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * This class is a StackTraceReport to report bugs of users to the Developer
 * 
 * @author daniel rhein
 * 
 */
public class StackTraceReport extends JDialog {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String ERROR_EMPTY_STACKTRACE = "Stacktrace can't be empty.";
	private static final String ERROR_NO_STACK_TRACE = "Stacktrace can't be null";
	private static final String PREAMBLE = "<html><body>Dear User <br> We don't like to waste your time with long text-messages,<br> but you would give us a hand to solve this error,<br> if you send us your Error-Log.<br> If you like to support us, you can send us this Error-Log via E-Mail.<br><br> First you can export the Information in the Error-Log-Message with the buttons 'copy to clipboard' or 'save to file'.<br> Finally you can send the message to us via E-Mail.</body></html>";
	// Variables
	private JLabel lblinformation;
	private JLabel lblstacktrace;
	private JTextArea txtStackTrace;
	private JScrollPane jsp;
	private JButton btnOk;
	private JButton btnCopyToClipBoard;
	private JButton btnSaveToFile;
	private DateFormatter dateFormatter = null;
	private FolderManager foldermanager = null;
	private ImageViewer imageviewer = null;
	private static int MWIDTH = 510;
	private static int MHEIGHT = 353;

	/*
	 * 
	 */
	public StackTraceReport(JFrame owner, boolean modal) {
		super(owner, modal);
		setResizable(false);
		setTitle("Report a bug");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		DateFormat dateformat = new SimpleDateFormat("ddmmyyHHMMSS");
		dateFormatter = new DateFormatter(dateformat);
		foldermanager = new FolderManager();
		imageviewer = new ImageViewer();
		try {
			lblinformation = new JLabel(PREAMBLE);
			lblstacktrace = new JLabel("Error-Log-Message:");
			txtStackTrace = new JTextArea();
			jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jsp.setViewportView(txtStackTrace);
			btnOk = new JButton("Close dialog");
			btnCopyToClipBoard = new JButton("Copy to clipboard",
					RessourcenEnummeration.CLIPBOARD_ICON.getIcon());
			btnSaveToFile = new JButton("Save to file",
					RessourcenEnummeration.SAVE_ICON.getIcon());
			imageviewer
					.setImage(RessourcenEnummeration.SUPPORT_ICON.getImage());
			setIconImage(RessourcenEnummeration.SUPPORT_ICON.getImage());
			setLayout();
			setConnectors();
			setDimension();
		} catch (IOException ex) {
			ErrorMessageDialog.showMessage(this, ex.getLocalizedMessage());
			ex.printStackTrace();
		}
	}

	
	public void showStackTraceReport(String stacktrace)
			throws IllegalArgumentException {
		if (stacktrace == null)
			throw new IllegalArgumentException(ERROR_NO_STACK_TRACE);
		if (stacktrace.isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_STACKTRACE);
		txtStackTrace.setText(stacktrace);
		this.setVisible(true);
	}

	private JDialog getJDialog() {
		return this;
	}

	private String getMessage() {
		return txtStackTrace.getText();
	}

	private void setLayout() {
		add(imageviewer);
		add(lblinformation);
		add(lblstacktrace);
		add(jsp);
		add(btnOk);
		add(btnCopyToClipBoard);
		add(btnSaveToFile);
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.NORTH, imageviewer, 10,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, imageviewer, 10,
				SpringLayout.WEST, getContentPane());
		// layout.putConstraint(SpringLayout.SOUTH, imageviewer, 10,
		// SpringLayout.NORTH, btnOk);
		layout.putConstraint(SpringLayout.WEST, lblinformation, 10,
				SpringLayout.EAST, imageviewer);
		layout.putConstraint(SpringLayout.NORTH, lblinformation, 10,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, lblstacktrace, 10,
				SpringLayout.SOUTH, lblinformation);
		layout.putConstraint(SpringLayout.WEST, lblstacktrace, 10,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, jsp, 10, SpringLayout.SOUTH,
				lblstacktrace);
		layout.putConstraint(SpringLayout.WEST, jsp, 20, SpringLayout.WEST,
				getContentPane());

		layout.putConstraint(SpringLayout.WEST, btnOk, 10, SpringLayout.WEST,
				getContentPane());
		layout.putConstraint(SpringLayout.NORTH, btnOk, 60, SpringLayout.SOUTH,
				lblstacktrace);
		layout.putConstraint(SpringLayout.WEST, btnCopyToClipBoard, 10,
				SpringLayout.EAST, btnOk);
		layout.putConstraint(SpringLayout.NORTH, btnCopyToClipBoard, 60,
				SpringLayout.SOUTH, lblstacktrace);
		layout.putConstraint(SpringLayout.WEST, btnSaveToFile, 10,
				SpringLayout.EAST, btnCopyToClipBoard);
		layout.putConstraint(SpringLayout.NORTH, btnSaveToFile, 60,
				SpringLayout.SOUTH, lblstacktrace);
		layout.putConstraint(SpringLayout.SOUTH, btnOk, 0, SpringLayout.SOUTH,
				btnSaveToFile);
		layout.putConstraint(SpringLayout.EAST, lblinformation, 0,
				SpringLayout.EAST, jsp);
		layout.putConstraint(SpringLayout.SOUTH, jsp, -10, SpringLayout.NORTH,
				btnOk);
		layout.putConstraint(SpringLayout.EAST, jsp, -10, SpringLayout.EAST,
				btnSaveToFile);
		// layout.putConstraint(SpringLayout.SOUTH,jsp,10,SpringLayout.NORTH,btnOk);

		setLayout(layout);
	}

	private void setDimension() {
		setMinimumSize(new Dimension(MWIDTH, MHEIGHT));
	}

	private void setConnectors() {
		btnCopyToClipBoard.addActionListener(getCopyToClipBoardActionListner());
		btnOk.addActionListener(getOKButtonActionListener());
		btnSaveToFile.addActionListener(getSaveToFileActionListner());
	}

	private ActionListener getOKButtonActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getJDialog().dispose();
			}
		};
	}

	private ActionListener getCopyToClipBoardActionListner() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Clipboard clipboard = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				StringTransferable transfer = new StringTransferable(
						getMessage());
				clipboard.setContents(transfer, null);
			}
		};
	}

	private ActionListener getSaveToFileActionListner() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder absolutepath = new StringBuilder();
				absolutepath.append(foldermanager.getUsersHomePath()).append(
						File.separatorChar);
				try {
					absolutepath.append(dateFormatter.valueToString(new Date(
							System.currentTimeMillis())));
					absolutepath.append(".log");
					File file = new File(absolutepath.toString());
					FileWriter filewriter = new FileWriter(file);
					BufferedWriter bufferedwriter = new BufferedWriter(
							filewriter);
					bufferedwriter.write(getMessage());
					bufferedwriter.close();
					filewriter.close();
					JOptionPane.showMessageDialog(getJDialog(),
							"File " + file.getAbsolutePath()
									+ " has been saved.");

				} catch (ParseException e1) {
					ErrorMessageDialog.showMessage(getJDialog(),
							e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e2) {
					ErrorMessageDialog.showMessage(getJDialog(),
							e2.getMessage());
					e2.printStackTrace();
				}

			}
		};
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException classnotfoundexceptoin) {
			ErrorMessageDialog.showMessage(null,
					classnotfoundexceptoin.getMessage());
			classnotfoundexceptoin.printStackTrace();
		} catch (InstantiationException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage());
			e.printStackTrace();
		}
		StackTraceReport str = new StackTraceReport(null, true);
		str.showStackTraceReport("Error");
	}

}
