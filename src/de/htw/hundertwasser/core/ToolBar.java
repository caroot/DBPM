/*
 * @author: Dominic Holz
 * @version: 0.1
 * @date: 12.09.12
 */

package de.htw.hundertwasser.core;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class ToolBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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
		btnRename.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRename.setToolTipText("Rename");
		btnRename.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/rename.png")));
		add(btnRename, "3, 6");
		
				
		JButton btnZoom = new JButton(" Zoom           ");
		btnZoom.setBackground(Color.WHITE);
		btnZoom.setFont(new Font("Arial", Font.PLAIN, 12));
		btnZoom.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_zoom_clean.png")));
		btnZoom.setToolTipText("Zoom");
		add(btnZoom, "3, 8");
		
		
		JButton btnCut = new JButton("Cut               ");
		btnCut.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCut.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_cut_clean.png")));
		btnCut.setToolTipText("Cut");
		add(btnCut, "3, 10");
		
		JButton btnDelete = new JButton("    Delete");
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/delete.png")));
		btnDelete.setToolTipText("Delete");
		add(btnDelete, "3, 12");
		
		JButton btnPrint = new JButton("Print            ");
		btnPrint.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_print_clean.png")));
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPrint.setToolTipText("Print");
		add(btnPrint, "3, 14");
		
		JButton btnFullscreen = new JButton("Fullscreen");
		btnFullscreen.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_fullscreen_clean.png")));
		btnFullscreen.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFullscreen.setToolTipText("Fullscreen");
		add(btnFullscreen, "3, 16");
		
		JButton btnBlackwhite = new JButton("  Black/White");
		btnBlackwhite.setIcon(new ImageIcon(ToolBar.class.getResource("/de/htw/hundertwasser/res/tool_blackwhite_clean.png")));
		btnBlackwhite.setFont(new Font("Arial", Font.PLAIN, 12));
		btnBlackwhite.setToolTipText("Black/White");
		add(btnBlackwhite, "3, 18");
	}

}
