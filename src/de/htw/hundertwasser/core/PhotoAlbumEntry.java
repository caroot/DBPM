package de.htw.hundertwasser.core;

/**
 * PhotoAlbumEntry includes an entry in the photo album Consisting of text, a
 * sticker and a photo.
 * 
 * @author daniel rhein
 * 
 */
// TODO:PhotoAlbumEntry testen.
public class PhotoAlbumEntry {
	// Variables
	private Photo photo;
	private String text;
	private StickerEnummeration sticker;
	// Constants
	private static final String ERROR_NO_STICKER = "The Sticker can't be null.";
	private static final String ERROR_NO_PHOTO = "Das Photo darf nicht null sein.";

	/*
	 * Function that returns the photo
	 * @return Photo
	 */
	public Photo getPhoto() {
		return photo;
	}

	/*
	 * Function that allow to set a photo
	 * @param photo
	 */
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/*
	 * Function that returns the Text
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/*
	 * Function that allow to set a Text
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * Function that returns the Sticker
	 * @return sticker
	 */
	public StickerEnummeration getSticker() {
		return sticker;
	}

	/*
	 * Function that allow to set a Sticker
	 */
	public void setSticker(StickerEnummeration sticker) {
		this.sticker = sticker;
	}

}
