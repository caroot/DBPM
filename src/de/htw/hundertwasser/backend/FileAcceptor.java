package de.htw.hundertwasser.backend;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Constructor that the Format pretends
 * @author Fabian Hewer
 */
public class FileAcceptor extends FileFilter implements FilenameFilter {
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "gif", "jpeg", "tiff", "png");
	private static String[] extensions = filter.getExtensions();

	/**
	 * Method, that says if a file is acceptable
	 * @param file, the File that is to test
	 * @return true, when acceptable
	 */
	public boolean accept(File file) {
		return filter.accept(file);
	}

	/**
	 * Method, that says if a file is acceptable
	 * @param file, the File that is to test
	 * @return true, when acceptable
	 */
	public boolean accept(File path, String name) {
		for (int i = 0; i < extensions.length; i++) {
			if (name.endsWith("." + extensions[i]))
				return true;
		}
		return false;
	}

	/**
	 * Method, that returns the Used FileNameExtensionFilter.
	 * @return filter
	 */
	public static FileNameExtensionFilter getFilter() {
		return filter;
	}

	/**
	 * Method, that returns the description of the user FileNameExtensionFilter
	 * @return String description
	 */
	public String getDescription() {
		return filter.getDescription();
	}

}
