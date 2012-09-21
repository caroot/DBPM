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

	private JLabel lblText;
	private ImageViewer iv;
	
	
	public NavBarPhotoAlbumRenderer() throws IOException
	{
		lblText=new JLabel("",RessourcenEnummeration.PHOTOALBUM.getIcon(),JLabel.WEST);
		ImageViewer iv = new ImageViewer();
		setLayout(new BorderLayout(10,10));
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		return null;
	}

}
