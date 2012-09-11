package de.htw.hundertwasser.backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

/**
 * This class will mange the Images of the current Project
 * @author daniel
 *
 */
public class ImageManager {

	private static final String ERROR_NO_FILE_PATH = "Der angegebene Pfad darf nicht null sein.";

	public ImageManager()
	{
		
	}
	
	/**
	 * Lese das Bild aus einer Datei
	 * @param absouluteFile Der absoulute Pfad zur Datei.
	 * @return picture of the image.
	 * @throws IOException Wird geworfen wenn das Bild nicht gefunden werden kann.
	 * @throws FileNotFoundException Wird geworfen wenn das Bild nicht gefunden werden kann.
	 * @throws IllegalArgumentException Wenn die Datei leer oder null ist.
	 */
	public BufferedImage getImage(String absoluteFile) throws IOException,FileNotFoundException,InsufficientPrivilegesException
	{
		if (absoluteFile== null) throw new IllegalArgumentException(ERROR_NO_FILE_PATH);
		if (absoluteFile.trim().isEmpty()) throw new IllegalArgumentException("Der Pfad zur Datei darf nicht leer sein.");
		File file = new File(absoluteFile);
		if (!file.exists()) throw new FileNotFoundException("File" + absoluteFile + " can't be found on your System.");
		if (!file.canRead()) throw new InsufficientPrivilegesException("I'm not allowed to open the file" + absoluteFile);
		return ImageIO.read(file);
	}
}
