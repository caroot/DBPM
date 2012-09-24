package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

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

	private static final String NAME = "Please Enter a name:";

	private JTree jtreePhotoAlbum;
	private JTree jtreePhotoBox;

	private JScrollPane scrlPhotoAlben;
	private JScrollPane scrlPhotoBoxes;

	private Font font;

	private DefaultMutableTreeNode boxRoot;
	private DefaultMutableTreeNode albumRoot;

	private ArrayList<NavBarPhotoBoxObserver> observerList;

	private PhotoBox photobox;
	private PhotoAlbum photoalbum;

	/**
	 * Constructor
	 */
	public NavigationBar() throws IOException {

		setBackground(Color.WHITE);

		scrlPhotoAlben = new JScrollPane();
		scrlPhotoBoxes = new JScrollPane();

		jtreePhotoAlbum = new JTree();
		jtreePhotoBox = new JTree();

		jtreePhotoBox.addTreeSelectionListener(BoxSelectionListener);
		jtreePhotoAlbum.addTreeSelectionListener(AlbumSelectionListener);

		jtreePhotoBox.getModel().addTreeModelListener(boxListener);

		observerList = new ArrayList<NavBarPhotoBoxObserver>();

		albumRoot = new DefaultMutableTreeNode("your photoalbums");
		jtreePhotoAlbum.setModel(new DefaultTreeModel(albumRoot));
		fillTreePhotoAlbum();

		boxRoot = new DefaultMutableTreeNode("your photoboxes");
		jtreePhotoBox.setModel(new DefaultTreeModel(boxRoot));
		fillTreePhotoBox();

		JPanel pnlPhotoBox = new JPanel();
		pnlPhotoBox.setLayout(new BorderLayout(0, 0));

		pnlPhotoBox.add(scrlPhotoBoxes, BorderLayout.CENTER);
		scrlPhotoBoxes.setViewportView(jtreePhotoBox);

		JButton btnNewBox = new JButton("+ new photobox      ");
		btnNewBox.setToolTipText("+ new photobox");
		btnNewBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewBox.addActionListener(btnBoxListener);
		btnNewBox.setBackground(Color.WHITE);
		btnNewBox.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewBox.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pnlPhotoAlbum = new JPanel();
		pnlPhotoAlbum.setLayout(new BorderLayout(0, 0));
		pnlPhotoAlbum.add(scrlPhotoAlben, BorderLayout.CENTER);
		scrlPhotoAlben.setViewportView(jtreePhotoAlbum);

		JButton btnNewAlbum = new JButton("+ new photoalbum    ");
		btnNewAlbum.setToolTipText("+ new photoalbum");
		btnNewAlbum.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewAlbum.addActionListener(btnAlbumListener);
		btnNewAlbum.setBackground(Color.WHITE);
		btnNewAlbum.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewAlbum.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlPhotoBox, GroupLayout.DEFAULT_SIZE, 100,
						GroupLayout.DEFAULT_SIZE)
				.addGroup(
						groupLayout.createSequentialGroup().addGap(50)
								.addComponent(btnNewBox))
				.addComponent(pnlPhotoAlbum, GroupLayout.DEFAULT_SIZE, 100,
						GroupLayout.DEFAULT_SIZE)
				.addGroup(
						groupLayout.createSequentialGroup().addGap(50)
								.addComponent(btnNewAlbum)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(pnlPhotoBox, GroupLayout.DEFAULT_SIZE,
								100, GroupLayout.DEFAULT_SIZE)
						.addComponent(btnNewBox)
						.addComponent(pnlPhotoAlbum, GroupLayout.DEFAULT_SIZE,
								100, GroupLayout.DEFAULT_SIZE)
						.addComponent(btnNewAlbum)));
		setLayout(groupLayout);

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
			addPhotoBox();
			// fillTreePhotoBox();
		}
	};

	/**
	 * 
	 */

	ActionListener btnAlbumListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			addPhotoAlbum();
		}
	};

	TreeSelectionListener BoxSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent tse) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtreePhotoBox
					.getLastSelectedPathComponent();

			// System.out.print(node.getUserObject().toString());
			sendMessage(ElementStorage.getPhotoBox(node.getUserObject()
					.toString()));
		}
	};

	TreeSelectionListener AlbumSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent tse) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtreePhotoAlbum
					.getLastSelectedPathComponent();

			// TODO sendMessage(photoalbum) to editscreen

		}
	};

	TreeModelListener boxListener = new TreeModelListener() {

		@Override
		public void treeStructureChanged(TreeModelEvent e) {
			jtreePhotoBox.revalidate();

		}

		@Override
		public void treeNodesRemoved(TreeModelEvent e) {
			jtreePhotoBox.revalidate();

		}

		@Override
		public void treeNodesInserted(TreeModelEvent e) {
			jtreePhotoBox.revalidate();

		}

		@Override
		public void treeNodesChanged(TreeModelEvent e) {
			jtreePhotoBox.revalidate();
			jtreePhotoBox.repaint();

		}
	};

	/**
	 * 
	 */
	public void fillTreePhotoBox() {

		ArrayList<PhotoBox> photobox = ElementStorage.getBoxList();
		if (photobox != null) {
			for (int i = 0; i < photobox.size(); i++) {
				boxRoot.add(new DefaultMutableTreeNode(photobox.get(i)
						.getName()));
				jtreePhotoBox.setModel(new DefaultTreeModel(boxRoot));
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
				albumRoot.add(new DefaultMutableTreeNode(photoalbum.get(i)
						.getName()));
				// System.out.println(test.get(i));
			}
		}

	}

	/**
	 * 
	 */
	public void addPhotoBox() {
		// System.out.println(ElementStorage.getBoxList().toString());
		String name = JOptionPane.showInputDialog(null, NAME, "Name the box",
				JOptionPane.QUESTION_MESSAGE);

		photobox = new PhotoBox(DialogHandler.chooseSource());
		photobox.setName(name);
		ElementStorage.addPhotoBox(photobox);

		boxRoot.removeAllChildren();
		fillTreePhotoBox();
		// System.out.println(ElementStorage.getBoxList().toString());
	}

	/**
	 * 
	 */
	public void addPhotoAlbum() {
		String name = JOptionPane.showInputDialog(null, NAME, "Name the album",
				JOptionPane.QUESTION_MESSAGE);

		photoalbum = new PhotoAlbum(DialogHandler.chooseSource());
		photoalbum.setName(name);
		ElementStorage.addPhotoAlbum(photoalbum);

		albumRoot.removeAllChildren();
		fillTreePhotoAlbum();
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

	/**
	 * 
	 * @param name
	 */
	public void selectPhotoBoxElement(String name) {
		for (int i = 0; i < jtreePhotoBox.getModel().getChildCount(
				jtreePhotoBox.getModel().getRoot()); i++) {
			DefaultMutableTreeNode tn = (DefaultMutableTreeNode) jtreePhotoBox
					.getModel().getChild(jtreePhotoBox.getModel().getRoot(), i);
			if (tn.getUserObject().toString().equals(name)) {
				jtreePhotoBox.setSelectionPath(new TreePath(tn.getPath()));
			}
		}
	}

	/**
	 * 
	 * @param name
	 */
	public void selectPhotoAlbumElement(String name) {
		for (int i = 0; i < jtreePhotoAlbum.getModel().getChildCount(
				jtreePhotoAlbum.getModel().getRoot()); i++) {
			DefaultMutableTreeNode tn = (DefaultMutableTreeNode) jtreePhotoAlbum
					.getModel().getChild(jtreePhotoAlbum.getModel().getRoot(),
							i);
			if (tn.getUserObject().toString().equals(name)) {
				jtreePhotoAlbum.setSelectionPath(new TreePath(tn.getPath()));
			}
		}
	}

	@Override
	public void sendMessage(PhotoBox photoBox) {
		// TODO Auto-generated method stub
		selectPhotoBoxElement(photoBox.getName());
		for (NavBarPhotoBoxObserver observer : observerList) {
			observer.receivePhotoBox(photoBox);
		}

	}
}