package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

import de.htw.hundertwasser.res.RessourcenEnummeration;

public class NavBarPhotoAlbumRenderer extends JPanel implements TreeCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblText;
	private ImageViewer iv;
	
	
	public NavBarPhotoAlbumRenderer() throws IOException
	{
		lblText=new JLabel("",RessourcenEnummeration.PHOTOALBUM.getIcon(),JLabel.LEADING);
		ImageViewer iv = new ImageViewer();
		setLayout(new BorderLayout(10,10));
		add(lblText,BorderLayout.CENTER);
		add(iv,BorderLayout.EAST);
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if (value instanceof PhotoAlbum)
		{
			PhotoAlbum photoalbum = (PhotoAlbum)value;
			lblText.setText(photoalbum.getName());
		}
		if (value instanceof String)
		{
			lblText.setText((String) value);
		}
		return this;
	}

}
