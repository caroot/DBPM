package de.htw.hundertwasser.backend;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Constructor that the Format pretends
 * @author daniel rhein
 * @return File that was selected 
 */
public class FileAcceptor extends FileFilter implements FilenameFilter {
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "gif", "jpeg", "tiff", "png");
	private static String[] extensions = filter.getExtensions();

	public boolean accept(File file) {
		return filter.accept(file);
	}

	@Override
	public boolean accept(File path, String name) {
		for (int i = 0; i < extensions.length; i++) {
			if (name.endsWith("." + extensions[i]))
				return true;
		}
		return false;
	}

	/*
	 * Function that the filter returns
	 * @return filter
	 */
	public static FileNameExtensionFilter getFilter() {
		return filter;
	}

	@Override
	public String getDescription() {
		return filter.getDescription();
	}

}
