package de.htw.test;

import de.htw.hundertwasser.backend.FolderManager;

public class PathTest {

	public static void main(String[] args) {
		FolderManager manager = new FolderManager();
		String path = manager.getApplicationPath();
		System.out.println(path);
	}
}
