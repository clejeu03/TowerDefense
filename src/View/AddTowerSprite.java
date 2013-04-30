/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 30 avr. 2013
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

/**
 * Project - TowerDefense</br>
 * <b>Class - AddTowerSprite</b></br>
 * <p>The AddTowerSprite class represents the AddTower button displayed on the ScenView</p>
 * <b>Creation :</b> 30/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class AddTowerSprite  extends Sprite{
	/*
	 * 1 : attaque
	 * 2 : soutien
	 */
	private int towerType;

	/**
	 * 
	 */
	public AddTowerSprite(SceneView scene, Point position, boolean clickable, int playerId, int width, int height, int towerType) {
		super(scene, position,clickable,playerId,width,height);
		
		this.towerType = towerType;
		
		String fileName ="img/";
		
		if(playerId == 0){
			fileName +="Electric/";
		}
		else if(playerId == 1){
			fileName +="Water/";
		}
		else if(playerId == 2){
			fileName +="Grass/";
		}
		else if(playerId == 3){
			fileName +="Fire/";
		}
		
		
		fileName += "addTower.png";
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
		((SceneView) view).addTowerClicked(position, playerId, towerType);
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
