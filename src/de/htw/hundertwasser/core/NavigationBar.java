package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import de.htw.hundertwasser.backend.ElementStorage;
import de.htw.hundertwasser.core.interfaces.NavBarPhotoBoxObserable;
import de.htw.hundertwasser.core.interfaces.NavBarPhotoBoxObserver;
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

public class NavigationBar extends JPanel implements NavBarPhotoBoxObserable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private NavBarPhotoAlbumModel modelPhotoAlbum;
	// private NavBarPhotoAlbumRenderer rendererPhotoAlbum;
	// private NavBarPhotoBoxModel modelPhotoBox;
	// private NavBarPhotoBoxRenderer rendererPhotoBox;
	private JTree jtreePhotoAlbum;

	private JScrollPane scrlPhotoAlben;
	private JScrollPane scrlPhotoBoxes;
	private JTree jtreePhotoBox;
	private Font font;
	private DefaultMutableTreeNode boxRoot;
	private DefaultMutableTreeNode albumRoot;
	private ArrayList<NavBarPhotoBoxObserver> observerList;

	// private ArrayList<String> test;

	/**
	 * Constructor
	 */
	public NavigationBar() throws IOException {

		jtreePhotoAlbum = new JTree();
		jtreePhotoBox = new JTree();
		jtreePhotoBox.addTreeSelectionListener(BoxSelectionListener);

		scrlPhotoAlben = new JScrollPane();
		scrlPhotoBoxes = new JScrollPane();

		// modelPhotoAlbum = new NavBarPhotoAlbumModel();
		// rendererPhotoAlbum = new NavBarPhotoAlbumRenderer();
		// modelPhotoBox = new NavBarPhotoBoxModel();
		// rendererPhotoBox = new NavBarPhotoBoxRenderer();

		// jtreePhotoAlbum.setModel(modelPhotoAlbum);
		// jtreePhotoAlbum.setCellRenderer(rendererPhotoAlbum);
		// modelPhotoAlbum.addTreeModelListener(getPhotoAlbumListener());
		//
		// jtreePhotoBox.setModel(modelPhotoBox);
		// jtreePhotoBox.setCellRenderer(rendererPhotoBox);
		// modelPhotoBox.addTreeModelListener(getPhotoBoxListener());

		observerList = new ArrayList<NavBarPhotoBoxObserver>();

		albumRoot = new DefaultMutableTreeNode("your photoalbums");
		jtreePhotoAlbum.setModel(new DefaultTreeModel(albumRoot));
		 fillTreePhotoAlbum();

		boxRoot = new DefaultMutableTreeNode("your photoboxes");
		jtreePhotoBox.setModel(new DefaultTreeModel(boxRoot));
		fillTreePhotoBox();

		// test = new ArrayList<String>();
		// fillArrayList();
		// fillTreePhotoBox(test);
		// fillTreePhotoAlbum(test);

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

	TreeSelectionListener BoxSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent tse) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtreePhotoBox
					.getLastSelectedPathComponent();

			System.out.print(node.getUserObject().toString());
			sendMessage(ElementStorage.getPhotoBox(node.getUserObject()
					.toString()));
		}
	};


	TreeSelectionListener AlbumSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent tse) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtreePhotoAlbum
					.getLastSelectedPathComponent();

//			TODO sendMessage(photoalbum) to editscreen
			
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

	/**
	 * 
	 * @return
	 */

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

	/**
	 * 
	 */
	public void fillTreePhotoBox() {

		ArrayList<PhotoBox> photobox = ElementStorage.getBoxList();
		if (photobox != null) {
			for (int i = 0; i < photobox.size(); i++) {
				boxRoot.add(new DefaultMutableTreeNode(photobox.get(i)
						.getName()));
				// System.out.println(test.get(i));
			}
		}

	}

	/**
	 * 
	 */
	public void fillTreePhotoAlbum() {

		ArrayList<PhotoAlbum> photoalbum = ElementStorage.getAlbumList();
		if (photoalbum != null) {
			for (int i = 0; i < photoalbum.size(); i++) {
				albumRoot.add(new DefaultMutableTreeNode(photoalbum.get(i).getName()));
				// System.out.println(test.get(i));
			}
		}

	}

	@Override
	public void addNavBarPhotoBoxObserver(NavBarPhotoBoxObserver observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);

	}

	@Override
	public void removeNavBarPhotoBoxObserver(NavBarPhotoBoxObserver observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);
	}

	@Override
	public void sendMessage(PhotoBox photoBox) {
		// TODO Auto-generated method stub
		for (NavBarPhotoBoxObserver observer : observerList) {
			observer.receivePhotoBox(photoBox);
		}

		// private void fillArrayList() {
		//
		// for (int j = 0; j < 10; j++) {
		//
		// test.add("Test " + j);
		// System.out.println(test.get(j));
		// }
		// }

	}
}