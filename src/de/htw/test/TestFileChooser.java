package de.htw.test;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class TestFileChooser extends FileFilter{

	TestFileChooser()
	{
		super();
	}
	
	public void start()
	{
		JFileChooser filechooser = new JFileChooser();	
		filechooser.setFileFilter(this);
		filechooser.setVisible(true);
	}
	
	@Override
	public boolean accept(File arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		TestFileChooser filechooser = new TestFileChooser();
		filechooser.start();
	}
}
