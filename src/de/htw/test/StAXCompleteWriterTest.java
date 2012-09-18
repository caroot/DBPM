/**
 * 
 */
package de.htw.test;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import javax.xml.stream.XMLStreamException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.htw.hundertwasser.backend.StAXCompleteWriter;
import de.htw.hundertwasser.core.Photo;
import de.htw.hundertwasser.core.PhotoAlbum;
import de.htw.hundertwasser.core.PhotoAlbumEntry;
import de.htw.hundertwasser.core.PhotoBox;
import de.htw.hundertwasser.core.StickerEnummeration;

/**
 * @author daniel
 *
 */
//TODO: Test fertig stellen.
public class StAXCompleteWriterTest {

	private static final int TEN=10;
	private static final int HUNDRED=100;
	

	PhotoBox photoBox10;
	PhotoAlbum photoAlbum10;
	PhotoBox photoBox100;
	PhotoAlbum photoAlbum100;
	PhotoBox photoBox0;
	PhotoAlbum photoAlbum0;
	
	ArrayList<PhotoAlbum> arPhotoAlbum;
	ArrayList<PhotoBox> arPhotoBox;

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private PhotoBox createPhotoBox(int anzPhotos)
	{
		PhotoBox photobox= new PhotoBox("asdasdsda");
		photobox.setName("MyPhotobox with" + anzPhotos);
		for (int i=0;i<anzPhotos;i++)
		{
			Photo photo = new Photo("Name "+i,"Source"+i);
			photo.setComment("Comment "+ i);
			photobox.addPhoto(photo);

		}
		return photobox;
	}
	
	private PhotoAlbum createPhotoAlbum(int anzEntries)
	{
		PhotoAlbum photoAlbum = new PhotoAlbum();
		photoAlbum.setName("MyPhotoAlbum with" + anzEntries);
		for (int i=0;i<anzEntries;i++)
		{
			PhotoAlbumEntry photoAlbumEntry = new PhotoAlbumEntry();
			Photo photo = new Photo("Name "+i,"Source"+i);
			photo.setComment("Comment "+ i);
			photoAlbumEntry.setPhoto(photo);
			photoAlbumEntry.setText("Text " + i);
			photoAlbumEntry.setSticker(StickerEnummeration.BOX);
			photoAlbum.addEntry(photoAlbumEntry, i);
		}
		return photoAlbum;
	}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		photoBox10 = createPhotoBox(10);
		photoBox100 = createPhotoBox(100);
		photoBox0 = new PhotoBox("asdasdsda");
		photoBox0.setName("MyPhotoBoxName");
		
		photoAlbum0 = new PhotoAlbum();
		photoAlbum0.setName("MyPhotoBoxName");
		photoAlbum10 = createPhotoAlbum(10);
		photoAlbum100 = createPhotoAlbum(100);
		arPhotoAlbum= new ArrayList<PhotoAlbum>();
		arPhotoBox = new ArrayList<PhotoBox>();
	
		arPhotoAlbum.add(photoAlbum0);
		arPhotoAlbum.add(photoAlbum10);
		arPhotoAlbum.add(photoAlbum100);
		
		arPhotoBox.add(photoBox0);
		arPhotoBox.add(photoBox10);
		arPhotoBox.add(photoBox100);

		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testOutput() {
		System.out.println("Please veriyfy the output to Complete this test");
		StAXCompleteWriter wr= new StAXCompleteWriter();
		try
		{
		wr.writeFile("TestWriting.xml",arPhotoAlbum, arPhotoBox);
		Assert.assertTrue(true);
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
		catch (OperationNotSupportedException e)
		{
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
		catch (XMLStreamException e)
		{
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
