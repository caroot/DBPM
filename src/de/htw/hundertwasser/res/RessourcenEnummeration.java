package de.htw.hundertwasser.res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 * Enummeration der Bilder in unserem Projekt.
 * @author daniel
 *
 */
public enum RessourcenEnummeration {

	PHOTOALBUM_HINZUFUEGEN("add_photoalbum.png"),
	PHOTOBOX_HINZUFUEGEN("add_photobox.png"),
	ALBUM("album.png"),
	PFEIL_LINKS("arrow_left.png"),
	PFEIL_RECHTS("arrow_right.png"),
	LOESCHEN("delete.png"),
	VOLLBILD_VERLASSEN("exit_fullscreen.png"),
	VOLLBILD_PFEIL_LINKS("fs_arrow_left.png"),
	VOLLBILD_PFEIL_RECHTS("fs_arrow_right.png"),
	OEFFNEN("open.png"),
	PHOTOALBUM("photoalbum.png"),
	PHOTOBOX("photobox.png"),
	UMBENENNEN("rename.png"),
	SENDEN("send.png"),
	THUMBNAIL_PFEIL_LINKS("thumbnail_arrows_left.png"),
	THUMBNAIL_PFEIL_RECHTS("thumbnail_arrows_right.png"),
	WERKZEUG_SCHWARZWEIS("tool_blackwhite.png"),
	WERKZEUG_AUSSCHNEIDEN("tool_cut.png"),
	WERKZEUG_LOESCHEN("tool_delete.png"),
	WERKZEIG_VOLLBILD("tool_fullscreen.png"),
	WERKZEUG_DRUCKEN("tool_print.png"),
	WERKZEUG_UMBENENNEN("tool_rename.png"),
	WERKZEUG_SICHERN("tool_save.png"),
	WERKZEUG_SENDEN("tool_send.png"),
	WERKZEUG_ZOOMEN("tool_zoom.png");

	
	
	
	/**
	 * Name der Datei.
	 */
	private String m_name;
	/**
	 * Fehler der geworfen wird wenn ein Name null ist.
	 */
	private static final String ERROR_NAME_NULL = "Der Name wurde nicht angegeben.";
	/**
	 * Fehler der geworfen wird wenn ein Name leer ist.
	 */
	private static final String ERROR_NO_NAME = "Der Name darf nicht leer sein.";
	
	/**
	 * Deklaration der RessourcenEnnumeration.
	 * @param str_name Name des Bildes.
	 * @throws IllegalArgumentException wird geworfen wenn der Name null oder leer ist.
	 */
	private RessourcenEnummeration(String str_name) throws IllegalArgumentException
	{
		if (str_name == null) throw new IllegalArgumentException(ERROR_NAME_NULL);
		if (str_name.trim().isEmpty()) throw new IllegalArgumentException(ERROR_NO_NAME);
		m_name = str_name;
	}
	
	/**
	 * Liefert den Namen der Ressource.
	 * @return
	 */
	public String getName()
	{
		return m_name;
	}
	
	/**
	 * Liefert das Bild zur Ressource.
	 * @param ressource
	 * @return
	 * @throws IOException
	 */
	public static  BufferedImage getImage(RessourcenEnummeration ressource) throws IOException
	{
		
		return ImageIO.read(RessourcenEnummeration.class.getResourceAsStream(ressource.getName()));
	}
	
	/**
	 * Liefert das Icon zur Ressource.
	 * @param ressource
	 * @return
	 * @throws IOException
	 */
	public static Icon getIcon(RessourcenEnummeration ressource) throws IOException
	{
		Icon icon = (Icon) ImageIO.read(RessourcenEnummeration.class.getResourceAsStream(ressource.getName()));
		return icon;
	}
}
