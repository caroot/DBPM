package de.htw.hundertwasser.custom.event;

import java.util.ArrayList;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;

/**
 * This Class will be used for the SplashScreen at startup.
 * It will Load an Preload Elements of the SplashScreen.
 * @author daniel
 *
 */
public class ProgressStartUpEvent extends ProgressStatusEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<PhotoBox> photoboxList=null;
	private ArrayList<PhotoAlbum> photoAlbumList=null;
	private boolean bIntegrity = false;
	private boolean bRunApplication = false;
	
	/**
	 * Constructor
	 * @param source
	 */
	public ProgressStartUpEvent(Object source) {
		  super(source);
     }

	/**
	 * Returns the Photoboxlist
	 * @return
	 */
	public ArrayList<PhotoBox> getPhotoboxList() {
		return photoboxList;
	}


	/**
	 * Determine the list of Photoboxes
	 * @param photoboxList
	 */
	public void setPhotoboxList(ArrayList<PhotoBox> photoboxList) {
		this.photoboxList = photoboxList;
	}


	/**
	 * Return the PhotoAlbumList
	 * @return
	 */
	public ArrayList<PhotoAlbum> getPhotoAlbumList() {
		return photoAlbumList;
	}

	/**
	 * Set the PhotoAlbumList
	 * @param photoAlbumList
	 */
	public void setPhotoAlbumList(ArrayList<PhotoAlbum> photoAlbumList) {
		this.photoAlbumList = photoAlbumList;
	}
	/**
	 * The StartScreen-Applikation could be started or not
	 * @param bRunApplication
	 */
	public void setRunApplikation(boolean bRunApplication)
	{
		this.bRunApplication = bRunApplication;
	}
	
	/**
	 * Determine if every setting is ok or not.
	 * @param bIntegrityCheck
	 */
	public void setIntegrityCheck(boolean bIntegrityCheck)
	{
		this.bIntegrity = bIntegrityCheck;		
	}
	
	/**
	 * This Function show, if the StartScreenApplication could be started or not.
	 * @return
	 */
	public boolean isRunApplication()
	{
		return bRunApplication;
	}
	/**
	 * This Function show, if the 	integrity is proofed.
	 * @return
	 */
	public boolean isIntegrityCheck()
	{
		return bIntegrity;
	}
}
