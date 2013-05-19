/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 18 mai 2013
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
 * <b>Class - ElvolveTowerSprite</b></br>
 * <p>The EvolveTowerSprite class represents the Evolvetower button displayed on the GameInfoMenu</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class EvolveTowerSprite extends Sprite{
	private TowerTypes towerType;
	/**
	 * 
	 */
	public EvolveTowerSprite(GameInfoMenu info, int id, Point position, boolean clickable, PlayerType playerType, int width, int height, TowerTypes towerType) {
		super(info,id, position, clickable, playerType, width, height);
		
		this.towerType = towerType;
		
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
		else if(towerType ==  TowerTypes.GUNTOWER){
			fileName += "gunTower.png";
			
		}
		else if(towerType == TowerTypes.FROSTTOWER){
			fileName += "frostTower.png";	
		}
		else if(towerType == TowerTypes.BOMBTOWER){
			fileName += "bombTower.png";
		}
		else if(towerType == TowerTypes.LAZERTOWER){
			fileName += "laserTower.png";
		}
		//Medical
		else if(towerType == TowerTypes.SUPPORTTOWER){
			fileName += "supportTower.png";
		}
		else if(towerType == TowerTypes.MEDICALTOWER){
			fileName += "medicalTower.png";			
		}
		else if(towerType == TowerTypes.SHIELDTOWER){
			fileName += "shieldTower.png";		
		}

		
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
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
		((GameInfoMenu) view).evolveTower(id, towerType);
		//TODO display the towerToAdd information in the GameInfoMenu ((GameInfoMenu) info).addTowerClicked(towerType)
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
