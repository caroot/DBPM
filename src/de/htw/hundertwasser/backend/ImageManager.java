package de.htw.hundertwasser.backend;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

/**
 * This class will manage the Images of the current Project
 * 
 * @author daniel rhein
 * 
 */
public class ImageManager implements ImageObserver{

	private static final String ERROR_NO_FILE_PATH = "Der angegebene Pfad darf nicht null sein.";

	private static final String ERROR_EMPTY_PATH = "The path can't be empty.";
	private static final String ERROR_NULL_PATH = "The path can't be null.";

	public ImageManager() {

	}

	/**
	 * Read the Picture from source
	 * 
	 * @param absouluteFile absolute path to the file
	 * @return picture of the image.
	 * @throws IOException thrown if the Picture was not found
	 * @throws FileNotFoundException thrown if the Picture was not found
	 * @throws IllegalArgumentException thrown if the File is null
	 */
	public BufferedImage getImage(String absoluteFile) throws IOException,
			FileNotFoundException, InsufficientPrivilegesException {
		if (absoluteFile == null)
			throw new IllegalArgumentException(ERROR_NO_FILE_PATH);
		if (absoluteFile.trim().isEmpty())
			throw new IllegalArgumentException(
					"The path of the file can't be emtpy.");
		File file = new File(absoluteFile);
		if (!file.exists())
			throw new FileNotFoundException("File" + absoluteFile
					+ " can't be found on your System.");
		if (!file.canRead())
			throw new InsufficientPrivilegesException(
					"I'm not allowed to open the file" + absoluteFile);
		return ImageIO.read(file);
	}

	/**
	 * Read the Picture from source
	 * 
	 * @param absouluteFile absolute path to the file
	 * @return picture of the image.
	 * @throws IOException thrown if the Picture was not found
	 * @throws FileNotFoundException thrown if the Picture was not found
	 * @throws IllegalArgumentException thrown if the File is null
	 */
	public BufferedImage getThumNailImage(String absoluteFile,int width,int height) throws IOException,
			FileNotFoundException, InsufficientPrivilegesException {
		if (absoluteFile == null)
			throw new IllegalArgumentException(ERROR_NO_FILE_PATH);
		if (absoluteFile.trim().isEmpty())
			throw new IllegalArgumentException(
					"The path of the file can't be emtpy.");
		File file = new File(absoluteFile);
		if (!file.exists())
			throw new FileNotFoundException("File" + absoluteFile
					+ " can't be found on your System.");
		if (!file.canRead())
			throw new InsufficientPrivilegesException(
					"I'm not allowed to open the file" + absoluteFile);
		BufferedImage bui = ImageIO.read(file);
		BufferedImage thumbnail = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB );

		 Graphics2D g2 = (Graphics2D)thumbnail.getGraphics();;
		   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                        RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(bui, 0, 0, width, height, this);
		return thumbnail;
	}
	
	/**
	 * This method will return the ImageFileSize in the given ImageSize
	 * @param imgs ImageSize Megabyte,Kilobyte,GigaByte
	 * @param absolutePathToFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public double getSize(ImageManagerSize imgs,String absolutePathToFile) throws FileNotFoundException,IllegalArgumentException
	{
		if (imgs==null) throw new IllegalArgumentException("The given Image size can't be null.");
		if (absolutePathToFile==null) throw new IllegalArgumentException("The given path can't be null.");
		if (absolutePathToFile.trim().isEmpty()) throw new IllegalArgumentException("The given path can't be empty.");
		
		File f = new File(absolutePathToFile);
		if (!f.exists()) throw new FileNotFoundException("The given File" + absolutePathToFile + " doesn't exists.");
		long size = f.length();
		switch(imgs)
		{
		case GIGABYTE:
			return (size/(1024*1024*1024));
		case KILOBYTE:
			return (size/(1024));
		case MEGABYTE:
			return (size/(1024*1024));
		case BYTE:
			return size;
		default:
			return 0;
		}
	}
	/**
	 * Returns an ArrayList of ImageFiles within the current directory
	 * 
	 * @param Path
	 * @return
	 */
	public File[] getImagesListInFolder(String path)
			throws IllegalArgumentException, ChoosenFileNotAFolderException,
			FileNotFoundException {
		// TODO:Evtl. Rekursives auslesen notwendig.
		File imageList = null;
		FileAcceptor fileAcceptor = new FileAcceptor();
		if (path == null)
			throw new IllegalArgumentException(ERROR_NULL_PATH);
		if (path.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_PATH);

		imageList = new File(path);
		if (imageList.exists()) {
			if (imageList.isDirectory()) {
				return imageList.listFiles(fileAcceptor);
			} else {
				throw new ChoosenFileNotAFolderException("The given path "
						+ path + " is not a Folder.");
			}
		} else {
			throw new FileNotFoundException("The file" + path
					+ " doesn't exists.");
		}
	}

	public Date getModifiedDate(String pathToFile) throws IllegalArgumentException,FileNotFoundException
	{
		if (pathToFile == null) throw new IllegalArgumentException(ERROR_NULL_PATH);
		if (pathToFile.isEmpty()) throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		File file = new File(pathToFile);
		if (file.exists())
		{
			long tmp = file.lastModified();
			Date modified = new Date(tmp);
			return modified;
		}
		else
		{
			throw new FileNotFoundException("File " + pathToFile + " doesn't exist.");
		}
	}
	
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		return true;
	}
}
