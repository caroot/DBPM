package de.htw.hundertwasser.backend;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoBox;

public class StAXCompleteWriter {

	private static final String ERROR_NO_PATH_TO_FILE = "Sie haben keinen Pfad zur XML-Datei angebenen. Dieser muss zum Speichern angebeen werden.";
	private static final String ERROR_NO_PHOTOALBUM = "Sie haben kein Photoalbum angegeben. Diese muss zum Speichern angebeen werden.";
	private static final String ERROR_NO_PHOTOBOX = "Sie haben keine Photobox angegeben. Diese muss zum Speichern angebeen werden.";
	
	
	public StAXCompleteWriter()
	{
		
	}
	
	public void writeFile(String absoluteFilePath,ArrayList<PhotoAlbum> arPhotoAlbum,ArrayList<PhotoBox> arPhotoBox) throws IllegalArgumentException, IOException
	{
		//TODO:ERRORHANDLING
		if (absoluteFilePath== null) throw new IllegalArgumentException(ERROR_NO_PATH_TO_FILE);
		if (arPhotoAlbum==null) throw new IllegalArgumentException(ERROR_NO_PHOTOALBUM);
		if (arPhotoBox==null) throw new IllegalArgumentException(ERROR_NO_PHOTOBOX);
		if (absoluteFilePath.trim().length()==0) throw new IllegalArgumentException(ERROR_NO_PATH_TO_FILE);
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(
		                           new FileOutputStream(absoluteFilePath) );
		writer.writeDTD()
	}
	
}
