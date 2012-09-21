package de.htw.hundertwasser.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JScrollBar;

/**
 * This class shows the Pictures
 * 
 * @author daniel rhein
 * @version 1.0
 * @since 04.09.2012
 */
public class ImageViewer extends JComponent {

	/**
	 * Version ID from the current Component
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Error if the Image is not available
	 */
	private static final String ERROR_NO_IMAGE = "Image kann nicht null sein.";

	/**
	 * TAG that the Class name relegates
	 */
	private static String TAG;
	/**
	 * Member Image-Object.
	 */
	private Image m_img;
	
	private JScrollBar scrollbar;

	/**
	 * Constructor
	 */
	public ImageViewer() {
		TAG = this.getClass().getSimpleName();
	}

	/**
	 * Specifies the fixed Image that has to be shown
	 * 
	 * @param image
	 *            Picture
	 * @throws IllegalArgumentException
	 *             if the Picture null is.
	 */
	public void setImage(Image image) throws IllegalArgumentException {
		if (image == null)
			throw new IllegalArgumentException(ERROR_NO_IMAGE, new Throwable(
					TAG + "." + "setImage()"));
		m_img = image;
		setPreferredSize(new Dimension(m_img.getWidth(this),
				m_img.getHeight(this)));
		setMinimumSize(new Dimension(m_img.getWidth(this),
				m_img.getHeight(this)));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
		if (m_img != null) {
			g.drawImage(m_img, 0, 0, this);
		}
	}

	/**
	 * Set the Picture of null.
	 */
	public void removeImage() {
		m_img = null;
	}

	/**
	 * Specifies if an Picture was assigned
	 * 
	 * @return true if m_img is null, otherwise true
	 */
	public boolean isEmpty() {
		if (m_img == null)
			return true;
		return false;
	}
}
