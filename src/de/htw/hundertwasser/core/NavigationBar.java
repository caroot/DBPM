package de.htw.hundertwasser.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class NavigationBar extends JFrame{
	private JTextField txtYourPhotoBoxes;
	private JTextField txtYourPhotoAlbums;
	public NavigationBar() {
		
		setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-30 ));	
		
		setUndecorated(true);
		setState(JFrame.MAXIMIZED_BOTH);
		setTitle("NavigationBar");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 144, 400);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPhotobox = new JButton("+ New PhotoAlbum");
		btnPhotobox.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnPhotobox.setBounds(6, 373, 110, 27);
		panel.add(btnPhotobox);
		
		JButton btnNewButton = new JButton("+New PhotoBox");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnNewButton.setBounds(6, 174, 110, 27);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 130, 162);
		panel.add(scrollPane);
		
		txtYourPhotoBoxes = new JTextField();
		txtYourPhotoBoxes.setBackground(Color.WHITE);
		txtYourPhotoBoxes.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtYourPhotoBoxes.setText("Your Photo Boxes\n");
		scrollPane.setColumnHeaderView(txtYourPhotoBoxes);
		txtYourPhotoBoxes.setColumns(10);
		
		JTree tree_1 = new JTree();
		tree_1.setFont(new Font("Calibri", Font.PLAIN, 10));
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Photo Boxes") {
				{
					add(new DefaultMutableTreeNode(""));
				}
			}
		));
		scrollPane.setViewportView(tree_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 204, 130, 162);
		panel.add(scrollPane_1);
		
		txtYourPhotoAlbums = new JTextField();
		txtYourPhotoAlbums.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtYourPhotoAlbums.setText("Your Photo Albums");
		scrollPane_1.setColumnHeaderView(txtYourPhotoAlbums);
		txtYourPhotoAlbums.setColumns(10);
		
		JTree tree = new JTree();
		tree.setFont(new Font("Calibri", Font.PLAIN, 10));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Photo Albums") {
				{
					add(new DefaultMutableTreeNode(""));
				}
			}
		));
		scrollPane_1.setViewportView(tree);
	}
}
