package de.htw.hundertwasser.core;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import de.htw.hundertwasser.backend.ImageManager;
import de.htw.hundertwasser.backend.ImageManagerSize;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

/**
 * This class abstracts a Photo There should be only the path to the file to
 * display it. The display itself is realized with another object to the Image
 * Manager.
 * 
 * @author daniel rhein
 * 
 */
// TODO:Photo-Testen.
public class Photo {
	/**
	 * This error message occurs if the name is null.
	 */
	public static final String ERROR_NO_NAME = "You need to set a name. It can't be null.";
	/**
	 * This error message occurs if the path is null.
	 */
	public static final String ERROR_NO_PATH = "You need to set a path. It can't be null.";
	/**
	 * This error message occurs if the name is empty.
	 */
	public static final String ERROR_EMPTY_NAME = "You need to set a name. It can't be empty.";
	/**
	 * This error message occurs if the paht is empty.
	 */
	public static final String ERROR_EMPTY_PATH = "You need to set a path. It can't be empty.";

	/**
	 * This is an instance of the ImageManager he'll keeps up the loading of
	 * Images by their absolute Filepath.
	 */
	private ImageManager manager;
	/**
	 * This is the Path to the file. It will reference where the Image is kept
	 * on the HDD.
	 */
	private String pathToFile = "";
	/**
	 * This is the name of the Photo. It will be referenced by this variable.
	 */
	private String name = "";
	/**
	 * This is the Image of a Photo. Once if it is read form the HDD it will be
	 * kept in this variable.<\p> And it will only reread if the developer will
	 * reread it.
	 */
	private BufferedImage m_image = null;

	/**
	 * This is the comment for the currrent photo.
	 */
	private String comment = "";

	/**
	 * 
	 */
	private int photoBoxId = 0;

	/**
	 * Return the Path to the given File.
	 * 
	 * @return path to File
	 */
	public String getPathToFile() {

		return pathToFile;
	}

	/**
	 * Create a new Photo
	 * 
	 * @param name
	 * @param absolutepath
	 * @throws IllegalArgumentException
	 */
	public Photo(String name, String absolutePath)
			throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException(ERROR_NO_NAME);
		if (absolutePath == null)
			throw new IllegalArgumentException(ERROR_NO_PATH);
		if (name.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_NAME);
		if (absolutePath.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		manager = new ImageManager();
		this.setName(name);
		this.setPathToFile(absolutePath);
	}

	/**
	 * This will set the Path to the file.
	 * 
	 * @param pathToFile
	 */
	private void setPathToFile(String pathToFile) {
		if (name == null)
			throw new IllegalArgumentException(ERROR_NO_PATH);
		if (name.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		this.pathToFile = pathToFile;
	}

	/**
	 * Determine which PhotoBox should be refrenced
	 * 
	 * @return photoboxid
	 */
	public int getPhotoBoxId() {
		return photoBoxId;
	}

	/**
	 * Determine the PhotoBoxId
	 * 
	 * @param photoBoxId
	 */
	public void setPhotoBoxId(int photoBoxId) {
		this.photoBoxId = photoBoxId;
	}

	/**
	 * This will return the name of the Photobook.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This will set the name of the Photobox.</p> If you like to avoid
	 * Exceptions than ensure that the name is not null or empty.
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException(ERROR_NO_NAME);
		if (name.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_EMPTY_NAME);
		this.name = name;
	}

	/**
	 * This determine if the image is already read from the HDD.
	 * 
	 * @return Null if there's not an Image set,  otherwise true
	 */
	public boolean isImageSet() {
		return m_image == null;
	}

	/**
	 * Deliver the file from HDD. If it's already set it will return the content
	 * of bufferedImage.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 *             if the file not exists
	 * @throws IOException
	 *             if the file can't be written
	 * @throws InsufficientPrivilegesException
	 *             if you have insufficient privilege for the file.
	 */
	public BufferedImage getImage() throws FileNotFoundException, IOException,
			InsufficientPrivilegesException {
		if (m_image == null) {
			m_image = manager.getImage(pathToFile);
		}
		return m_image;
	}

	/**
	 * Sometimes it will be necessary to reload the image. </p> this could be
	 * done by this function.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InsufficientPrivilegesException
	 */
	public void reloadImage() throws FileNotFoundException, IOException,
			InsufficientPrivilegesException {
		m_image = manager.getImage(pathToFile);
	}

	/**
	 * This will set free the Image and it's content.
	 */
	public void wipeout() {
		if (m_image != null)
			m_image = null;

	}

	/**
	 * Set the Comment of the Picture
	 * 
	 * @return comment of the Picture
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method will return the size of the Image
	 * @param imgs
	 * @return
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public double getSize(ImageManagerSize imgs) throws FileNotFoundException, IllegalArgumentException
	{
		return manager.getSize(imgs, getPathToFile());
	}
	
	public Date getLastModifiedDate() throws FileNotFoundException,IllegalArgumentException
	{
		return manager.getModifiedDate(getPathToFile());
	}
	
	/**
	 * Set the Comment of the Picuture. It is allow to save empty comments.
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
