package de.htw.test;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{
	
//	private BufferedImage image;

	private int xPosEnd;
	private int yPosEnd;
	private int xPosStart;
	private int yPosStart;
		
	public MouseListener(){
		
		initialiseMousePosition();
	
	}
	
	public void initialiseMousePosition(){
		
		
		System.out.println(getWidth());
		System.out.println(getHeight());
	
	}
	
	
	MouseAdapter getStart = new MouseAdapter() {
		public void mousePressed(MouseEvent e){
			
			xPosStart = getX(getMousePosition());
			yPosStart = getY(getMousePosition());
		}
	};
	
	
	MouseAdapter getEnd = new MouseAdapter() {
		public void mouseReleased(MouseEvent e){
			
			xPosEnd=getX(getMousePosition());
			yPosEnd=getY(getMousePosition());
		}
	
	};
	
	private Point getMousePosition(){

		PointerInfo pInfo = MouseInfo.getPointerInfo();
		Point location = pInfo.getLocation();
	
		return location;
	}
	
	
	private int getX(Point location){

		return location.x;
	}
	
	private int getY(Point location){
		
		return location.y;
	}
	
	private int getWidth(){
		
		return Math.abs(xPosStart - xPosEnd);
	}
	
	private int getHeight(){
		
		return Math.abs(yPosStart - yPosEnd);
	}
}


