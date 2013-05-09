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
import GameEngine.TowerManager.TowerTypes;

/**
 * Project - TowerDefense</br>
 * <b>Class - TowerSprite</b></br>
 * <p>The TowerSprite class represents the tower images displayed on the ScenView</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class TowerSprite extends Sprite{
	private TowerTypes towerType;
	private int range;
	
	/**
	 * Constructor of the TowerSprite class
	 * @param scene
	 * @param position
	 * @param clickable
	 * @param playerId
	 * @param width
	 * @param height
	 * @param towerType2
	 * @param range
	 */
	public TowerSprite(SceneView scene, int id, Point position, boolean clickable, PlayerType playerType, int width, int height, TowerTypes towerType2, int range) {
		super(scene, id, position,clickable,playerType,width,height);
		
		towerType = towerType2;
		this.range = range;
				
		//Loading the tower image (different one according the tower type and player)
		String fileName ="img/";
		
		if(playerType == PlayerType.ELECTRIC){
			fileName +="Electric/";
		}
		else if(playerType == PlayerType.WATER){
			fileName +="Water/";
		}
		else if(playerType == PlayerType.GRASS){
			fileName +="Grass/";
		}
		else if(playerType == PlayerType.FIRE){
			fileName +="Fire/";
		}
		
		//Attack
		if(towerType == TowerTypes.ATTACKTOWER){
			fileName += "attackTower.png";
		}
		//Medical
		else if(towerType == TowerTypes.SUPPORTTOWER){
			fileName += "medicalTower.png";
		}
		
		
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Add listeners if the tower is clickable
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
     * Getter range
     * @return
     * @see 
     */	
	public int getRange(){
		return range;
	}
	public TowerTypes getTowerType() {
		return towerType;
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
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
			((SceneView) view).towerClicked(position, playerType);
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
