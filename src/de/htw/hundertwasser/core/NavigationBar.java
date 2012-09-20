package de.htw.hundertwasser.core;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;



public class NavigationBar extends JPanel{
	public NavigationBar() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(6, 6, 141, 288);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(6, 6, 129, 115);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblYourPhotoAlbums = new JLabel("      Your photo boxes");
		lblYourPhotoAlbums.setVerticalAlignment(SwingConstants.TOP);
		lblYourPhotoAlbums.setFont(new Font("Calibri", Font.PLAIN, 11));
		panel_1.add(lblYourPhotoAlbums);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(6, 149, 129, 115);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblYourPhotoAlbums_1 = new JLabel("     Your photo albums");
		lblYourPhotoAlbums_1.setVerticalAlignment(SwingConstants.TOP);
		lblYourPhotoAlbums_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		panel_2.add(lblYourPhotoAlbums_1);
		
		JButton btnNewButton = new JButton("+new photoalbum");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(16, 122, 100, 24);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("+new photoalbum");
		btnNewButton_1.setBounds(16, 264, 100, 24);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 10));
	}
}
