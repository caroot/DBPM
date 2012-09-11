package de.htw.hundertwasser.backend;

import java.util.Map;
import java.util.Set;

import de.htw.hundertwasser.core.EnvironmentChecker;

/**
 * This class will manage the Folders of the current Project
 * 
 * @author daniel
 *
 */
public class FolderManager {

	private EnvironmentChecker environmentChecker;
	
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
}
