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
          setProgress(0);
          try {
              Thread.sleep(150);
              while (progress < 100 && !isCancelled()) {
                  //Sleep for up to one second.
                  Thread.sleep(random.nextInt(1000));
                  //Make random progress.
                  progress += random.nextInt(10);
                  setProgress(Math.min(progress, 100));
              }
          } catch (InterruptedException ignore) {}
          return null;
      }

      @Override
      public void done() {
          Toolkit.getDefaultToolkit().beep();
      }	

}
