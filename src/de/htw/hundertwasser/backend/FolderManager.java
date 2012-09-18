package de.htw.hundertwasser.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import de.htw.hundertwasser.core.EnvironmentChecker;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolder;

/**
 * This class will manage the Folders of the current Project
 * 
 * @author daniel
 *
 */
public class FolderManager {

	private EnvironmentChecker environmentChecker;
	
	private static final String ERROR_EMPTY_PATH = "The path can't be empty.";
	private static final String ERROR_NULL_PATH = "The path can't be null.";
	
	public FolderManager()
	{
		environmentChecker = new EnvironmentChecker();
		
	}
	/**
	 * Show the Application path
	 * @return
	 */
	public String getApplicationPath()
	{
		return System.getenv("APPDATA");
	}
	
	
	public Set<String> getEnvironmentKeyValues()
	{
		Map<String,String> keymap =System.getenv();
		return 	keymap.keySet();
	}
	
	public String getEnvironmentKeyValue(String key)
	{
		return System.getenv(key);
	}
	
	public String getUsersHomePath()
	{
		return environmentChecker.getProperty("user.home");
	}
	
	/**
	 * Returns an File-List of Folders within the current path
	 * @param path,absolute path to the file
	 * @return list of Files
	 */
	public File[] getFolderList(String path) throws IllegalArgumentException, ChoosenFileNotAFolder,FileNotFoundException
	{
		File directory =null;
		if (path==null) throw new IllegalArgumentException(ERROR_NULL_PATH);
		if (path.trim().isEmpty()) throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		
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
			 throw new ChoosenFileNotAFolder("Your path "+ path + " is not a folder.");
		 }
		}
		else
		{
			throw new FileNotFoundException("Your path " + path + " doesn't exists.");
		}
	}
	
	/**
	 * ImportPhotobox to the current working directory.
	 * @param name
	 * @param pathtoFile
	 * @return
	 */
	public PhotoBox importPhotoBox(String name,String pathtoFile) 
	{
		return null;
	}
}
