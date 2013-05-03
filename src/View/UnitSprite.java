/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 3 mai 2013
 */
package View;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
public class UnitSprite  extends Sprite{
	private boolean flipped;

	/**
	 * 
	 */
	public UnitSprite(SceneView scene, Point position, boolean clickable, PlayerType playerType, int width, int height) {
		super(scene, position,clickable,playerType,width,height);
		
		flipped = false;
		
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
		
		fileName += "starter.png";
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	
	
    public void setFlipped(boolean flipped) {
		this.flipped = flipped;
		revalidate();
		repaint();
	}

	/**
     * Draw the Sprite
     */
    @Override
	public void paintComponent(Graphics g){
		if(!flipped) g.drawImage(image, 0, 0,this);
	    //Flip the image horizontally
		else if(true){
		g.drawImage (image, 
				image.getWidth(this), 0, 0, image.getHeight(this),
	            0, 0, image.getWidth(this), image.getHeight(this),
	            this);
	    }
	  }  


}
