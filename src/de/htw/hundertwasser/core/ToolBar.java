/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */

package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;
import de.htw.hundertwasser.view.PhotoAlbumFullScreen;
import de.htw.hundertwasser.view.PhotoBoxFullScreen;

public class ToolBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_PRINTING ="An error occured while trying to print";
	private static final String CONFIRM = "Are you sure?";
	private static final String ZOOM_VALUE = "Needs to be implemented";
	private static final String ERROR = "An Error occured";
	private static final String NO_PICTURE ="No picture given";
	private static final String NEW_NAME ="Enter new name: ";
	
//	private String name = "TestBild";
	private String absolutePath = "C:/Users/Dominic/Pictures/pics/bild.jpg";
	
	private boolean inPhotoBox;

	private Photo photo;
	/*
	 * 
	 */
	public ToolBar() {
		
		photo=new Photo("TestBild", absolutePath);
		
		setBackground(Color.WHITE);
//		setUndecorated(true);
		setVisible(true);
		setToolTipText("Tools");
		setPreferredSize(new Dimension(250, 450));
		setMinimumSize(new Dimension(250, 450));
		FormLayout formLayout = new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(101dlu;default):grow"),
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
				FormFactory.DEFAULT_ROWSPEC,});
		formLayout.setHonorsVisibility(false);
		
		setLayout(formLayout);
		
		JLabel lblTools = new JLabel("Tools");
		lblTools.setBackground(Color.WHITE);
		lblTools.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTools.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTools, "3, 2, 1, 2");
		
		JLabel label = new JLabel("---------------");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, "3, 4");
		
		JButton btnRename = new JButton(" Rename       ");
		btnRename.setBackground(Color.WHITE);
		btnRename.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRename.setToolTipText("Rename");
		btnRename.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/rename.png")));
		add(btnRename, "3, 6");
		btnRename.addActionListener(renameListener);
		
				
		JButton btnZoom = new JButton(" Zoom           ");
		btnZoom.setBackground(Color.WHITE);
		btnZoom.setFont(new Font("Arial", Font.PLAIN, 12));
		btnZoom.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_zoom_clean.png")));
		btnZoom.setToolTipText("Zoom");
		add(btnZoom, "3, 8");
		btnZoom.addActionListener(ZoomListener);
		
		JButton btnCut = new JButton("Cut               ");
		btnCut.setBackground(Color.WHITE);
		btnCut.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCut.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_cut_clean.png")));
		btnCut.setToolTipText("Cut");
		add(btnCut, "3, 10");
		btnCut.addActionListener(CutListener);
		
		
		JButton btnDelete = new JButton("Delete          ");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/delete.png")));
		btnDelete.setToolTipText("Delete");
		add(btnDelete, "3, 12");
		btnDelete.addActionListener(DeleteListener);
		
		
		
		JButton btnPrint = new JButton("Print            ");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_print_clean.png")));
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPrint.setToolTipText("Print");
		add(btnPrint, "3, 14");
		btnPrint.addActionListener(printListener);
		
		
		
		JButton btnFullscreen = new JButton("Fullscreen");
		btnFullscreen.setBackground(Color.WHITE);
		btnFullscreen.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_fullscreen_clean.png")));
		btnFullscreen.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFullscreen.setToolTipText("Fullscreen");
		add(btnFullscreen, "3, 16");
		btnFullscreen.addActionListener(FullScreenListener);
		
		
		JButton btnBlackwhite = new JButton("  Black/White");
		btnBlackwhite.setBackground(Color.WHITE);
		btnBlackwhite.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_blackwhite_clean.png")));
		btnBlackwhite.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBlackwhite.setToolTipText("Black/White");
		add(btnBlackwhite, "3, 18");
		btnBlackwhite.addActionListener(BlackWhiteListener);
	
	}
		
	
	ActionListener renameListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try{
			String newName = 
					JOptionPane.showInputDialog(null, NEW_NAME, "rename", JOptionPane.QUESTION_MESSAGE);
			
			if (photo!=null){	
				
				System.out.println(photo.getName());
				photo.setName(newName);
				
				JOptionPane.showMessageDialog(null, "Name successfully changed", "Success", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(photo.getName());
			}
			else {
							JOptionPane.showMessageDialog(	null,
							NO_PICTURE,
							ERROR,
							JOptionPane.OK_OPTION );
			}
			
		}	catch (IllegalArgumentException illegalArg){
								
				JOptionPane.showMessageDialog(null, illegalArg.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
				
			}
		}
	};

	
	ActionListener ZoomListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(	null,
					ZOOM_VALUE,
					"Zoom",
					JOptionPane.OK_OPTION );
			
