package de.htw.hundertwasser.backend;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import de.htw.hundertwasser.core.EnvironmentChecker;
import de.htw.hundertwasser.core.ImportStatusBar;
import de.htw.hundertwasser.core.Photo;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.core.interfaces.FolderManagerObservable;
import de.htw.hundertwasser.core.interfaces.FolderManagerObserver;
import de.htw.hundertwasser.core.interfaces.ProgressStatusEventListener;
import de.htw.hundertwasser.custom.error.CantCreateDirectoryException;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.custom.event.ProgressStatusEvent;

/**
 * This class will manage the Folders of the current Project
 * 
 * @author daniel rhein
 * 
 */
public class FolderManager implements ProgressStatusEventListener,FolderManagerObservable{

	private static final String ERROR_NAME_EMPTY = "Name can't be empty";

	private static final String ERROR_NAME_NULL = "Name can't be null.";

	private EnvironmentChecker environmentChecker;

	private static final String ERROR_EMPTY_PATH = "The path can't be empty.";
	private static final String ERROR_NULL_PATH = "The path can't be null.";

	private static final String DPBM_WORKINGDIRECTORY = "Dunkelbunt-PhotoManager"
			+ File.separatorChar;
	
	private static final String DPBM_BINDIR = "bin"
			+ File.separatorChar;

	private static final String DPBM_PHOTOBOXDIR = "PhotoBox"
			+ File.separatorChar;
	
	public ArrayList<FolderManagerObserver> alObserverList = new ArrayList<FolderManagerObserver>();
	
	public FolderManager() {
		environmentChecker = new EnvironmentChecker();

	}

	/**
	 * Show the Application path
	 * 
	 * @return returns the Application Path
	 */
	public String getApplicationPath() {
		String pathname = System.getenv("APPDATA");
		if (pathname == null || pathname.trim().isEmpty()) {
			pathname = System.getProperty("user.home");
		}

		return pathname;
	}

