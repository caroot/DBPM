package de.htw.hundertwasser.core;

import java.util.Set;

public class EnvironmentChecker {

	
	private static String OSNAME="Windows";
    private static String JAVAVERSION="1.6";
	public String getJavaRuntimeVersion()
	{
		return System.getProperty("java.runtime.version");
	}
	
	public String getCurrentOS()
	{
		return System.getProperty("os.name");
	}
	
	public String getCurrentOSVersion()
	{
		return System.getProperty("os.version");
	}
	
	public String getCurrentOSArch()
	{
		return System.getProperty("os.arch");
	}
	
	public boolean isThisEnvironmentSuitable()
	{
	   if (isOSCorrect())
	   {
		   if (isRuntimeEnvironmentCorrect())
		   {
			   return true;
		   }
	   }
		return false;	 
	}
	
	public boolean isOSCorrect()
	{
		return (getCurrentOS().contains(OSNAME));
	}
	
	public boolean isRuntimeEnvironmentCorrect()
	{
		return (getJavaRuntimeVersion().contains(JAVAVERSION));
	}
	
	public Set<Object> getSystemPropertiesKeySet()
	{
		return System.getProperties().keySet();
	}
	
	public String getProperty(String key)
	{
		return System.getProperty(key);
	}
}
