package de.htw.hundertwasser.core;

import java.util.ArrayList;

/**
 * This class represents a PhotoAlbum
 * @author daniel
 *
 */
//TODO Das PhotoAlbum kann Photos speichern
//TODO Jedem Photo kann ein Text und Sticker zugeordnet werden.
//TODO Ein Eintrag ins PhotoAlbum besteht aus einem Text,einem Sticker und einem Photo.
//TODO DIES MUSS HIER ABGEBILDET WERDEN.
public class PhotoAlbum {

	private static final String ERROR_NO_STICKER = "The Sticker can't be null.";
	private static final String ERROR_NO_PHOTO = "Das Photo darf nicht null sein.";
	private String name;
	private ArrayList<PhotoAlbumEntry> arrayList;
	private ArrayList<StickerEnummeration> arrayListSticker;
	/**
	 * Konstruktor
	 */
	public PhotoAlbum()
	{
		
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



	

	public ArrayList<PhotoAlbumEntry> getArrayList() {
		return arrayList;
	}


	public void setEntry(PhotoAlbumEntry entry,int id)
	{
		
	}

	public void addEntry(PhotoAlbumEntry entry) throws IllegalArgumentException
	{
		if (entry==null) throw new IllegalArgumentException(ERROR_NO_PHOTO);
		arrayList.add(entry);
	}
	
	public void removeEntry(PhotoAlbumEntry entry) throws IllegalArgumentException
	{
		if (entry==null) throw new IllegalArgumentException(ERROR_NO_PHOTO);
		arrayList.remove(entry);
	}
	
	
}
