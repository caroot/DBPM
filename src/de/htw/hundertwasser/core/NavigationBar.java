package de.htw.hundertwasser.core;

import javax.naming.OperationNotSupportedException;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import de.htw.hundertwasser.res.RessourcenEnummeration;
import de.htw.hundertwasser.view.StartScreenElement;

/*
 * Class that creates the NavigationBar
 * @author Johannes Schramm
 */
public class NavigationBar extends JPanel {
	
	/*
	 * Constructor
	 */
	public NavigationBar() {
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(150, 803));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(6, 6, 141, 575);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		panel_1.setBounds(6, 6, 129, 231);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblYourPhotoAlbums = new JLabel("     Your photo boxes");
		lblYourPhotoAlbums.setVerticalAlignment(SwingConstants.TOP);
		lblYourPhotoAlbums.setFont(new Font("Calibri", Font.PLAIN, 13));
		panel_1.add(lblYourPhotoAlbums);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1,
				(Color) new Color(0, 0, 0)));
		panel_2.setBounds(6, 285, 129, 246);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblYourPhotoAlbums_1 = new JLabel("     Your photo albums");
		panel_2.add(lblYourPhotoAlbums_1, BorderLayout.CENTER);
		lblYourPhotoAlbums_1.setVerticalAlignment(SwingConstants.TOP);
		lblYourPhotoAlbums_1.setFont(new Font("Calibri", Font.PLAIN, 13));

		JButton btnNewButton = new JButton("+new photobox");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(6, 249, 129, 24);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("+new photoalbum");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(6, 540, 129, 24);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));

		// Font
		Font font;
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
}
