package de.htw.hundertwasser.core;

import java.util.ArrayList;

/**
 * This class represents a PhotoAlbum
 * @author daniel
 *
 */
public class PhotoAlbum {

	private static final String ERROR_NO_STICKER = "The Sticker can't be null.";
	private static final String ERROR_NO_PHOTO = "Das Photo darf nicht null sein.";
	private String name;
	private ArrayList<Photo> arrayList;
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



	public ArrayList<Photo> getPhotoArrayList() {
		return arrayList;
	}

	public ArrayList<StickerEnummeration> getStickerArrayList()
	{
		return arrayListSticker;
	}


	public void addPhoto(Photo photo) throws IllegalArgumentException
	{
		if (photo==null) throw new IllegalArgumentException(ERROR_NO_PHOTO);
		arrayList.add(photo);
	}
	
	public void removePhoto(Photo photo) throws IllegalArgumentException
	{
		if (photo==null) throw new IllegalArgumentException(ERROR_NO_PHOTO);
		arrayList.remove(photo);
	}
	
	public void addSticker(StickerEnummeration sticker) throws IllegalArgumentException
	{
		if (sticker==null) throw new IllegalArgumentException(ERROR_NO_STICKER);
		arrayListSticker.add(sticker);
	}
	
	public void removeSticker(StickerEnummeration sticker) throws IllegalArgumentException
	{
		if (sticker==null) throw new IllegalArgumentException(ERROR_NO_PHOTO);
		arrayListSticker.remove(sticker);
	}
}
