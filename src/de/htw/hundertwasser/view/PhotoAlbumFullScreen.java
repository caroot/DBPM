

package de.htw.hundertwasser.view;



/**
 * Klasse die zum anzeigen des gewünschten Fotoalbums im Fullscreen Modus genutzt wird
 * @author johannes
 *
 */

public class PhotoAlbumFullScreen extends FullScreen {
	FullScreen fsalbum = new FullScreen();
	public PhotoAlbumFullScreen (){
		super();
		
	}
	public void exitButton(){
		if(fsalbum.getActionListener1() == true){
			System.out.println(""+fsalbum.getActionListener1()+"");
				fsalbum.dispose();
		}
	}
	public void leftArrow(){
		if(fsalbum.getActionListener0() == true){
			
		}
	}
	public void reightArrwo(){
		if(fsalbum.getActionListener2() == true){
			
		}
	}
 
	public static void main(String[] args) {
		PhotoAlbumFullScreen fsnew = new PhotoAlbumFullScreen();
		fsnew.setVisible(true);
	}
}
