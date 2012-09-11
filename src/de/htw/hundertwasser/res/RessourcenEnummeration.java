package de.htw.hundertwasser.res;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.naming.OperationNotSupportedException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Enummeration der Bilder in unserem Projekt.
 * 
 * @author daniel
 * 
 */
public enum RessourcenEnummeration {

	PHOTOALBUM_HINZUFUEGEN("add_photoalbum.png"), PHOTOBOX_HINZUFUEGEN(
			"add_photobox.png"), ALBUM("album.png"), PFEIL_LINKS(
			"arrow_left.png"), PFEIL_RECHTS("arrow_right.png"), LOESCHEN(
			"delete.png"), VOLLBILD_VERLASSEN("exit_fullscreen.png"), VOLLBILD_PFEIL_LINKS(
			"fs_arrow_left.png"), VOLLBILD_PFEIL_RECHTS("fs_arrow_right.png"), VOLLBILD_VERLASSEN_KLEIN(
			"exit_fullscreen_small.png"), VOLLBILD_PFEIL_LINKS_KLEIN(
			"fs_arrow_left_small.png"), VOLLBILD_PFEIL_RECHTS_KLEIN(
			"fs_arrow_right_small.png"), OEFFNEN("open.png"), PHOTOALBUM(
			"photoalbum.png"), PHOTOBOX("photobox.png"), UMBENENNEN(
			"rename.png"), SENDEN("send.png"), THUMBNAIL_PFEIL_LINKS(
			"thumbnail_arrows_left.png"), THUMBNAIL_PFEIL_RECHTS(
			"thumbnail_arrows_right.png"), WERKZEUG_SCHWARZWEIS(
			"tool_blackwhite.png"), WERKZEUG_AUSSCHNEIDEN("tool_cut.png"), WERKZEUG_LOESCHEN(
			"tool_delete.png"), WERKZEIG_VOLLBILD("tool_fullscreen.png"), WERKZEUG_DRUCKEN(
			"tool_print.png"), WERKZEUG_UMBENENNEN("tool_rename.png"), WERKZEUG_SICHERN(
			"tool_save.png"), WERKZEUG_SENDEN("tool_send.png"), WERKZEUG_ZOOMEN(
			"tool_zoom.png"), DTD_COMPLETE("Complete.dtd"), DTD_EXPORT_PHOTOALBUM(
			"TransferPhotoAlbum.dtd"), DTD_EXPORT_PHOTOBOX(
			"TransferPhotoAlbum.dtd"), FONT_CALIBRI_BOLD("CALIBRIB.TTF"), FONT_CALIBRI(
			"CALIBRI.TTF"), FONT_CALIBRI_ITALIC("CALIBRII.TTF"), FONT_CALIBRI_SCHRAEG(
			"CALIBRIZ.TTF");

	private static final String ERROR_NO_FONT = "Nur Fonts können angezeigt werden.";
	private static final String ERROR_NO_DTD = "Diese Methode kann nur bei einer der vorhandenen DTDs angewendet werden.";
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
	 * 
	 * @param str_name
	 *            Name des Bildes.
	 * @throws IllegalArgumentException
	 *             wird geworfen wenn der Name null oder leer ist.
	 */
	private RessourcenEnummeration(String str_name)
			throws IllegalArgumentException {
		if (str_name == null)
			throw new IllegalArgumentException(ERROR_NAME_NULL);
		if (str_name.trim().isEmpty())
			throw new IllegalArgumentException(ERROR_NO_NAME);
		m_name = str_name;
	}

	/**
	 * Liefert den Namen der Ressource.
	 * 
	 * @return
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * Liefert das Bild zur Ressource.
	 * 
	 * @param ressource
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getImage(RessourcenEnummeration ressource)
			throws IOException {
		return ImageIO.read(RessourcenEnummeration.class.getResource(ressource
				.getName()));
	}

	/**
	 * Liefert das Bild zur Ressource.
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getImage() throws IOException {
		return ImageIO
				.read(RessourcenEnummeration.class.getResource(getName()));
	}

	/**
	 * Liefert das Icon zur Ressource.
	 * 
	 * @param ressource
	 * @return
	 * @throws IOException
	 */
	public static Icon getIcon(RessourcenEnummeration ressource)
			throws IOException {
		ImageIcon icon = new ImageIcon(
				ImageIO.read(RessourcenEnummeration.class.getResource(ressource
						.getName())));
		return icon;
	}

	/**
	 * Liefert das Icon zur Ressource.
	 * 
	 * @param ressource
	 * @return
	 * @throws IOException
	 */
	public Icon getIcon() throws IOException {
		ImageIcon icon = new ImageIcon(
				ImageIO.read(RessourcenEnummeration.class
						.getResource(getName())));
		return icon;
	}

	/**
	 * Diese Methode liefert den Inhalte einer beliebigen DTD.</p> Wird eine
	 * Datei benutzt die keine DTD ist, wird eine Exception geworfen.
	 * 
	 * @return
	 * @throws IOException
	 * @throws OperationNotSupportedException
	 */
	public String getContent() throws IOException,
			OperationNotSupportedException {
		StringBuilder sb = new StringBuilder();
		if (this.equals(DTD_COMPLETE) || this.equals(DTD_EXPORT_PHOTOBOX)
				|| this.equals(DTD_EXPORT_PHOTOALBUM)) {
			InputStreamReader is = new InputStreamReader(
					RessourcenEnummeration.class.getResourceAsStream(getName()));
			BufferedReader br = new BufferedReader(is);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} else {
			throw new OperationNotSupportedException(ERROR_NO_DTD);
		}
	}

	/**
	 * Diese Methode liefert einen Font aus dem Ressourcenpool</p> Wird eine
	 * Datei benutzt die kein Font ist, wird eine Exception geworfen.
	 * 
	 * @return
	 * @throws IOException
	 * @throws OperationNotSupportedException
	 * @throws FontFormatException
	 */
	public Font getFont() throws IOException, OperationNotSupportedException,
			FontFormatException {

		if (this.equals(FONT_CALIBRI) || this.equals(FONT_CALIBRI_BOLD)
				|| this.equals(FONT_CALIBRI_ITALIC)
				|| this.equals(FONT_CALIBRI_SCHRAEG)) {
			Font font = Font
					.createFont(Font.TRUETYPE_FONT,
							RessourcenEnummeration.class
									.getResourceAsStream(getName()));
			return font;
		}
		throw new OperationNotSupportedException(ERROR_NO_FONT);
	}

}
