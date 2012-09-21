package de.htw.hundertwasser.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoAlbumEntry;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.res.RessourcenEnummeration;

/*
 * Class that save the data into an XML file
 * @author daniel rhein
 */
public class StAXCompleteWriter {

	private static final String ERROR_NO_PATH_TO_FILE = "You did not specify a path to the XML file. This must be specified for saving.";
	private static final String ERROR_NO_PHOTOALBUM = "This must be specified for saving photo album. This must be specified for saving.";
	private static final String ERROR_NO_PHOTOBOX = "This must be specified for saving the photo box . This must be specified for saving.";
	private RessourcenEnummeration ressource;

	public StAXCompleteWriter() {
		ressource = RessourcenEnummeration.DTD_COMPLETE;
	}

	public void writeFile(String absoluteFilePath,
			ArrayList<PhotoAlbum> arPhotoAlbum, ArrayList<PhotoBox> arPhotoBox)
			throws IllegalArgumentException, IOException,
			OperationNotSupportedException, XMLStreamException {
		if (absoluteFilePath == null)
			throw new IllegalArgumentException(ERROR_NO_PATH_TO_FILE);
		if (arPhotoAlbum == null)
			throw new IllegalArgumentException(ERROR_NO_PHOTOALBUM);
		if (arPhotoBox == null)
			throw new IllegalArgumentException(ERROR_NO_PHOTOBOX);
		if (absoluteFilePath.trim().length() == 0)
			throw new IllegalArgumentException(ERROR_NO_PATH_TO_FILE);
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory
				.createXMLStreamWriter(new FileOutputStream(absoluteFilePath));
		writer.writeStartDocument("1.0");
		writer.writeDTD(ressource.getContent());
		writer.writeStartElement("DunkelbuntPhotomanager");
		writer.writeAttribute("anzPhotoAlbum",
				String.valueOf(arPhotoAlbum.size()));
		writer.writeAttribute("anzPhotoBox", String.valueOf(arPhotoBox.size()));

		for (PhotoAlbum photoalbum : arPhotoAlbum) {
			writer.writeStartElement("PhotoAlbum");
			writer.writeAttribute("name", photoalbum.getName());
			writer.writeAttribute("anzEntries",
					String.valueOf(photoalbum.getCountEntries()));
			for (int i = 0; i < photoalbum.getCountEntries(); i++) {
				PhotoAlbumEntry entry = photoalbum.getEntry(i);

				if (entry != null) {
					writer.writeStartElement("PhotoAlbumEntry"); // START_PHOTOALBUMENTRY
					writer.writeAttribute("id", String.valueOf(i));
					if (entry.getPhoto() != null) {
						writer.writeEmptyElement("Photo");
						writer.writeAttribute("src", entry.getPhoto()
								.getPathToFile());
						writer.writeAttribute("name", entry.getPhoto()
								.getName());
						writer.writeAttribute("comment", entry.getPhoto()
								.getComment());
					}
					if (entry.getText() != null) {
						writer.writeEmptyElement("Text");
						writer.writeAttribute("text", entry.getText());
					}
					if (entry.getSticker() != null) {
						writer.writeEmptyElement("Sticker");
						writer.writeAttribute("id",
								String.valueOf(entry.getSticker().ordinal()));
					}
					writer.writeEndElement();// END_PHOTOALBUMENTRY
				}
			}
			writer.writeEndElement();// END_PHOTOALBUM
		}
		for (PhotoBox photoBox : arPhotoBox) {
			writer.writeStartElement("Photobox");
			writer.writeAttribute("name", photoBox.getName());
			writer.writeAttribute("anzPhotos",
					String.valueOf(photoBox.getCount()));
			for (int i = 0; i < photoBox.getCount(); i++) {
				writer.writeStartElement("Photo");
				writer.writeAttribute("src", photoBox.getPhoto(i)
						.getPathToFile());
				writer.writeAttribute("name", photoBox.getPhoto(i).getName());
				writer.writeAttribute("comment", photoBox.getPhoto(i)
						.getComment());
				writer.writeEndElement();

			}
			writer.writeEndElement();// END_PHOTOALBUMENTRY
		}
	}

}
