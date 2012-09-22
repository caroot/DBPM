package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.OperationNotSupportedException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import de.htw.hundertwasser.model.NavBarPhotoAlbumModel;
import de.htw.hundertwasser.model.NavBarPhotoBoxModel;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * Class that creates the NavigationBar
 * 
 * @author Johannes Schramm
 * @author Dominic Holz
 * 
 * @since 22.09.2012
 * 
 */

public class NavigationBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavBarPhotoAlbumModel modelPhotoAlbum;
	private NavBarPhotoAlbumRenderer rendererPhotoAlbum;
	
	private NavBarPhotoBoxModel modelPhotoBox;
	private NavBarPhotoBoxRenderer rendererPhotoBox;
	private JTree jtreePhotoAlbum;

	private JScrollPane scrlPhotoAlben;
	private JScrollPane scrlPhotoBoxes;
	private JTree jtreePhotoBox;
	private Font font;

	/**
	 * Constructor
	 */
	public NavigationBar() throws IOException {

		jtreePhotoAlbum = new JTree();
		jtreePhotoBox = new JTree();
		scrlPhotoAlben = new JScrollPane();
		scrlPhotoBoxes = new JScrollPane();

		modelPhotoAlbum = new NavBarPhotoAlbumModel();
		rendererPhotoAlbum = new NavBarPhotoAlbumRenderer();
		modelPhotoBox = new NavBarPhotoBoxModel();
		rendererPhotoBox = new NavBarPhotoBoxRenderer();
		
		jtreePhotoAlbum.setModel(modelPhotoAlbum);
		jtreePhotoAlbum.setCellRenderer(rendererPhotoAlbum);
		modelPhotoAlbum.addTreeModelListener(getPhotoAlbumListener());

		jtreePhotoBox.setModel(modelPhotoBox);
		jtreePhotoBox.setCellRenderer(rendererPhotoBox);
		modelPhotoBox.addTreeModelListener(getPhotoBoxListener());
		
		
		fillPhotoAlbumTest();

		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(150, 803));

		JPanel pnlBackground = new JPanel();
		pnlBackground.setBackground(Color.WHITE);
		pnlBackground.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		pnlBackground.setBounds(6, 6, 141, 575);
		add(pnlBackground);
		pnlBackground.setLayout(null);

		JPanel pnlPhotoBox = new JPanel();
		pnlPhotoBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		pnlPhotoBox.setBounds(6, 6, 129, 231);
		pnlBackground.add(pnlPhotoBox);
		pnlPhotoBox.setLayout(new BorderLayout(0, 0));

		pnlPhotoBox.add(scrlPhotoBoxes, BorderLayout.CENTER);
		scrlPhotoBoxes.setViewportView(jtreePhotoBox);

		// JLabel lblYourPhotoBoxes = new JLabel("     Your photo boxes");
		// lblYourPhotoBoxes.setVerticalAlignment(SwingConstants.TOP);
		// lblYourPhotoBoxes.setFont(new Font("Calibri", Font.PLAIN, 13));
		// pnlPhotoBox.add(lblYourPhotoBoxes); // lblYourPhotoAlbums

		// JLabel lblYourPhotoAlbums = new JLabel("     Your albums");
		// pnlPhotoAlbum.add(jtreePhotoAlbum, BorderLayout.CENTER);
		// lblYourPhotoAlbums.setVerticalAlignment(SwingConstants.TOP);
		// lblYourPhotoAlbums.setFont(new Font("Calibri", Font.PLAIN, 13));

		JPanel pnlPhotoAlbum = new JPanel();
		pnlPhotoAlbum.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		pnlPhotoAlbum.setBounds(6, 285, 129, 246);
		pnlBackground.add(pnlPhotoAlbum);
		pnlPhotoAlbum.setLayout(new BorderLayout(0, 0));
		pnlPhotoAlbum.add(scrlPhotoAlben, BorderLayout.CENTER);
		scrlPhotoAlben.setViewportView(jtreePhotoAlbum);

		JButton btnNewBox = new JButton("+new photobox");
		btnNewBox.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewBox.addActionListener(btnBoxListener);
		btnNewBox.setBounds(6, 249, 129, 24);
		pnlBackground.add(btnNewBox);

		JButton btnNewAlbum = new JButton("+new photoalbum");
		btnNewAlbum.addActionListener(btnAlbumListener);
		btnNewAlbum.setBounds(6, 540, 129, 24);
		pnlBackground.add(btnNewAlbum);
		btnNewAlbum.setFont(new Font("Calibri", Font.PLAIN, 11));

		// Font

		try {
			font = RessourcenEnummeration.FONT_CALIBRI.getFont()
					.deriveFont(11f);
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* 
 	*/
	private void fillPhotoAlbumTest() {
		for (int i = 0; i < 3; i++) {
			PhotoAlbum album = new PhotoAlbum("Mein " + i + ".tes PhotoAlbum");
			modelPhotoAlbum.addPhotoAlbum(album);
		}
	}

	/**
	 * 
	 * 
	 */
	ActionListener btnBoxListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	};

	/**
	 * 
	 */

	ActionListener btnAlbumListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

		}
	};

	/**
	 * 
	 * @return TreeModelListener
	 */
	private TreeModelListener getPhotoAlbumListener() {
		return new TreeModelListener() {

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				jtreePhotoAlbum.revalidate();
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				jtreePhotoAlbum.revalidate();
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				jtreePhotoAlbum.revalidate();
			}

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				jtreePhotoAlbum.repaint();
				jtreePhotoAlbum.revalidate();
			}

		};
	}

	
	private TreeModelListener getPhotoBoxListener() {
		return new TreeModelListener() {

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				jtreePhotoBox.revalidate();
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				jtreePhotoBox.revalidate();
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				jtreePhotoBox.revalidate();
			}

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				jtreePhotoBox.repaint();
				jtreePhotoBox.revalidate();
			}

		};
	}

}
