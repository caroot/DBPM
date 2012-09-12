package de.htw.hundertwasser.core;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.htw.hundertwasser.backend.ImageManager;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;

/**
 * Diese Klasse abstrahiert ein  Photo
 * Es muss nur den Pfad zur Datei kennen die es Anzeigen soll.
 * Die Anzeige selbst wird mit einem anderen Objekt dem ImageManager realisiert.
 * @author daniel
 *
 */
public class Photo {
	/**
	 * This error message occurs if the name is null.
	 */
	private static final String ERROR_NO_NAME ="You need to set a name. It can't be null.";
	/**
	 * This error message occurs if the path is null.
	 */
	private static final String ERROR_NO_PATH ="You need to set a path. It can't be null.";
	/**
	 * This error message occurs if the name is empty.
	 */
	private static final String ERROR_EMPTY_NAME ="You need to set a name. It can't be empty.";
	/**
	 * This error message occurs if the paht is empty.
	 */
	private static final String ERROR_EMPTY_PATH ="You need to set a path. It can't be empty.";
	
	/**
	 * This is an instance of the ImageManager he'll keeps up the loading of Images by their absolute Filepath.
	 */
	private ImageManager manager;
	/**
	 * This is the Path to the file. It will reference where the Image is kept on the HDD.
	 */
	private String pathToFile = "";
	/**
	 * This is the name of the Photo. It will be referenced by this variable. 
	 */
	private String name="";
	/**
	 * This is the Image of a Photo. Once if it is read form the HDD it will be kept in this variable.<\p>
	 * And it will only reread if the developer will reread it.
	 */
	private BufferedImage m_image = null;
	
	/**
	 * This is the comment for the currrent photo.
	 */
	private String comment ="";
	
	/**
	 * Return the Path to the given File.
	 * @return
	 */
	public String getPathToFile() {
		
		return pathToFile;
	}



	/**
	 * This will set the Path to the file.
	 * @param pathToFile
	 */
	public void setPathToFile(String pathToFile) {
		if (name==null) throw new IllegalArgumentException(ERROR_NO_PATH);
		if (name.trim().isEmpty()) throw new IllegalArgumentException(ERROR_EMPTY_PATH);
		this.pathToFile = pathToFile;
	}



/**
 * This will return the name of the Photobook.
 * @return
 */
	public String getName() {
		return name;
	}



/**
 * This will set the name of the Photobox.</p>
 * If you like to avoid Exceptions than ensure that the name is not null or empty. 
 * @param name
 * @throws IllegalArgumentException
 */
	public void setName(String name) throws IllegalArgumentException{
		if (name==null) throw new IllegalArgumentException(ERROR_NO_NAME);
		if (name.trim().isEmpty()) throw new IllegalArgumentException(ERROR_EMPTY_NAME);
		this.name = name;
	}

	
	/**
	 * This determine if the image is already read from the HDD.
	 * @return
	 */
	public boolean isImageSet()
	{
		return m_image==null;
	}

/**
 * Deliver the file from HDD. If it's already set it will return the content of bufferedImage.
 * @return
 * @throws FileNotFoundException if the file not exists
 * @throws IOException if the file can't be written
 * @throws InsufficientPrivilegesException if you have insufficient privilege for the file.
 */
	public BufferedImage getImage() throws FileNotFoundException, IOException, InsufficientPrivilegesException
	{
		if (m_image==null) 
		{		
			m_image=manager.getImage(pathToFile);
		}
		return m_image;
	}
	
	/**
	 * Sometimes it will be necessary to reload the image. </p>
	 * this could be done by this function.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InsufficientPrivilegesException
	 */
	public void reloadImage() throws FileNotFoundException, IOException, InsufficientPrivilegesException
	{	
			m_image=manager.getImage(pathToFile);
	}
	
	/**
	 * This will set free the Image and it's content.
	 */
	public void wipeout()
	{
		if (m_image!=null) m_image=null;
		
	}


	/**
	 * Set the Comment of the Picture
	 * @return
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * Set the Comment  of the Picuture. It is allow to save empty comments.
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
