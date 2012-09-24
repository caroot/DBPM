package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 * @author Dominic Holz (template by Daniel Rhein)
 * 
 */
public class NavBarPhotoBoxRenderer extends JPanel implements TreeCellRenderer {

	private static final long serialVersionUID = 1L;
	private JLabel lblText;

	// private ImageViewer iv;

	public NavBarPhotoBoxRenderer() throws IOException {
		// lblText=new
		// JLabel("",RessourcenEnummeration.PHOTOBOX.getIcon(),JLabel.LEADING);
		lblText = new JLabel("");
		// iv = new ImageViewer();
		setLayout(new BorderLayout(10, 10));
		add(lblText, BorderLayout.CENTER);
		// add(iv,BorderLayout.EAST);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (value instanceof PhotoAlbum) {
			PhotoBox photobox = (PhotoBox) value;
			lblText.setText(photobox.getName());
		}
		if (value instanceof String) {
			lblText.setText((String) value);
		}
		return this;
	}

}
