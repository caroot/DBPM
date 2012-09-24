package de.htw.hundertwasser.backend;

import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import de.htw.hundertwasser.core.interfaces.ProgressStatusListener;
import de.htw.hundertwasser.custom.event.ProgressStatusEvent;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;

/**
 * This class will mange how the files are copied.
 * @author daniel rhein
 *
 */
public class CopyFilesManagerTask extends SwingWorker<Void,Void>{
	  
	/**
	 * This error message occurs if the given Path is empty.
	 */
		private static final String ERROR_TARGET_PATH_EMPTY = "TargetPath can't be empty.";
		/**
		 * This error message occurs if the given Path is null.
		 */
		private static final String ERROR_TARGET_PATH_NULL = "TargetPath can't be null.";
		
		private static final String STATUS_FINISHED = "Process has end sucessfully.";
		
		/**
		 * Internal used FolderManager
		 * @link {@link FolderManager}
		 */
		private FolderManager folderManager = new FolderManager();
		/**
		 * Maximum amount of files
		 */
		private int maxfiles=0;
		/**
		 * List of files that shall be copied.
		 */
		private File[] fileList=null;
		/**
		 * File to copy
		 */
		private File file=null;
		/**
		 * Path to the files
		 */
		private String targetPath="";
		/**
		 * Determine if the PhotoBox should be created or not.
		 */
		private boolean bCreatePhotoBox=false;
		/**
		 * Determine if a completeFilelist should be copied to the targetPath or not.
		 */
		private boolean bCopyFileList = false;
		/**
		 * An set of Progress status Listeners
		 */
		private ArrayList<ProgressStatusListener> eventlisteners = new ArrayList<ProgressStatusListener>();
		
		
	/**
	 * Constructor copy files from source to targetPath
	 * @param fileList List of files
	 * @param targetPath Path where the files should be copied
	 * @throws IllegalArgumentException if the filelist is empty or null. And it will be thrown if the targetPath is empty or null.
	 */
	public CopyFilesManagerTask(File[] fileList,String targetPath) throws IllegalArgumentException
	{
		copyFiles(fileList,targetPath);
	}
	/**
	 * Constructor copy files from source to targetpath
	 * @param file list of files
	 * @param targetPath Path where the files should be copied
	 * @throws IllegalArgumentException if the file is null. And it will be thrown if the targetPath is empty or null.
	 */
	public CopyFilesManagerTask(File file,String targetPath) throws IllegalArgumentException
	{
		copyFiles(file,targetPath);
	}
		
		
	  private void copyFiles(File[] fileslist,String targetPath) throws IllegalArgumentException
	  {
		  if (fileslist ==null) throw new IllegalArgumentException("Filelist can't be null.");
		  checkTargetFile(targetPath);
		  this.fileList=fileslist;
		  bCopyFileList=true;
		  maxfiles=fileslist.length;
		  this.targetPath = targetPath;
	  }
	  
	  /**
	   * Try to determine if the targetPath is valid, and if this path exists
	   * @param targetPath
	   */
	  private void checkTargetFile(String targetPath)
	  {
		  if (targetPath == null) throw new IllegalArgumentException(ERROR_TARGET_PATH_NULL);
		  if (targetPath.trim().isEmpty()) throw new IllegalArgumentException(ERROR_TARGET_PATH_EMPTY);
		  File targetFile = new File(targetPath);
		  if (targetFile.exists())
		  {
			  bCreatePhotoBox=false;
		  }
		  else
		  {
			  bCreatePhotoBox=true;
		  }
	  }
	  
	  private void copyFiles(File file,String targetPath) throws IllegalArgumentException
	  {
		  checkTargetFile(targetPath);
		  if (file == null) throw new IllegalArgumentException("File can't be null.");
		  this.file = file;
		  maxfiles=1;
		  bCopyFileList=false;
		  this.targetPath = targetPath;
	  }
	  
