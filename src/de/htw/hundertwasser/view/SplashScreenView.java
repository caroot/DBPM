package de.htw.hundertwasser.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

import de.htw.hundertwasser.backend.ElementStorage;
import de.htw.hundertwasser.backend.StartUpTask;
import de.htw.hundertwasser.core.ImportStatusBar;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.core.interfaces.ProgressStartUpEventListener;
import de.htw.hundertwasser.core.interfaces.ProgressStatusEventListener;
import de.htw.hundertwasser.custom.event.ProgressStartUpEvent;
import de.htw.hundertwasser.custom.event.ProgressStatusEvent;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/**
 * The First Screen whithin the Application
 * it will shown up at startup.
 * @author daniel
 *
 */
public class SplashScreenView extends JFrame implements PropertyChangeListener,ProgressStartUpEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel photoBoxLoad = null;
	private JLabel photoAlbumLoad = null;
	private JLabel integrityCheck = null;
	private JLabel runApplication = null;
	private JLabel information = null;
	private JProgressBar progressBar = null;
	/**
	 * Maximum for Progress-Amount
	 */
	private static final int MAX_PROGRESS_VALUE = 100;
	
	/**
	 * Current width
	 */
	public static int WIDTH = 450;
	/**
	 * Current height
	 */
	public static int HEIGHT = 220;
	/**
	 * Current BackgroundColor
	 */
	private static Color BACKGROUNDCOLOR = Color.WHITE;
	/**
	 * Current ForeGroundColor
	 */
	private static Color FOREGROUNDCOLOR = Color.BLUE;
	
	
	public SplashScreenView()
	{
		photoBoxLoad = new JLabel();
		photoAlbumLoad = new JLabel();
		integrityCheck = new JLabel();
		information = new JLabel("State");
		progressBar = new JProgressBar();
		runApplication = new JLabel();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setMinimumSize(new Dimension(WIDTH,HEIGHT));
		SpringLayout layout= new SpringLayout();
		Container contentPane = getContentPane();
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT) / 2
				);
