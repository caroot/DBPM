package de.htw.hundertwasser.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

/**
 * Der ImageViewer erlaubt die Dastellung von Bilddateien.
 * @author daniel
 * @version 1.0
 * @since 04.09.2012
 */
public class ImageViewer extends JComponent{

	/**
	 * Version ID der aktuellen Komponente
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Fehlermeldung wenn das Image nicht vorhanden ist.
	 */
	private static final String ERROR_NO_IMAGE="Image kann nicht null sein.";
	/**
	 * TAG der auf den Namen der Klasse verweist.
	 */
	private static String TAG;
	/**
	 * Member Image-Objekt.
	 */
	private Image m_img;
	
	/**
	 * Konstruktor
	 */
	public ImageViewer()
	{
		TAG = this.getClass().getSimpleName();
	}
	
	/**
	 * Legt das Bild, dass angezeigt werden soll fest.
	 * @param image, Bilddatei.
	 * @throws IllegalArgumentException, wenn das Bild null ist.
	 */
	public void setImage(Image image) throws IllegalArgumentException
	{
		if (image == null) throw new IllegalArgumentException(ERROR_NO_IMAGE,new Throwable(TAG+"."+"setImage()"));
		m_img = image;
		setPreferredSize(new Dimension(m_img.getWidth(this),m_img.getHeight(this)));
		setMinimumSize(new Dimension(m_img.getWidth(this),m_img.getHeight(this)));
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		if (m_img!=null)
		{
		g.drawImage(m_img, 0, 0, this);
		}
	}
	/**
	 * Setzt das Bild auf leer.
	 */
	public void removeImage()
	{
		m_img = null;
	}
	
	/**
	 * Gibt an, ob dieser Komponente ein Image zugeordnet wurde.
	 * @return
	 */
	public boolean isEmpty()
	{
		if (m_img==null) return true;
		return false;
	}
}
