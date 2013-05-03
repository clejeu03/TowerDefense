/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 27 avr. 2013
 */
package View;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameEngine.GameManager;
import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - StarterSprite</b></br>
 * <p>The StarterSprite class represents the Starters images displayed on the PlayMenu</p>
 * <b>Creation :</b> 27/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see PlayMenu
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class StarterSprite extends Sprite{
	private Image imageOver;
	private boolean over;
	private boolean chosen;

	/**
	 * 
	 */
	public StarterSprite(PlayMenu menu, Point position, boolean clickable, PlayerType playerType, int width, int height) {
		super(menu, position,clickable,playerType,width,height);
		
		over = false;
		chosen = false;
		
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
		
		String fileNameOver = new String(fileName);
		
		fileNameOver += "starter.png";
		fileName += "base.png";
		
		try {	
		      image = ImageIO.read(new File(fileName));
		      imageOver = ImageIO.read(new File(fileNameOver));
		  
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
         });
	}
	
	
	/**
	 * Setter - chosen
	 * @param chosen
	 */
	public void setChosen(boolean chosen) {
		this.chosen = chosen;
		//Repaint the panel
    	revalidate();
    	repaint();	
	}


	/**
	 *  Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		((PlayMenu) view).starterClicked(playerType);
		chosen = true;
	}
	
	/**
	 * Event "the mouse has entered the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseEntered(MouseEvent me) {
		//Change the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		over = true;
	
		//Repaint the panel
    	revalidate();
    	repaint();
	}
	
	/**
	 * Event "the mouse has quit the zone" handler
	 * @param me : MouseEvent
	 */
	private void myMouseExited(MouseEvent me) {
		//Change the cursor aspect
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		over = false;
				
		//Repaint the panel
    	revalidate();
    	repaint();
	}
	
    /**
     * Draw the Sprite
     */
    @Override
	public void paintComponent(Graphics g){
		if((!over)&&(!chosen)) g.drawImage(image, 7, 7,this);
		else if((over)||chosen) g.drawImage(imageOver, 0, 0,this);
	  }  

}
