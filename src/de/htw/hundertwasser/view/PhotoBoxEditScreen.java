package de.htw.hundertwasser.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

public class PhotoBoxEditScreen extends JFrame {

	private JFrame frmPBES;

	public PhotoBoxEditScreen() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 120, 250);
		getContentPane().add(scrollPane);

		JTree photoBoxesTree = new JTree();
		scrollPane.setViewportView(photoBoxesTree);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 300, 120, 250);
		getContentPane().add(scrollPane_1);

		JTree photoAlbumTree = new JTree();
		scrollPane_1.setViewportView(photoAlbumTree);

		JScrollPane InfoPanel = new JScrollPane();
		InfoPanel.setBounds(850, 40, 120, 125);
		getContentPane().add(InfoPanel);

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.LIGHT_GRAY);
		InfoPanel.setViewportView(infoPanel);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(850, 176, 120, 317);
		getContentPane().add(toolBar);

		JPanel thumbnails = new JPanel();
		thumbnails.setBackground(Color.BLACK);
		thumbnails.setBounds(10, 561, 120, 140);
		getContentPane().add(thumbnails);

		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setBounds(800, 279, 40, 35);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("LAST");
		btnNewButton_1.setBounds(140, 279, 40, 35);
		getContentPane().add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(190, 40, 600, 510);
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(228, 561, 100, 60);
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(228, 641, 100, 60);
		getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.YELLOW);
		panel_3.setBounds(338, 561, 100, 60);
		getContentPane().add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.YELLOW);
		panel_4.setBounds(338, 641, 100, 60);
		getContentPane().add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.YELLOW);
		panel_5.setBounds(448, 561, 100, 60);
		getContentPane().add(panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.YELLOW);
		panel_6.setBounds(448, 641, 100, 60);
		getContentPane().add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.YELLOW);
		panel_7.setBounds(558, 561, 100, 60);
		getContentPane().add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.YELLOW);
		panel_8.setBounds(558, 641, 100, 60);
		getContentPane().add(panel_8);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.YELLOW);
		panel_9.setBounds(668, 561, 100, 60);
		getContentPane().add(panel_9);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.YELLOW);
		panel_10.setBounds(668, 641, 100, 60);
		getContentPane().add(panel_10);

		JButton button = new JButton("NEXT");
		button.setBounds(778, 614, 40, 35);
		getContentPane().add(button);

		// JButton button_1 = new JButton("NEXT");
		// button_1.setBounds(178, 614, 40, 35);
		// getContentPane().add(button_1);
	}

	public static void main(String[] args) {

		PhotoBoxEditScreen frmPBES = new PhotoBoxEditScreen();
		frmPBES.setVisible(true);
		frmPBES.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frmPBES.setTitle("Dunkelbunt Photomanager");
		frmPBES.setBounds(80, 10, 1200, 700);
		frmPBES.getContentPane().setLayout(null);
		// frmPBES.getContentPane().setLayout(mgr);

	}
}