	/**
	 * This method allows to determine if a Folder exists or not.
	 * 
	 * @param path
	 * @return true if the given path is a existing folder, false otherwise.
	 * @throws IllegalArgumentException
	 *             if the path is emtpy or null
	 */
	private boolean isFolderExists(String path) throws IllegalArgumentException {
		checkPath(path);
		File directory = new File(path);
		if (directory.isDirectory()) {
			if (directory.exists()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * This method returns the working directory.</br> This is the
	 * ApplicationPath concatenated with the name of our Programm. </br>
	 * <b>Attention:</b></br> If the working directory won't exist this Method
	 * tries to create a Directory.</br> If the creation of the working
	 * directory fails, you'll receive an
	 * <b>CantCreateDirectoryExceptoin</b></p>
	 * 
	 * @return String with the current working directory.
	 * @throws CantCreateDirectoryException
	 *             it will be thrown if it's not able to create the directory.
	 */
	public String getWorkingDirectory() throws CantCreateDirectoryException {
		StringBuilder sb = new StringBuilder();
		sb.append(getApplicationPath());
		if (!sb.toString().endsWith(String.valueOf(File.separatorChar))) {
			sb.append(File.separatorChar);
		}
		sb.append(DPBM_WORKINGDIRECTORY);
		if (!isFolderExists(sb.toString())) {
			if (createDirectories(sb.toString())) {
				return sb.toString();
			} else {
				throw new CantCreateDirectoryException(
						"Can't create Directory " + sb.toString());

			}
		} else {
			return sb.toString();
		}

	}
	
	public String getPhotoBoxWorkingDirectory() throws CantCreateDirectoryException
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getWorkingDirectory());
		sb.append(DPBM_PHOTOBOXDIR);
		if (!sb.toString().endsWith(String.valueOf(File.separatorChar))) {
			sb.append(File.separatorChar);
		}
		if (!isFolderExists(sb.toString())) {
			if (createDirectories(sb.toString())) {
				return sb.toString();
			} else {
				throw new CantCreateDirectoryException(
						"Can't create Directory " + sb.toString());

			}
		} else {
			return sb.toString();
		}
	}

	/**
	 * This method will create the directories
	 * 
	 * @param path
	 * @return
	 */
	protected boolean createDirectories(String path)
			throws IllegalArgumentException {
		checkPath(path);
		File directory = new File(path);
		return directory.mkdir();
	}

	/**
	 * This method will return a Set of the complete Environment-Keys.
	 * 
	 * @return
	 */
	public Set<String> getEnvironmentKeyValues() {
		Map<String, String> keymap = System.getenv();
		return keymap.keySet();
	}

	/**
	 * This method allows you to read the local environment with a ceartain key.
	 * 
	 * @param key
	 * @return
	 */
	public String getEnvironmentKeyValue(String key) {
		return System.getenv(key);
	}

	/**
	 * This method will return the users home-path if it's set.
	 * 
	 * @return
	 */
	public String getUsersHomePath() {
		return environmentChecker.getProperty("user.home");
	}

	/**
	 * Returns an File-List of Folders within the current path
	 * 
	 * @param path
	 *            ,absolute path to the file
	 * @return list of Files
	 * @throws IllegalArgumentException
	 *             ,ChoosenFileNotAFolderException,FileNotFoundException
	 */
	public File[] getFolderList(String path) throws IllegalArgumentException,
			ChoosenFileNotAFolderException, FileNotFoundException {
		File directory = null;
		checkPath(path);
		if (path.endsWith(".")) {
			directory = new File(path);
		} else {
			directory = new File(path + ".");
		}
		if (directory.exists()) {
			if (directory.isDirectory()) {
				return directory.listFiles();
			} else {
				throw new ChoosenFileNotAFolderException("Your path " + path
						+ " is not a folder.");
			}
		} else {
			throw new FileNotFoundException("Your path " + path
					+ " doesn't exists.");
		}
	}

	/**
	 * This function will return the current Photoboxes within the Working directory.
	 * @return
	 * @throws IllegalArgumentException
	 * @throws FileNotFoundException
	 * @throws ChoosenFileNotAFolderException
	 * @throws CantCreateDirectoryException
	 */
	public synchronized ArrayList<PhotoBox> readCurrentWorkingDirectoryPhotoBox() throws IllegalArgumentException, FileNotFoundException, ChoosenFileNotAFolderException, CantCreateDirectoryException
	{
		File[] directories = getFolderList(getPhotoBoxWorkingDirectory());
		if (directories == null) return null;
		if (directories.length==0) return null;
		ArrayList<PhotoBox> arPhotoBox = new ArrayList<PhotoBox>();
		for (File directory:directories)
		{
			PhotoBox newPhotoBox = createPhotoBox(directory.getAbsolutePath(),directory.getName());
			arPhotoBox.add(newPhotoBox);
		}
		return arPhotoBox;
	}
	
	
//	public void  deletePhotoBox(String name)
//	{
//		ArrayList<PhotoBox> photobox =readCurrentWorkingDirectoryPhotoBox();
//		
//	}
	/**
	 * ImportPhotobox and it's content to the current working directory.
	 * 
	 * @param name
	 *            Name of the Photobox
	 * @param pathtoFile
	 *            this is a ImageFile or a Directory
	 * @return The created Photobox
	 * @throws FileNotFoundException
	 *             Occurs if the pathToFile doesn't exists
	 * @throws ChoosenFileNotAFolderException
	 *             Occurs if the Path to File is not a folder
	 * @throws IllegalArgumentException
	 *             Occurs if one of the parameter is emtpy or null.
	 * @throws CantCreateDirectoryException
	 */
	public synchronized PhotoBox importPhotoBox(String name,String pathtoFile) throws FileNotFoundException, IllegalArgumentException, ChoosenFileNotAFolderException, CantCreateDirectoryException 
	{
		ImageManager imageManager = new ImageManager();
		
		ImportStatusBar importStatusBar = new ImportStatusBar();
		
		File[] fileList = null;
		PhotoBox photobox =null;
		checkPath(pathtoFile);
		if (name.trim().isEmpty()) throw new IllegalArgumentException(ERROR_NAME_EMPTY);
		if (name==null) throw new IllegalArgumentException(ERROR_NAME_NULL);
		File file = new File(pathtoFile);
		
		//Initialise importstatusbar
		{
		importStatusBar.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width-importStatusBar.WIDTH) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height-importStatusBar.HEIGHT) / 2
				);
		importStatusBar.setUndecorated(true);
		importStatusBar.setAlwaysOnTop(true);
		importStatusBar.setTitle("Import PhotoBox");
		}
		
		if (file.exists())
		{
			if (file.isDirectory())
			{
				fileList = imageManager.getImagesListInFolder(pathtoFile);
				if (fileList == null) 
				{
					throw new FileNotFoundException("Given folder is null.");
				}
				if (fileList.length==0) 
				{
					if (JOptionPane.showConfirmDialog(null, "The folder "+ pathtoFile + " is empty. Would you like to create a empty Photobox?","Question",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{
						CopyFilesManagerTask task = new CopyFilesManagerTask(fileList,getPhotoBoxWorkingDirectory()+name);
						task.addEventListener(importStatusBar);
						task.addEventListener(this);
						task.addPropertyChangeListener(importStatusBar);
						task.execute();
						importStatusBar.setModal(true);
						importStatusBar.setVisible(true);
						return new PhotoBox(getPhotoBoxWorkingDirectory()+name,name);
					}
					else
					{
						return null;
					}
				}
				else
				{
					CopyFilesManagerTask task = new CopyFilesManagerTask(fileList,getPhotoBoxWorkingDirectory()+name);
					
					task.addEventListener(importStatusBar);
					task.addEventListener(this);
					task.addPropertyChangeListener(importStatusBar);
					task.execute();
					importStatusBar.setModal(true);
					importStatusBar.setVisible(true);
					return createPhotoBox(getPhotoBoxWorkingDirectory()+name,name);
				
				}
			}
			else
			{
				File sourceFile = new File(pathtoFile);
				CopyFilesManagerTask task = new CopyFilesManagerTask(sourceFile, getPhotoBoxWorkingDirectory()+name);
				task.addEventListener(importStatusBar);
				task.addEventListener(this);
				task.addPropertyChangeListener(importStatusBar);
				task.execute();
				importStatusBar.setModal(true);
				importStatusBar.setVisible(true);
				return createPhotoBox(getPhotoBoxWorkingDirectory()+name,name);
			}
		}
		else
		{
			throw new FileNotFoundException("File " + file.getName() +" won't exists." );
		}
		
	}


	
	private PhotoBox createPhotoBox(String getAbsolutePath,String name) throws IllegalArgumentException, FileNotFoundException, ChoosenFileNotAFolderException
	{
		PhotoBox photobox = new PhotoBox(getAbsolutePath,name);
		ImageManager img = new ImageManager();
		File[] fileList=img.getImagesListInFolder(getAbsolutePath);
		if (fileList!=null)
		{
			if (fileList.length!=0)
			{
				for(File file:fileList)
				{
					photobox.addPhoto(new Photo(file.getName(),file.getAbsolutePath()));
				}
			}
		}
		return photobox;
	}
	
	/**
	 * This Method determine if the given paht is empty or null an throw an
	 * IllegalArgumentException in case.
	 * 
	 * @param path
	 * @throws IllegalArgumentException
	 */
	private void checkPath(String path) throws IllegalArgumentException {
		if (path.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		if (path == null)
			throw new IllegalArgumentException(ERROR_EMPTY_PATH);

	}

	@Override
	public void handleProgressStatusEvent(EventObject statusinformation) {
		ProgressStatusEvent event = (ProgressStatusEvent)statusinformation;
		if (event.getFinished())
		{
			sendFolderManagerMessage();
		}
		
	}

	@Override
	public void addFolderManagerObserver(FolderManagerObserver observer) {
		alObserverList.add(observer);
	}

	@Override
	public void removeFolderManagerObserver(FolderManagerObserver observer) {
		alObserverList.remove(observer);
	}

	@Override
	public void sendFolderManagerMessage() {
		for(FolderManagerObserver observer:alObserverList)
		{
			observer.importNewPhotoBox();
		}
	}

}
