

package de.htw.hundertwasser.view;



/**
 * Klasse die zum anzeigen des gewünschten Fotoalbums im Fullscreen Modus genutzt wird
 * @author johannes
 *
 */

public class PhotoBoxFullScreen extends FullScreen {
	FullScreen fsbox = new FullScreen();
	public PhotoBoxFullScreen (){
		super();
		
	}
	public void exitButton(){
		if(fsbox.getActionListener1() == true){
			System.out.println(""+fsbox.getActionListener1()+"");
				fsbox.dispose();
		}
	}
	public void leftArrow(){
		if(fsbox.getActionListener0() == true){
			
		}
	}
	public void reightArrwo(){
		if(fsbox.getActionListener2() == true){
			
		}
	}
 
	public static void main(String[] args) {
		PhotoAlbumFullScreen fsnew = new PhotoAlbumFullScreen();
		fsnew.setVisible(true);
	}
}
