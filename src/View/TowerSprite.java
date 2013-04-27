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
	private int towerType;
	private int range;
	
	/**
	 * Constructor of the TowerSprite class
	 * @param scene
	 * @param position
	 * @param clickable
	 * @param playerId
	 * @param width
	 * @param height
	 * @param type
	 * @param range
	 */
	public TowerSprite(SceneView scene, Point position, boolean clickable, int playerId, int width, int height, int type, int range) {
		super(scene, position,clickable,playerId,width,height);
		
		towerType = type;
		this.range = range;
				
		//Loading the tower image (different one according the tower type and player)
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
		
		
		fileName += "starter.png";
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
	
	/**
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
			System.out.println("TowerOwner number : "+ playerId+" !");
			System.out.println("Position on the Sprite ("+ me.getPoint().x+","+ me.getPoint().y+")");
			((SceneView) view).towerClicked(position, playerId);
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
