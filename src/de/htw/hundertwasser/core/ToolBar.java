/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */

package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
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

import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

public class ToolBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_PRINTING ="An error occured while trying to print";

	private Photo photo;
	/*
	 * 
	 */
	public ToolBar() {
		setBackground(Color.WHITE);
		setToolTipText("Tools");
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
		
		JButton btnRename = new JButton("    Rename  ");
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
		
		
		JButton btnCut = new JButton("Cut               ");
		btnCut.setBackground(Color.WHITE);
		btnCut.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCut.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_cut_clean.png")));
		btnCut.setToolTipText("Cut");
		add(btnCut, "3, 10");
		
		JButton btnDelete = new JButton("    Delete");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/delete.png")));
		btnDelete.setToolTipText("Delete");
		add(btnDelete, "3, 12");
		
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
		
		JButton btnBlackwhite = new JButton("  Black/White");
		btnBlackwhite.setBackground(Color.WHITE);
		btnBlackwhite.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_blackwhite_clean.png")));
		btnBlackwhite.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBlackwhite.setToolTipText("Black/White");
		add(btnBlackwhite, "3, 18");
	}
	
	
	
	
	ActionListener renameListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String newName = 
					JOptionPane.showInputDialog(null, "New name:", "rename", JOptionPane.QUESTION_MESSAGE);
			if(newName == null){
				return; // cancel pressed
			}
				
			photo.setName(newName);
		}
	};

	
	ActionListener printListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			String newName = 
//					JOptionPane.showInputDialog(null, "New name:", "rename", JOptionPane.QUESTION_MESSAGE);
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
	
	
	
	
	private void print() throws PrinterException, FileNotFoundException, IOException, InsufficientPrivilegesException{
		
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		PrintJob printOrder = tk.getPrintJob( new Frame(), "print", null );
	
	
		PrinterJob pjob = PrinterJob.getPrinterJob();
		
		if (pjob != null){
		
//		 String jobName = "DBPM Printjob: " + photo.getName();
			
//		    pjob.setJobName(jobName);		
		if ( pjob.printDialog() == false )
			return;
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
}
