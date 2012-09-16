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
	private static final String ERROR_NO_PHOTO = "Das Photo can't be null.";
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

	/**
	 * Determine the entry of an Photoalbum
	 * @param entry
	 * @param id
	 */
	public void setEntry(PhotoAlbumEntry entry,int id) throws IllegalArgumentException
	{
	  hashPhotoAlbumEntry.put(id,entry);
	}
	
	/**
	 * Add an Entry to the Photoalbum
	 * @param entry
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public void addEntry(PhotoAlbumEntry entry,int id) throws IllegalArgumentException
	{
		hashPhotoAlbumEntry.put(id,entry);
	}
	
	/**
	 * Removes an Entry from the Photoalbum
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public void removeEntry(int id) throws IllegalArgumentException
	{
		hashPhotoAlbumEntry.remove(id);
	}
	
	/**
	 * Counts the Entries
	 * @return
	 */
	public int getCountEntries()
	{
		return hashPhotoAlbumEntry.size();
	}
}
