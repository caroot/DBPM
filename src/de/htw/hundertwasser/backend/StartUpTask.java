package de.htw.hundertwasser.backend;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingWorker;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.core.interfaces.ProgressStatusEventListener;
import de.htw.hundertwasser.custom.error.CantCreateDirectoryException;
import de.htw.hundertwasser.custom.error.ChoosenFileNotAFolderException;
import de.htw.hundertwasser.custom.event.ProgressStartUpEvent;
import de.htw.hundertwasser.custom.event.ProgressStatusEvent;

public class StartUpTask extends SwingWorker<Void,Void>{

	private static final String STATUS_FINISHED = "Process has end sucessfully.";
	private FolderManager folderManager = new FolderManager();
	/**
	 * An set of Progress status Listeners
	 */
	private ArrayList<ProgressStatusEventListener> eventlisteners = new ArrayList<ProgressStatusEventListener>();
	@Override
	protected Void doInBackground() throws Exception {
		try {
		setProgress(0);//Wait for startup
		Thread.sleep(1000);
		fireEvent("System going to startup");
		setProgress(1);
		Thread.sleep(1000);
		fireEvent("Search Photobox.");
		ArrayList<PhotoBox> arphotoboxArrayList;
			arphotoboxArrayList = folderManager.readCurrentWorkingDirectoryPhotoBox();
			firePhotoBoxEvent(arphotoboxArrayList);
			Thread.sleep(1000);
		setProgress(25);
			//Read the PhotoAlbums and collect them
			fireEvent("PhotoAlbum collected");
			Thread.sleep(1000);
		setProgress(50);
			fireEvent("Integrity Check will start");
			Thread.sleep(1000);
		setProgress(75);
			//Everything is allright
			fireEvent("Integrity Check running");
			fireEventIntegrety(true);
			Thread.sleep(1000);
		setProgress(100);
			fireEvent(true);
			fireEvent("Everything is all right, application will start up soon.");
			fireEventRunApplication(true);
			Thread.sleep(1000);
		} catch (ChoosenFileNotAFolderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CantCreateDirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private synchronized void firePhotoBoxEvent(ArrayList<PhotoBox> arListPhotoBox)
	{
		ProgressStartUpEvent event = new ProgressStartUpEvent(this);
	  	  event.setPhotoboxList(arListPhotoBox);
	  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
	  	  while(iterator.hasNext())
	  	  {
	  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
	  	  }
	}
	
	private synchronized void firePhotoAlbumEvent(ArrayList<PhotoAlbum> arListPhotoBox)
	{
		ProgressStartUpEvent event = new ProgressStartUpEvent(this);
	  	  event.setPhotoAlbumList(arListPhotoBox);
	  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
	  	  while(iterator.hasNext())
	  	  {
	  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
	  	  }
	}
	
	private synchronized void fireEventIntegrety(boolean bIntegretyCheck)
	{
		ProgressStartUpEvent event = new ProgressStartUpEvent(this);
	  	  event.setIntegrityCheck(bIntegretyCheck);
	  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
	  	  while(iterator.hasNext())
	  	  {
	  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
	  	  }
	}
	
	private synchronized void fireEventRunApplication(boolean bRunApplication)
	{
		ProgressStartUpEvent event = new ProgressStartUpEvent(this);
	  	  event.setRunApplikation(bRunApplication);
	  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
	  	  while(iterator.hasNext())
	  	  {
	  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
	  	  }
	}
	
	public synchronized void addEventListener(ProgressStatusEventListener listener)
    {
  	  eventlisteners.add(listener);
    }
    
    public synchronized void removeEventListener(ProgressStatusEventListener listener)
    {
  	  eventlisteners.remove(listener);
    }
    
    /**
     * This method will transmit the status information
     * @param statusInformation information about the current status
     */
    private synchronized void fireEvent(String statusInformation)
    {
    	ProgressStatusEvent event = new ProgressStatusEvent(this);
  	  event.setStatusInformation(statusInformation);
  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
  	  while(iterator.hasNext())
  	  {
  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
  	  }
    }
    /**
     * This method will transmit the information that the process is finished
     * @param bfinished
     */
    private synchronized void fireEvent(boolean bfinished)
    {
    	ProgressStatusEvent event = new ProgressStatusEvent(this);
  	  event.setStatusInformation(STATUS_FINISHED);
  	  event.setFinished(true);
  	  Iterator<ProgressStatusEventListener> iterator = eventlisteners.iterator();
  	  while(iterator.hasNext())
  	  {
  		  ((ProgressStatusEventListener)iterator.next()).handleProgressStatusEvent(event);
  	  }
    }
}
