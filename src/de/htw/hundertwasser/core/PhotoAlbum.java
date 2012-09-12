package de.htw.hundertwasser.core;

import java.util.HashMap;

/**
 * This class represents a PhotoAlbum
 * @author daniel
 *
 */
//TODO: Photoalbum testen; Name des Photoalbums festlegen. 
public class PhotoAlbum {

	private static final String ERROR_NO_STICKER = "The Sticker can't be null.";
	private static final String ERROR_NO_PHOTO = "Das Photo darf nicht null sein.";
	private String name="";
	private HashMap<Integer,PhotoAlbumEntry> hashPhotoAlbumEntry; 
	/**
	 * Konstruktor
	 */
	public PhotoAlbum()
	{
		hashPhotoAlbumEntry = new HashMap<Integer,PhotoAlbumEntry>();
	}
	
	
	/**
	 * Liefert den Namen des PhotoAlbums
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**
	 * Legt den Namen des PhotoAlbums fest.
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {		
		this.name = name;
	}



	

	public PhotoAlbumEntry getEntry(int id) {
		if (hashPhotoAlbumEntry.containsKey(id))
		{
			return hashPhotoAlbumEntry.get(id);
		}
		return null;
	}


	public void setEntry(PhotoAlbumEntry entry,int id)
	{
		
	}

	public void addEntry(PhotoAlbumEntry entry,int id) throws IllegalArgumentException
	{
		hashPhotoAlbumEntry.put(id,entry);
	}
	
	public void removeEntry(int id) throws IllegalArgumentException
	{
		hashPhotoAlbumEntry.remove(id);
	}
	
	public int getCountEntries()
	{
		return hashPhotoAlbumEntry.size();
	}
}