//			TODO: Zoom Implementierung
		}
	};
	
	
	ActionListener CutListener = new ActionListener() {
		
		
		public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(	null,
						ZOOM_VALUE,
						"Zoom",
						JOptionPane.OK_OPTION );
				
		}
	};
		
	
	
	ActionListener DeleteListener = new ActionListener() {
				
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showConfirmDialog(	null,
											CONFIRM,
											"Confirm Delete",
											JOptionPane.YES_NO_OPTION );
		}
	};
		
	ActionListener printListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			try {
				print();
			} catch (PrinterException printexc) {
				
				JOptionPane.showMessageDialog(	null,
						printexc.getMessage(),
						ERROR_PRINTING,
						JOptionPane.ERROR_MESSAGE );
					printexc.printStackTrace(); // For debug purpose
			} catch (FileNotFoundException fileexc) {
				JOptionPane.showMessageDialog(	null,
						fileexc.getMessage(),
						ERROR_PRINTING,
						JOptionPane.ERROR_MESSAGE );
				fileexc.printStackTrace();
			} catch (IOException ioexc) {
				JOptionPane.showMessageDialog(	null,
						ioexc.getMessage(),
						ERROR_PRINTING,
						JOptionPane.ERROR_MESSAGE );
				ioexc.printStackTrace();
			} catch (InsufficientPrivilegesException privexc) {
				JOptionPane.showMessageDialog(	null,
						privexc.getMessage(),
						ERROR_PRINTING,
						JOptionPane.ERROR_MESSAGE );
				privexc.printStackTrace();
	
			} catch (NullPointerException nullpt) {
				JOptionPane.showMessageDialog(	null,
						nullpt.getMessage(),
						ERROR_PRINTING,
						JOptionPane.ERROR_MESSAGE );
				nullpt.printStackTrace();
			}
		}
	};
	
	
	ActionListener FullScreenListener = new ActionListener() {
		
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (inPhotoBox == true){
				PhotoBoxFullScreen pbfs = new PhotoBoxFullScreen();
				pbfs.main(null);
		}
			else {
				PhotoAlbumFullScreen pafs = new PhotoAlbumFullScreen();
				pafs.main(null);
			}
		}
	};
	
	
	ActionListener BlackWhiteListener = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(	null,
						ZOOM_VALUE,
						"Zoom",
						JOptionPane.OK_OPTION );
			}
	};
	
	
	
	private void print() throws PrinterException, FileNotFoundException, IOException, InsufficientPrivilegesException{
		
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		PrintJob printOrder = tk.getPrintJob( new Frame(), "print", null );
	
	
		PrinterJob pjob = PrinterJob.getPrinterJob();
		
		if (pjob != null){
		
//		 String jobName = "DBPM Printjob: " + photo.getName();
			
//		    pjob.setJobName(jobName);		
		if ( pjob.printDialog() == false )
			{return;}
	    	pjob.setPrintable( new Printable() {
			
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
					throws PrinterException {
				// TODO Auto-generated method stub
				return 0;
			}
		} 
	    );
	   
	    pjob.print();
		} 
	}


//  main method
//	
//	public static void main(String[] args) {
//	ToolBar tb = new ToolBar();
//	tb.setVisible(true);
//	}
}