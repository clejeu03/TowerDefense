/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 11 mai 2013
 */
package View;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
@SuppressWarnings("serial")
public class EditorAddBaseSprite extends Sprite{

	protected MainViews toolBar;
	/**
	 * 
	 */
	public EditorAddBaseSprite(EditorScene editorScene, EditorToolBar toolBar, Point position,PlayerType playerType, int width, int height) {
		super(editorScene, -1, position,true, playerType,width,height);
		
		this.toolBar = toolBar;
		
		//Loading the base image (different one according the tower type and player)
		String fileName ="img/";
		
		if(playerType == PlayerType.NEUTRAL){
			fileName +="Neutral/";
		}
		else {
			fileName +="Fire/";
		}
		
		fileName += "base.png";
		
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	             myMousePressed(me);
	            } 
	       public void mouseEntered(MouseEvent me) { 
	    	   myMouseEntered(me);
         }
	       public void mouseExited(MouseEvent me) { 
	    	   myMouseExited(me);
         } 
       });
	}
	/**
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		Point position = toolBar.getPosition();
		position.translate(this.getPosition().x, this.getPosition().y);
		((EditorScene) view).addBaseClicked(position, playerType);
	}
	
	/**
	 * Event "the mouse has entered the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseEntered(MouseEvent me) {
		//Change the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Event "the mouse has quit the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseExited(MouseEvent me) {
		//Change the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
