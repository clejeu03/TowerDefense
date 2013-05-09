/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
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
 * <b>Class - InfoSprite</b></br>
 * <p>The InfoSprite class represents the informative images displayed around an tower of the 
 * Sceneview when the user click on this tower</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */

@SuppressWarnings("serial")
public class TowerInfoSprite extends Sprite{
	private  int type;
	private Point positionElt;
	
	/**
	 * Constructor of the TowerInfoSprite class
	 * @param scene
	 * @param position
	 * @param clickable
	 * @param playerId
	 * @param width
	 * @param height
	 * @param type
	 * @param positionElt
	 */
	public TowerInfoSprite(SceneView scene, int id, Point position, boolean clickable, PlayerType playerType, int width, int height, int type, Point positionElt) {
		super(scene, id, position,clickable,playerType,width,height);
		this.type = type;
		this.positionElt = positionElt;
		
		//Loading the image according to the type (TODO)
		try {
		      image = ImageIO.read(new File("img/delete.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Adding listeners if the InfoSprite is clickable
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
	 * Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
			((SceneView) view).towerToSupress(id, positionElt, playerType);
	}
	
	/**
	 * Event "the mouse has entered the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseEntered(MouseEvent me) {
		//Changing the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Event "the mouse has quit the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseExited(MouseEvent me) {
		//Changing the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}


}
