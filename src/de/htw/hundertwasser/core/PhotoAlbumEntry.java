package de.htw.hundertwasser.core;
/**
 * PhotoAlbumEntry beinhaltet einen Eintrag ins PhotoAlbum
 * Bestehend aus Text, einem Sticker und einem Photo.
 * @author daniel
 *
 */
//TODO:PhotoAlbumEntry testen.
public class PhotoAlbumEntry {
private Photo photo;
private String text;
private StickerEnummeration sticker;

private static final String ERROR_NO_STICKER = "The Sticker can't be null.";
private static final String ERROR_NO_PHOTO = "Das Photo darf nicht null sein.";

public Photo getPhoto() {
	return photo;
}
public void setPhoto(Photo photo) {
	this.photo = photo;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public StickerEnummeration getSticker() {
	return sticker;
}
public void setSticker(StickerEnummeration sticker) {
	this.sticker = sticker;
}


}
