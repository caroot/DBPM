package de.htw.hundertwasser.core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

import de.htw.hundertwasser.core.interfaces.ProgressStatusEventListener;
import de.htw.hundertwasser.custom.event.ProgressStatusEvent;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;
/**
 * This class will represent a Progressbar for the importstatus
 * @author daniel
 *
 */
public class ImportStatusBar extends JDialog implements PropertyChangeListener,ProgressStatusEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Maximum for Progress-Amount
	 */
	private static final int MAX_PROGRESS_VALUE = 100;
	/**
	 * Current width
	 */
	public static int WIDTH = 600;
	/**
	 * Current height
	 */
	public static int HEIGHT = 110;
	/**
	 * Current BackgroundColor
	 */
	private static Color BACKGROUNDCOLOR = Color.WHITE;
	/**
	 * Current ForeGroundColor
	 */
	private static Color FOREGROUNDCOLOR = Color.BLUE;
	
	/**
	 * Current status information
	 */
	private JLabel lblstatus=null;	
	/**
	 * Current progress
	 */
	private JProgressBar progressbar = null;
	/**
	 * Constructor
	 */
	public ImportStatusBar()
	{
		SpringLayout layout = new SpringLayout();
		try {
			lblstatus=new JLabel("Status",RessourcenEnummeration.INFO_ICON.getIcon(),JLabel.LEADING);
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(this, e.getLocalizedMessage(), "Error", e.getStackTrace());
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setMinimumSize(new Dimension(WIDTH,HEIGHT));
		setBackground(BACKGROUNDCOLOR);
		progressbar = new JProgressBar();
		progressbar.setMaximum(MAX_PROGRESS_VALUE);
		progressbar.setBackground(BACKGROUNDCOLOR);
		progressbar.setForeground(FOREGROUNDCOLOR);
		lblstatus.setForeground(FOREGROUNDCOLOR);
		progressbar.setIndeterminate(true);
		setLayout(layout);
		Container contentPane = getContentPane();
		contentPane.add(lblstatus);
		contentPane.add(progressbar);
		layout.putConstraint(SpringLayout.NORTH, lblstatus, 10,SpringLayout.NORTH,contentPane);
		layout.putConstraint(SpringLayout.WEST, lblstatus, 10,SpringLayout.WEST,contentPane);
		layout.putConstraint(SpringLayout.NORTH, progressbar, 10,SpringLayout.SOUTH,lblstatus);
		layout.putConstraint(SpringLayout.WEST, progressbar, 10,SpringLayout.WEST,contentPane);
		layout.putConstraint(SpringLayout.SOUTH, progressbar, 50,SpringLayout.SOUTH,lblstatus);
		layout.putConstraint(SpringLayout.WIDTH,progressbar,-20,SpringLayout.WIDTH,contentPane);
		layout.putConstraint(SpringLayout.WIDTH,lblstatus,-20,SpringLayout.WIDTH,contentPane);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName() ) {
            int progress = (Integer) evt.getNewValue();
            progressbar.setIndeterminate(false);
            progressbar.setValue(progress);
		}
	}

	@Override
	public void handleProgressStatusEvent(EventObject statusinformation) {
		ProgressStatusEvent event = (ProgressStatusEvent)statusinformation;
		lblstatus.setText(event.getStatusInformation());
		if (event.getFinished())
		{
			dispose();
		}
	}

	
	public static void main(String[] args) {
		ImportStatusBar imp = new ImportStatusBar();
		imp.setVisible(true);
		imp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
