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
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import GameEngine.GameManager;
import GameEngine.TowerManager;
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
public class TowerSprite extends Sprite implements Runnable{
	private TowerTypes towerType;
	private ArrayList<TowerManager.TowerTypes> evolutions;
	private int range;
	private boolean isActivated;
	private Thread thread;
	
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
	public TowerSprite(SceneView scene, int id, Point position, boolean clickable, PlayerType playerType, int width, int height, TowerTypes towerType, int range) {
		super(scene, id, position,clickable,playerType,width,height);
		
		this.range = range;
		this.isActivated = false;
		this.evolutions = new ArrayList<TowerManager.TowerTypes>();
		
		setTowerType(towerType);
		
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
	
	public void setTowerType(TowerTypes towerType){
		//Removing all the current evolution			
		this.evolutions = new ArrayList<TowerManager.TowerTypes>();
		
		this.towerType = towerType;
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
			this.setEvolutions(TowerTypes.GUNTOWER, TowerTypes.FROSTTOWER);
		}
		else if(towerType ==  TowerTypes.GUNTOWER){
			fileName += "gunTower.png";
			this.setEvolutions(TowerTypes.BOMBTOWER, TowerTypes.LAZERTOWER);
		}
		else if(towerType == TowerTypes.FROSTTOWER){
			fileName += "frostTower.png";	
			this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		}
		else if(towerType == TowerTypes.BOMBTOWER){
			fileName += "bombTower.png";
			this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		}
		else if(towerType == TowerTypes.LAZERTOWER ){
			fileName += "laserTower.png";
			this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		}
		//Medical
		else if(towerType == TowerTypes.SUPPORTTOWER){
			fileName += "supportTower.png";
			this.setEvolutions(TowerTypes.MEDICALTOWER, TowerTypes.SHIELDTOWER);
		}
		else if(towerType == TowerTypes.MEDICALTOWER){
			fileName += "medicalTower.png";			
			this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		}
		else if(towerType == TowerTypes.SHIELDTOWER){
			fileName += "shieldTower.png";		
			this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		}

		
		
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	/**
	 * Setter that determine the evolutions of a tower. If there's no evolutions, the two types must be NOTOWER.
	 * @param type1
	 * @param type2
	 */
	public void setEvolutions(TowerManager.TowerTypes type1, TowerManager.TowerTypes type2){
		this.evolutions.add(type1);
		this.evolutions.add(type2);
	}
	
	/**
	 * Getter - Array with one or two elements the two types of evolution
	 * @return the evolutions
	 */
	public ArrayList<TowerManager.TowerTypes> getEvolutions() {
		return evolutions;
	}
	
    /**
     * Getter range
     * @return
     * @see 
     */	
	public int getRange(){
		return range;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
		if(this.isActivated){
			//Start the thread
			this.thread = new Thread(this);
	        thread.start();       
		}
	}
	
	public void run()
	{
		 if(isActivated)
		 {
			 try{
				Thread.sleep(100);
				this.isActivated = false;
		 	}catch(Exception e){e.printStackTrace();}
		 }
	}

	public void setRange(int range) {
		this.range = range;
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
			((SceneView) view).towerClicked(id, position, playerType, towerType,evolutions);
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

	public void setPlayerType(PlayerType newPlayerType, boolean clickable){
		this.playerType = newPlayerType;
		setTowerType(this.towerType);
		setClickable(clickable);
	}
}
