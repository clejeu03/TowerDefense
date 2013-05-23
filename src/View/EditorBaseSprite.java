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

import GameEngine.GameManager;
import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - EditorBaseSprite</b></br>
 * <p>The EditorBaseSprite class represents the base images displayed on the EditorScene</p>
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class EditorBaseSprite extends Sprite{

	/**
	 * 
	 */
	public EditorBaseSprite(EditorScene scene, int id, boolean clickable, Point position,PlayerType playerType, int width, int height) {
		super(scene, -1, position, clickable,playerType,width,height);
		
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
			
	}
	
	public void setClickable(boolean clickable){
		this.clickable = clickable;
		
		if(clickable){
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
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		((EditorScene) view).baseClicked(position, playerType);
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