	  /**
	   * This method is the first attempt to copy files. </br>
	   * But the methods depends on system-calls which are different on every OS.</br>
	   * Furthermore you won't copy a file, but you move it from one directory to the target directory.</br>
	   * <b>This method is obsolete.</b>
	   * 
	   * @param source
	   * @param target
	   * @return
	   * @deprecated
	   */
	  @SuppressWarnings("unused")
	private boolean moveFile(File source,String target)
	  {
		  File tempfile = new File(target);
		  Component comp = null;
		  if (tempfile.exists())
		  {
			  //TODO:Show Confirmation Dialog
			 if (JOptionPane.showConfirmDialog(comp, "Would you like to override the File " + target + ". Please Confirm this opration with yes to overwrite the file or no to discard the operation for the file.","Question",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			 {
				 if (!tempfile.delete())
				 {
					 JOptionPane.showMessageDialog(comp, "Can't rewrite file " + target + ".","File-Operation-Error-Occured",JOptionPane.ERROR_MESSAGE);
				 }
				 else
				 {
					 //moveFile
					 return source.renameTo(tempfile);
				 }
			 }
			 return false;
		  }
		  if (!tempfile.delete())
			 {
				 JOptionPane.showMessageDialog(comp, "Can't rewrite file " + target + ".","File-Operation-Error-Occured",JOptionPane.ERROR_MESSAGE);
			 }
			 else
			 {
				 //moveFile
				 return source.renameTo(tempfile);
			 }
		  return false;
	  }
	  
	  /**
	   * This method copies source-Files to an given target.
	   * @param source File that determine absolutley where the file is from.
	   * @param target Target directory, where the file should be copied
	   * @throws IllegalArgumentException if the source is null or the target-directory is empty.
	   * @throws FileNotFoundException if the given source can't be found
	   * @throws IOException if it can't be written to the target.
	   */
	  private void copyFile(File source,String target) throws IllegalArgumentException,FileNotFoundException,IOException
	  {
		  	File inputFile = source;
		    File outputFile = new File(target);
		    Component comp = null;
		    if (outputFile.exists())
		    {
		    	fireEvent("Found file with the same name.");
		    	 if (JOptionPane.showConfirmDialog(comp, "I found a file with the same name. Would you like to rename  " + source.getName() + "? So press the Button 'yes'. If you like to skip it press the button 'no'. Please Confirm this opration with yes to change its name  or no to skip the operation for the file.","Question",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				 {
		    		 String newName=null;
		    		 
		    		 while(newName==null)
		    		 {
		    			 newName = JOptionPane.showInputDialog(null,"Please, enter a new filename.",outputFile.getName());
		    			 if (newName==null)	
		    			 {
		    				 JOptionPane.showMessageDialog(null, "Name can't be null.", "Input error", JOptionPane.INFORMATION_MESSAGE);
		    			 }
		    		 }
		    		 fireEvent("File renamed to "+newName+" .");
		    		 if (!newName.contains(outputFile.toString().split(".")[1])) //Suffix not given
		    		 {
		    			 newName = newName + outputFile.toString().split(".")[1];//Apply suffix.
		    		 }
		    		 fireEvent("File renamed to "+newName+" .");
		    		 outputFile = new File(target.replace(outputFile.getName(), newName));
		    		 fireEvent("Output file renamed.");
				 }
		    	 else
		    	 {
		    		 //Skip Operation.
		    		 return; //Do nothing
		    	 }
		    }
		    
		    if (!inputFile.exists()) throw new IllegalArgumentException("Source-file can't be found");
		    
		    FileReader in = new FileReader(inputFile);
	    	FileWriter out = new FileWriter(outputFile);
	    	int c;
	    	fireEvent("Copy File " + inputFile.getName() + " to " + outputFile.getName());
	    	while ((c = in.read()) != -1)
	    		out.write(c);

	    	in.close();
	    	out.close();
	    	fireEvent("File successfully copied");
	  }
	
	  @Override
      public Void doInBackground() {
          int progress = 0;
          int i = 0;
          setProgress(0);
          try {
              Thread.sleep(1000);
              while (progress < 100 && !isCancelled()) {
            	 if (bCreatePhotoBox)
                 {
            		 fireEvent("Try to create directories");
                     if (folderManager.createDirectories(targetPath)==true)
                     {
                    	 fireEvent("Directories created");
                     }
                 }
            	  if (bCopyFileList)
            	  {
            		 copyFile(fileList[i], targetPath+File.separatorChar+fileList[i].getName()); 
            	  }
            	  else
            	  {
            		  copyFile(file,targetPath+File.separatorChar+file.getName());
            		  setProgress(100);
            	  }
                  //Sleep for up to one second.
            	  //Thread.sleep(random.nextInt(1000));
                  //Make random progress.
                  progress = ((100*i)/maxfiles);
                  setProgress(progress);
                  i++;
              }
          } catch (InterruptedException ignore) {} 
          catch (IllegalArgumentException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "Error", e.getStackTrace());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "Error", e.getStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(null, e.getMessage(), "Error", e.getStackTrace());
			e.printStackTrace();
		}
          return null;
      }

      @Override
      public void done() {
    	  fireEvent(true);
          Toolkit.getDefaultToolkit().beep();
      }	

      public synchronized void addEventListener(ProgressStatusListener listener)
      {
    	  eventlisteners.add(listener);
      }
      
      public synchronized void removeEventListener(ProgressStatusListener listener)
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
    	  Iterator<ProgressStatusListener> iterator = eventlisteners.iterator();
    	  while(iterator.hasNext())
    	  {
    		  ((ProgressStatusListener)iterator.next()).handleProgressStatusEvent(event);
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
    	  Iterator<ProgressStatusListener> iterator = eventlisteners.iterator();
    	  while(iterator.hasNext())
    	  {
    		  ((ProgressStatusListener)iterator.next()).handleProgressStatusEvent(event);
    	  }
      }
}
