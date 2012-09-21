package de.htw.hundertwasser.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JScrollBar;

import de.htw.hundertwasser.core.interfaces.ThumbNailBarObserver;
import de.htw.hundertwasser.custom.error.InsufficientPrivilegesException;
import de.htw.hundertwasser.errorsupport.ErrorMessageDialog;

/**
 * This class shows the Pictures
 * 
 * @author daniel rhein
 * @version 1.0
 * @since 04.09.2012
 */
public class ImageViewer extends JComponent implements MouseWheelListener,ComponentListener,ContainerListener,ThumbNailBarObserver  {

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
	private int mywidth=0;
	private int myheight=0;
	private JScrollBar scrollbar;

	/**
	 * Constructor
	 */
	public ImageViewer() {
		TAG = this.getClass().getSimpleName();
		this.addComponentListener(this);
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
//		setPreferredSize(new Dimension(m_img.getWidth(this),
//				m_img.getHeight(this)));
		setMaximumSize(new Dimension(m_img.getWidth(this),
				m_img.getHeight(this)));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		if (m_img != null) {
//			g.drawImage(m_img, 0, 0, this);
			g.drawImage(m_img,0,0,this.getWidth(),this.getHeight(),this);
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

//	private void sthChanged() {
//        System.out.println("Something changed!  " + this.getWidth() + "  " + this.getHeight());
//    }
	
	@Override
	public void componentResized(ComponentEvent e) {
//		 sthChanged(); 
	}

	@Override
	public void componentShown(ComponentEvent e) {
		repaint();
	}

	@Override
	public void componentAdded(ContainerEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentRemoved(ContainerEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhoto(Photo photo) {
		try {
			this.m_img = photo.getImage();
			repaint();
		} catch (FileNotFoundException e) {
			ErrorMessageDialog.showMessage(this,e.getMessage(),"Error",e.getStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			ErrorMessageDialog.showMessage(this,e.getMessage(),"Error",e.getStackTrace().toString());
			e.printStackTrace();
		} catch (InsufficientPrivilegesException e) {
			ErrorMessageDialog.showMessage(this,e.getMessage(),"Error",e.getStackTrace().toString());
			e.printStackTrace();
		}
		
	}
}
