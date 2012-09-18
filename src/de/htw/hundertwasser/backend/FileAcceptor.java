package de.htw.hundertwasser.backend;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FileAcceptor {
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Images", "jpg", "gif", "jpeg", "tiff", "png");
	
	public static boolean accept(File file) {
		return filter.accept(file);
	}
	
	public static FileNameExtensionFilter getFilter() {
		return filter;
	}
}
