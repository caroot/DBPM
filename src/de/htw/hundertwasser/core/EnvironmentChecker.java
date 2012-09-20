package de.htw.hundertwasser.core;

import java.util.Set;

/*
 * Class that checks the Environment
 * @autor fabian hewer 
 */
public class EnvironmentChecker {

	// Constans
	private static String OSNAME = "Windows";
	private static String JAVAVERSION = "1.6";

	/*
	 * Check what Java Runtime it is
	 * @return Java Runtime Version
	 */
	public String getJavaRuntimeVersion() {
		return System.getProperty("java.runtime.version");
	}

	/*
	 * Check what Operating System it is
	 * @return OperatingSystemName
	 */
	public String getCurrentOS() {
		return System.getProperty("os.name");
	}

	/*
	 * Check what Version the Operating System it is
	 * @return OperatingSystemVersion
	 */
	public String getCurrentOSVersion() {
		return System.getProperty("os.version");
	}

	/*
	 * Check what architecture the Operating System is
	 * @return OperatingSystemArchitectureVersion
	 */
	public String getCurrentOSArch() {
		return System.getProperty("os.arch");
	}

	/*
	 * Check if the Environment contains WINDOWS
	 * @return true if the OS contains WINDOWS, otherwise false
	 */
	public boolean isThisEnvironmentSuitable() {
		if (isOSCorrect()) {
			if (isRuntimeEnvironmentCorrect()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Function that checks if the CurrentOS contains WINDOWS
	 * @return OperatingSystemName
	 */
	public boolean isOSCorrect() {
		return (getCurrentOS().contains(OSNAME));
	}

	/*
	 * Check the RuntimeEnvironment
	 * @return JavaRuntimeVersion
	 */
	public boolean isRuntimeEnvironmentCorrect() {
		return (getJavaRuntimeVersion().contains(JAVAVERSION));
	}

	/*
	 * Check the PropertiesKeySet
	 * @return KeySet
	 */
	public Set<Object> getSystemPropertiesKeySet() {
		return System.getProperties().keySet();
	}

	/*
	 * Check the Property
	 * @return Property
	 */
	public String getProperty(String key) {
		return System.getProperty(key);
	}
}
