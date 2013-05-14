/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 26 avr. 2013
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
 * <b>Class - BaseSprite</b></br>
 * <p>The BaseSprite class represents the base images displayed on the SceneView</p>
 * <b>Creation :</b> 26/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class BaseSprite extends Sprite{
	private int amount;
	private TextInfoSprite textAmount;

	/**
	 * 
	 */
	public BaseSprite(SceneView scene, int id, Point position, boolean clickable, PlayerType playerType, int width, int height, int amount) {
		super(scene, id, position,clickable,playerType,width,height);
		
		this.amount = amount;
		
		//The amount will be display under the base
		Point textPosition = new Point(position.x, position.y + 5 +(height/2));
		textAmount = new TextInfoSprite(scene, this.id, textPosition, false, playerType, 25, 25);
		textAmount.setText(""+amount);
		
		//Loading the base image (different one according the tower type and player)
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
		else if(playerType == PlayerType.NEUTRAL){
			fileName +="Neutral/";
		}
		
		fileName += "base.png";
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//All the bases are clickable 
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
	       public void mouseReleased(MouseEvent me) { 
	    	   myMouseReleased(me);
           } 
         });
	}
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		textAmount.setText(""+amount);
	}

	public TextInfoSprite getTextAmount(){
		return textAmount;
	}

	/**
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
			((SceneView) view).baseClicked(id, position, playerType);
	}
	private void myMouseReleased(MouseEvent me) {
		((SceneView) view).attackBase(position, playerType);
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
	
	/**
	 * Reset the playerType of a base
	 * @param newPlayerType
	 */
	public void setPlayerType(PlayerType newPlayerType){
		this.playerType = newPlayerType;
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
		else if(playerType == PlayerType.NEUTRAL){
			fileName +="Neutral/";
		}
		
		fileName += "base.png";
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
}
