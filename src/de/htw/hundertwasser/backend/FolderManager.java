package de.htw.hundertwasser.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import de.htw.hundertwasser.core.EnvironmentChecker;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.custom.error.CantCreateDirectoryException;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;

/**
 * This class will manage the Folders of the current Project
 * 
 * @author daniel rhein
 *
 */
public class FolderManager {

	private static final String ERROR_NAME_EMPTY = "Name can't be empty";

	private static final String ERROR_NAME_NULL = "Name can't be null.";

	private EnvironmentChecker environmentChecker;
	
	private static final String ERROR_EMPTY_PATH = "The path can't be empty.";
	private static final String ERROR_NULL_PATH = "The path can't be null.";
	
	private static final String DPBM_WORKINGDIRECTORY="Dunkelbunt-PhotoManager"+File.separatorChar;
	
	public FolderManager()
	{
		environmentChecker = new EnvironmentChecker();
		
	}
	/**
	 * Show the Application path
	 * @return returns the Application Path
	 */
	public String getApplicationPath()
	{
		return System.getenv("APPDATA");
	}
	
	/**
	 * This method allows to determine if a Folder exists or not.
	 * @param path
	 * @return true if the given path is a existing folder, false otherwise.
	 * @throws IllegalArgumentException if the path is emtpy or null
	 */
	private boolean isFolderExists(String path) throws IllegalArgumentException
	{
		checkPath(path);
		File directory = new File(path);
	    if (directory.isDirectory())
	    {
	    	if (directory.exists())
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    return false;
	}
	
	/**
	 * This method returns the working directory.</br> 
	 * This is the ApplicationPath concatenated with the name of our Programm. </br>
	 * <b>Attention:</b></br>
	 * If the working directory won't exist this Method tries to create a Directory.</br>
	 * If the creation of the working directory fails, you'll receive an <b>CantCreateDirectoryExceptoin</b></p>
	 * @return String with the current working directory.
	 * @throws CantCreateDirectoryException it will be thrown if it's not able to create the directory.
	 */
	public String getWorkingDirectory() throws CantCreateDirectoryException
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getApplicationPath());
		sb.append(DPBM_WORKINGDIRECTORY);
		if (!isFolderExists(sb.toString()))
		{
			if (createDirectories(sb.toString()))
			{
				return sb.toString();
			}
			else
			{
				throw new CantCreateDirectoryException("Can't create Directory " + sb.toString());
				
			}
		}
		else
		{
			return sb.toString();
		}
		
		
	}
	
	/**
	 * This method will create the directories
	 * @param path
	 * @return 
	 */
	private boolean createDirectories(String path) throws IllegalArgumentException
	{
		checkPath(path);
		File directory = new File(path);
		return directory.mkdir();
	}
	
	/**
	 * This method will return a Set of the complete Environment-Keys.
	 * @return
	 */
	public Set<String> getEnvironmentKeyValues()
	{
		Map<String,String> keymap =System.getenv();
		return 	keymap.keySet();
	}
	
	/**
	 * This method allows you to read the local environment with a ceartain key.
	 * @param key
	 * @return
	 */
	public String getEnvironmentKeyValue(String key)
	{
		return System.getenv(key);
	}
	/**
	 * This method will return the users home-path if it's set.
	 * @return
	 */
	public String getUsersHomePath()
	{
		return environmentChecker.getProperty("user.home");
	}
	
	/**
	 * Returns an File-List of Folders within the current path
	 * @param path,absolute path to the file
	 * @return list of Files
	 * @throws IllegalArgumentException,ChoosenFileNotAFolderException,FileNotFoundException
	 */
	public File[] getFolderList(String path) throws IllegalArgumentException, ChoosenFileNotAFolderException,FileNotFoundException
	{
		File directory =null;
		checkPath(path);	
		if (path.endsWith("."))
		{
			directory = new File(path);
		}
		else
		{
			directory = new File(path+".");
		}
		if (directory.exists())
		{
		 if (directory.isDirectory())
		 {
			 return directory.listFiles();
		 }
		 else
		 {
			 throw new ChoosenFileNotAFolderException("Your path "+ path + " is not a folder.");
		 }
		}
		else
		{
			throw new FileNotFoundException("Your path " + path + " doesn't exists.");
		}
	}
	
	/**
	 * ImportPhotobox and it's content to the current working directory.
	 * @param name Name of the Photobox
	 * @param pathtoFile this is a ImageFile or a Directory
	 * @return The created Photobox
	 * @throws FileNotFoundException 
	 */
	public PhotoBox importPhotoBox(String name,String pathtoFile) throws FileNotFoundException 
	{
		checkPath(pathtoFile);
		if (name.trim().isEmpty()) throw new IllegalArgumentException(ERROR_NAME_EMPTY);
		if (name==null) throw new IllegalArgumentException(ERROR_NAME_NULL);
		File file = new File(pathtoFile);
		if (file.exists())
		{
			if (file.isDirectory())
			{
				
			}
			else
			{
				
			}
		}
		else
		{
			throw new FileNotFoundException("File " + file.getName() +" won't exists." );
		}
		PhotoBox photobox = new PhotoBox(pathtoFile);
		return null;
	}
	
	/**
	 * This Method determine if the given paht is empty or null an throw an IllegalArgumentException in case. 
	 * @param path
	 * @throws IllegalArgumentException
	 */
	private void checkPath(String path) throws IllegalArgumentException
	{
		if (path.trim().isEmpty()) throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		if (path==null) throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		
	}
}
