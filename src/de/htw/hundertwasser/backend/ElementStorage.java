package de.htw.hundertwasser.backend;

import java.util.ArrayList;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;

/**
 * This Class stores PhotoBox- and PhotoAlbum List, to be referenced from outside
 * @author Fabian Hewer
 *
 */
public class ElementStorage {
	
	//Error Constants
	public static final String NO_LIST = "There is no list.";
	public static final String NO_BOX = "This Photobox was not found.";
	public static final String NAME_TAKEN_BOX = "There is already a Photobox with that name";
	public static final String NO_ALBUM = "This Photoalbum was not found.";
	public static final String NAME_TAKEN_ALBUM = "There is already a Photoalbum with that name";

	//Storage Lists
	private static ArrayList<PhotoBox> boxList;
	private static ArrayList<PhotoAlbum> albumList;
	
	/**
	 * Method, that adds a PhotoBox to the List
	 * @param box the PhotoBox
	 */
	public static void addPhotoBox(PhotoBox box) {
		if(boxList == null) {
			boxList = new ArrayList<PhotoBox>();
		} else {
			for(int i = 0; i < boxList.size(); i++) {
				if(boxList.get(i).getName().equals(box.getName())) {
					throw new IllegalArgumentException(NAME_TAKEN_BOX);
				}
			}
		}
		boxList.add(box);
	}
	
	/**
	 * Method, that removes a PhotoBox from The list, referenced by it's name
	 * @param name The Name of the PhotoBox that is to delete
	 * @return false, if the element was not found.
	 */
	public static boolean removePhotoBox(String name) {
		if(boxList == null) {
			ErrorMessageDialog.showMessage(null, NO_LIST);
			return false;
		}
		for(int i = 0; i < boxList.size(); i++) {
			if(boxList.get(i).getName().equals(name)) {
				boxList.remove(i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method, that removes a PhotoBox from The list, referenced by the Object
	 * @param name The Name of the PhotoBox that is to delete
	 * @return false, if the element was not found.
	 */
	public static boolean removePhotoBox(PhotoBox box) {
		if(boxList == null) {
			ErrorMessageDialog.showMessage(null, NO_LIST);
			return false;
		}
		if(!boxList.remove(box)) {
			ErrorMessageDialog.showMessage(null, NO_BOX);
			return false;
		}
		return true;
	}
	
	/**
	 * Method, that returns a PhotoBox referenced by it's name
	 * @param name the name of the PhotoBox
	 * @return the PhotoBox
	 */
	public static PhotoBox getPhotoBox(String name) {
		if(boxList == null) {
			throw new IllegalArgumentException(NO_LIST);
		}
		for(int i = 0; i < boxList.size(); i++) {
			if(boxList.get(i).getName().equals(name)) {
				return boxList.get(i);
			}
		}
		throw new IllegalArgumentException(NO_BOX);
	}
	
	/**
	 * Method that returns the Number of PhotoBoxes
	 * @return the Number
	 */
	public static int getBoxNumber() {
		if(boxList == null) {
			return 0;
		}
		return boxList.size();
	}
	
	/**
	 * Method, that adds a PhotoAlbum to the List
	 * @param album the PhotoAlbum
	 */
	public static void addPhotoAlbum(PhotoAlbum album) {
		if(albumList == null) {
			albumList = new ArrayList<PhotoAlbum>();
		} else {
			for(int i = 0; i < albumList.size(); i++) {
				if(albumList.get(i).getName().equals(album.getName())) {
					throw new IllegalArgumentException(NAME_TAKEN_ALBUM);
				}
			}
		}
		albumList.add(album);
	}
	
	/**
	 * Method, that removes a PhotoAlbum from The list, referenced by it's name
	 * @param name The Name of the PhotoAlbum that is to delete
	 * @return false, if the element was not found.
	 */
	public static boolean removePhotoAlbum(String name) {
		if(albumList == null) {
			ErrorMessageDialog.showMessage(null, NO_LIST);
			return false;
		}
		for(int i = 0; i < albumList.size(); i++) {
			if(albumList.get(i).getName().equals(name)) {
				albumList.remove(i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method, that removes a PhotoAlbum from The list, referenced by the Object
	 * @param name The Name of the PhotoAlbum that is to delete
	 * @return false, if the element was not found.
	 */
	public static boolean removePhotoAlbum(PhotoAlbum album) {
		if(albumList == null) {
			ErrorMessageDialog.showMessage(null, NO_LIST);
			return false;
		}
		if(!albumList.remove(album)) {
			ErrorMessageDialog.showMessage(null, NO_ALBUM);
			return false;
		}
		return true;
	}
	
	/**
	 * Method, that returns a PhotoAlbum referenced by it's name
	 * @param name the name of the PhotoAlbum
	 * @return the PhotoAlbum
	 */
	public static PhotoAlbum getPhotoAlbum(String name) {
		if(albumList == null) {
			throw new IllegalArgumentException(NO_LIST);
		}
		for(int i = 0; i < albumList.size(); i++) {
			if(albumList.get(i).getName().equals(name)) {
				return albumList.get(i);
			}
		}
		throw new IllegalArgumentException(NO_ALBUM);
	}
	
	/**
	 * Method that returns the Number of PhotoAlbums
	 * @return the Number
	 */
	public static int getAlbumNumber() {
		if(albumList == null) {
			return 0;
		}
		return albumList.size();
	}

	/**
	 * This method returns the boxlist in order to display it in the navbar
	 * 
	 * @return boxlist
	 */
	public static ArrayList<PhotoBox> getBoxList() {
		return boxList;
	}

	/**
	 * This method returns the albumlist in order to display it in the navbar
	 * 
	 * @return albumlist
	 */
	public static ArrayList<PhotoAlbum> getAlbumList() {
		return albumList;
	}

}