//		setUndecorated(true);
		contentPane.setLayout(layout);
		contentPane.add(photoBoxLoad);
		contentPane.add(photoAlbumLoad);
		contentPane.add(integrityCheck);
		contentPane.add(runApplication);
		contentPane.add(information);
		contentPane.add(progressBar);
		loadIcons();
		layout.putConstraint(SpringLayout.NORTH, photoBoxLoad, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.NORTH, photoAlbumLoad, 10, SpringLayout.NORTH,contentPane);
		layout.putConstraint(SpringLayout.NORTH, integrityCheck, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.NORTH, information, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.NORTH, progressBar, 10, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.NORTH, runApplication, 10, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, photoBoxLoad,10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.WEST, photoAlbumLoad,10, SpringLayout.EAST, photoBoxLoad);
		layout.putConstraint(SpringLayout.WEST, integrityCheck,10, SpringLayout.EAST, photoAlbumLoad);
		layout.putConstraint(SpringLayout.WEST, runApplication,10, SpringLayout.EAST, integrityCheck);
		layout.putConstraint(SpringLayout.NORTH, information,10, SpringLayout.SOUTH, photoBoxLoad);
		layout.putConstraint(SpringLayout.NORTH, progressBar,10, SpringLayout.SOUTH, information);
		layout.putConstraint(SpringLayout.WEST, information,10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.WEST, progressBar,10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.WIDTH, progressBar,-20, SpringLayout.WIDTH, contentPane);
		setBackground(BACKGROUNDCOLOR);
		progressBar.setMaximum(MAX_PROGRESS_VALUE);
		progressBar.setBackground(BACKGROUNDCOLOR);
		progressBar.setForeground(FOREGROUNDCOLOR);
		information.setForeground(FOREGROUNDCOLOR);
		setTitle("Loading DunkelBuntPhotoManager");
		
	}
	
	private void loadIcons()
	{
		try
		{
			information.setIcon(RessourcenEnummeration.INFO_ICON.getIcon());
			showPhotoAlbumGray();
			showPhotoBoxGray();
			showRunApplicationGray();
			showIntegretyGray();
		}
		catch(IOException ex)
		{
			ErrorMessageDialog.showMessage(this, ex.getMessage(),"Error", ex.getStackTrace());
			ex.printStackTrace();
		}
	}
	
	public void start()
	{
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try
		{
		if ("progress" == evt.getPropertyName() ) {
            int progress = (Integer) evt.getNewValue();
            switch(progress)
            {
            case 1:
            break;
            case 25:
            break;
            case 50:
            break;
            case 75:
            	showIntegretyFilled();
            break;
            case 100:
            	showRunApplicationFilled();
            break;
            }
            progressBar.setIndeterminate(false);
            progressBar.setValue(progress);
		}
		}
		catch(IOException ex)
		{
			ErrorMessageDialog.showMessage(this, ex.getMessage(), "Error", ex.getStackTrace());
			ex.printStackTrace();
		}
	}

	private void showPhotoBoxGray() throws IOException
	{
		photoBoxLoad.setIcon(RessourcenEnummeration.PHOTOBOX_HINZUFUEGEN.getIcon());
	}
	private void showPhotoBoxGrayUnfilled() throws IOException
	{
		photoBoxLoad.setIcon(RessourcenEnummeration.PHOTOBOX_HINZUFUEGEN_NEU.getIcon());
	}
	private void showPhotoBoxGrayFilled() throws IOException
	{
		photoBoxLoad.setIcon(RessourcenEnummeration.PHOTOBOX.getIcon());
	}
	private void showPhotoAlbumGray() throws IOException
	{
		photoAlbumLoad.setIcon(RessourcenEnummeration.PHOTOALBUM_HINZUFUEGEN.getIcon());
	}
	private void showPhotoAlbumGrayUnfilled() throws IOException
	{
		photoAlbumLoad.setIcon(RessourcenEnummeration.PHOTOALBUM_HINZUFUEGEN_NEU.getIcon());
	}
	private void showPhotoAlbumGrayFilled() throws IOException
	{
		photoAlbumLoad.setIcon(RessourcenEnummeration.PHOTOALBUM.getIcon());
	}
	private void showIntegretyGray() throws IOException
	{
		integrityCheck.setIcon(RessourcenEnummeration.INTEGRETY_CHECK_GRAY.getIcon());
	}
	private void showIntegretyFilled() throws IOException
	{
		integrityCheck.setIcon(RessourcenEnummeration.INTEGRETY_CHECK.getIcon());
	
	}
	private void showRunApplicationGray() throws IOException
	{
		runApplication.setIcon(RessourcenEnummeration.RUN_GRAY.getIcon());
	}
	private void showRunApplicationFilled() throws IOException
	{
		runApplication.setIcon(RessourcenEnummeration.RUN.getIcon());
	}
	
	private void startStartScreen()
	{
		setVisible(false);		
		StartScreen startScreen = new StartScreen();
		StartScreen.refreshAlbums();
		StartScreen.refreshBoxes();
	}
	
	@Override
	public void handleProgressStartUpEvent(EventObject event) {
		if (event instanceof ProgressStatusEvent)
		{
		ProgressStatusEvent myevent = (ProgressStatusEvent)event;
		information.setText(myevent.getStatusInformation());
		}
		else
		{
		ProgressStartUpEvent eventstartup = (ProgressStartUpEvent)event;
		information.setText(eventstartup.getStatusInformation());
		try
		{
		if (eventstartup.getPhotoboxList()!=null)
		{
			for (PhotoBox box:eventstartup.getPhotoboxList())
			{
				information.setText("Add Photobox " + box.getName() );
				ElementStorage.addPhotoBox(box);
			}
			showPhotoBoxGrayFilled();
		}
		if (eventstartup.getPhotoAlbumList()!=null)
		{
			for (PhotoAlbum album:eventstartup.getPhotoAlbumList())
			{
				ElementStorage.addPhotoAlbum(album);
			}
			showPhotoAlbumGrayFilled();
		}
		if (eventstartup.isIntegrityCheck())
		{
			showIntegretyFilled();
		}
		if (eventstartup.isRunApplication())
		{
			showIntegretyFilled();
			startStartScreen();
		}
		}
		catch (IOException ex)
		{
			ErrorMessageDialog.showMessage(this, ex.getMessage(), "Error", ex.getStackTrace());
			ex.printStackTrace();
		}
		}
	}
	

	public static void main(String[] args) {
		SplashScreenView imp = new SplashScreenView();
		imp.setVisible(true);
		StartUpTask task = new StartUpTask();
		task.addEventListener(imp);
		task.addPropertyChangeListener(imp);
		task.execute();
//		imp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	
}
