package de.htw.hundertwasser.backend;

import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * This class will mange how the files are copied.
 * @author daniel rhein
 *
 */
public class CopyFilesManagerTask extends SwingWorker<Void,Void>{
	  
		private static final String ERROR_TARGET_PATH_EMPTY = "TargetPath can't be empty.";
		private static final String ERROR_TARGET_PATH_NULL = "TargetPath can't be null.";
		/**
		 * Internal used FolderManager
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
	  
	  private void copyFile(File source,String target) throws FileNotFoundException,IOException
	  {
		  	File inputFile = source;
		    File outputFile = new File(target);
		    Component comp = null;
		    if (outputFile.exists())
		    {
		    	 if (JOptionPane.showConfirmDialog(comp, "I found a file with the same name. Would you like to rename  " + source.getName() + "? So press the Button 'yes'. If you like to skip it press the button 'no'. Please Confirm this opration with yes to overwrite the file or no to discard the operation for the file.","Question",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				 {
		    		 //TODO:INPUTBOX eingeben.
				 }
		    }
		    else
		    {
		    }
		    FileReader in = new FileReader(inputFile);
	    	FileWriter out = new FileWriter(outputFile);
	    	int c;

	    	while ((c = in.read()) != -1)
	    		out.write(c);

	    	in.close();
	    	out.close();
		    
		    
		  
	  }
	
	  @Override
      public Void doInBackground() {
          Random random = new Random();
          int progress = 0;
          int i = 0;
          setProgress(0);
          try {
              Thread.sleep(150);
              while (progress < 100 && !isCancelled()) {
            	 if (bCreatePhotoBox) folderManager.createDirectories(targetPath);
            	  if (bCopyFileList)
            	  {
            		  
            	  }
                  //Sleep for up to one second.
            	  //Thread.sleep(random.nextInt(1000));
                  //Make random progress.
                  progress = ((100*i)/maxfiles);
                  setProgress(progress);
              }
          } catch (InterruptedException ignore) {}
          return null;
      }

      @Override
      public void done() {
          Toolkit.getDefaultToolkit().beep();
      }	

}
