package de.htw.hundertwasser.core;

import java.util.ArrayList;

/**
 * This class abstracts a real Photobox. It can keept a lot of Photos.
 * 
 * 
 * @author daniel
 *
 */
//TODO: PhotoBox testen; Name des Photoalbums festlegen. 
public class PhotoBox {

	private static final String ERROR_INDEX_OUT_OF_BOUNDS = "ID of a Photo needs to be within the current Photobox boundaries.";
	private static final String ERROR_ID_NEGATIVE = "ID of a Photo must be positiv and at least greater than -1";
	private static final int MIN_ID = 0;
	private static final String ERROR_PHOTO_NULL = "photo can't be null";
	private static final String ERROR_EMPTY_NAME = "name can't be empty.";
	private static final String ERROR_NAME_NULL = "name can't be null";
	private String name="";
	private ArrayList<Photo> arPhotos;
	/**
	 * Konstruktor
	 */
	public PhotoBox()
	{
		arPhotos= new ArrayList<Photo>();
	}
	/**
	 * Name of the  PhotoBox
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the Name of the Photobox
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name== null) throw new IllegalArgumentException(ERROR_NAME_NULL);
		if (name.trim().isEmpty())  throw new IllegalArgumentException(ERROR_EMPTY_NAME);
		this.name = name;
	}
	
	/**
	 * Add a Photo to the current Photobox
	 * @param photo
	 * @throws IllegalArgumentException
	 */
	public void addPhoto(Photo photo) throws IllegalArgumentException
	{
		if (photo==null) throw new IllegalArgumentException(ERROR_PHOTO_NULL);
		arPhotos.add(photo);
	}
	/**
	 * Returns the amount of Photos within the box.
	 * @return
	 */
	public int getCount()
	{
		return arPhotos.size();
	}
	
	/**
	 * Returns a Photo of the Photobox
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IndexOutOfBoundsException
	 */
	public Photo getPhoto(int id) throws IllegalArgumentException,IndexOutOfBoundsException
	{
		if (id<MIN_ID) throw new IllegalArgumentException(ERROR_ID_NEGATIVE);
		if (id>=arPhotos.size()) throw new IndexOutOfBoundsException(ERROR_INDEX_OUT_OF_BOUNDS);
		return arPhotos.get(id);
	}
	
	/**
	 * Remove the photo form a Photobox
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean removePhoto(int id) throws IllegalArgumentException
	{
		if (id<MIN_ID) throw new IllegalArgumentException(ERROR_ID_NEGATIVE);
		if (id>=arPhotos.size()) throw new IndexOutOfBoundsException(ERROR_INDEX_OUT_OF_BOUNDS);
		arPhotos.remove(id);
		return true;
	}
}
