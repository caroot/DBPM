
package de.htw.hundertwasser.view;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PhotoAlbumFullScreen extends JFrame {
	public PhotoAlbumFullScreen() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setInheritsPopupMenu(true);
		btnNewButton.setIgnoreRepaint(false);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/arrow_left.png")));
		btnNewButton.setBounds(173, 233, 27, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setSelectedIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen.png")));
		btnNewButton_1.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/exit_fullscreen.png")));
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(202, 233, 57, 29);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(PhotoAlbumFullScreen.class.getResource("/de/htw/hundertwasser/res/arrow_right.png")));
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		btnNewButton_2.setBounds(259, 233, 27, 29);
		getContentPane().add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 434, 232);
		getContentPane().add(panel);
	}
}
