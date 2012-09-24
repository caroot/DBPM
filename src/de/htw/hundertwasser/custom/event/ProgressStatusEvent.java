package de.htw.hundertwasser.custom.event;

import java.util.EventObject;
/**
 * This Event will inform the ImportStatusBar about the current progress information.
 * @author daniel
 *
 */
public class ProgressStatusEvent extends EventObject {

	/**
	 * this string will contain the status informatoin
	 */
	private String statusInformation;
	/**
	 * This variable will show that the process is finished.
	 */
	private boolean bFinish=false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor 
	 * @param source
	 */
	public ProgressStatusEvent(Object source) {
		  super(source);
     }
	 /**
	  * This method will return the status Information
	  * @return
	  */
	 public String getStatusInformation()
	 {
		 return statusInformation;
	 }
	 
	 /**
	  * This method will set the status Information
	  */
	 public void setStatusInformation(String information)
	 {
		 this.statusInformation = information;
	 }
	 /**
	  * This method will determine that the process is finished
	  * @param finished
	  */
	 public void setFinished(boolean finished)
	 {
		 this.bFinish=finished;
	 }
	 
	 /**
	  * This function will show that the process is finished or not. 
	  * @return
	  */
	 public boolean getFinished()
	 {
		 return bFinish;
	 }
}
