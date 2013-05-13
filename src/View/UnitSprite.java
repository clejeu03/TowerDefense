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

import GameEngine.GameManager;
import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - UnitSprite</b></br>
 * <p>The UnitSprite class represents the units images displayed on the ScenView</p>
 * <b>Creation :</b> 03/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
@SuppressWarnings("serial")
public class UnitSprite  extends Sprite{
	private boolean flipped;
	private int amount;
	private TextInfoSprite textAmount;

	/**
	 * 
	 */
	public UnitSprite(SceneView scene, int id, Point position, PlayerType playerType, int amount) {
		super(scene, id, position,false,playerType,32,32);
		
		this.amount = amount;
		flipped = false;
		
		//The amount will be display above the Unit
		Point textPosition = new Point(position.x, position.y -20);
		textAmount = new TextInfoSprite(scene, this.id, textPosition, false, playerType, 25, 25);
		textAmount.setText(""+this.amount);
	
		
		//Loading the unit image (different one according the player)
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
		
		fileName += "unit.png";
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	
	public void setAmount(int amount) {
		this.amount = amount;	
		textAmount.setText(""+amount);
	}
	
	public TextInfoSprite getTextAmount(){
		return textAmount;
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
		else if(flipped){
		g.drawImage (image, 
				32, 0, 0, 32,
	            0, 0, 32, 32,
	            this);
	    }
    }
}
