package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.jws.Oneway;
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
import de.htw.hundertwasser.backend.FolderManager;
import de.htw.hundertwasser.core.interfaces.NavBarPhotoBoxObserable;
import de.htw.hundertwasser.core.interfaces.NavBarPhotoBoxObserver;
import de.htw.hundertwasser.custom.error.CantCreateDirectoryException;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.view.StartScreen;

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

	private static final String ERROR_TITLE = "NavigationBar Error";
	private static final String ERROR_FILEMANAGER = "An Error Occured within the Filemanager Operation";
	private static final String ERROR_FONT = "An Error occured while trying to change font";
	private static final String ERROR_LISTENER = "An Error occured within the navbar tree";
	
	
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

	private DefaultTreeModel boxTreeModel;
	private DefaultTreeModel albumTreeModel;

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
		albumTreeModel = new DefaultTreeModel(albumRoot);
		jtreePhotoAlbum.setModel(albumTreeModel);

		fillTreePhotoAlbum();

		boxRoot = new DefaultMutableTreeNode("your photoboxes");
		boxTreeModel = new DefaultTreeModel(boxRoot);
		jtreePhotoBox.setModel(boxTreeModel);

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
		} catch (OperationNotSupportedException opnosuppexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FONT, ERROR_TITLE,
					opnosuppexc.getStackTrace());
		} catch (IOException ioexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FONT, ERROR_TITLE,
					ioexc.getStackTrace());
		} catch (FontFormatException fontformexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FONT, ERROR_TITLE,
					fontformexc.getStackTrace());
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

	/**
	 * 
	 */

	TreeSelectionListener BoxSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent tse) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtreePhotoBox
					.getLastSelectedPathComponent();

			// System.out.print(node.getUserObject().toString());
			try {
				node.getUserObject();
				sendMessage(ElementStorage.getPhotoBox(node.getUserObject()
						.toString()));

			} catch (NullPointerException nullpntrexc) {
				ErrorMessageDialog.showMessage(null, ERROR_LISTENER, ERROR_TITLE,
						nullpntrexc.getStackTrace());
			}
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
			boxTreeModel.reload();
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
				// jtreePhotoBox.setModel(new DefaultTreeModel(boxRoot));
				// System.out.println("filltreephotobox");
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

		// System.out.println("\n" + boxRoot.getLastChild().toString());
		// boxTreeModel.reload();

		String path = DialogHandler.chooseSource();
		if (path == null) {
			return;
		}
		FolderManager manager = new FolderManager();
		try {
			photobox = manager.importPhotoBox(name, path);
		} catch (FileNotFoundException filenotfoundexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FILEMANAGER,
					ERROR_TITLE, filenotfoundexc.getStackTrace());
		} catch (IllegalArgumentException illegargexcp) {
			ErrorMessageDialog.showMessage(null, ERROR_FILEMANAGER,
					ERROR_TITLE, illegargexcp.getStackTrace());
		} catch (ChoosenFileNotAFolderException notafileexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FILEMANAGER,
					ERROR_TITLE, notafileexc.getStackTrace());
		} catch (CantCreateDirectoryException cantcreateexc) {
			ErrorMessageDialog.showMessage(null, ERROR_FILEMANAGER,
					ERROR_TITLE, cantcreateexc.getStackTrace());
		}
		if (photobox == null)
			return; // Import verworfen.

		ElementStorage.addPhotoBox(photobox);
		DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(name);
		boxRoot.add(newChild);

		jtreePhotoBox.updateUI();

		StartScreen.refreshBoxes();
	}

	/**
	 * 
	 */
	public void addPhotoAlbum() {
		String name = JOptionPane.showInputDialog(null, NAME, "Name the album",
				JOptionPane.QUESTION_MESSAGE);

		photoalbum = new PhotoAlbum(name);
		ElementStorage.addPhotoAlbum(photoalbum);

		jtreePhotoAlbum.updateUI();
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
		selectPhotoBoxElement(photoBox.getName());
		for (NavBarPhotoBoxObserver observer : observerList) {
			observer.receivePhotoBox(photoBox);
		}

	}
}