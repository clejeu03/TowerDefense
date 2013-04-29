/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 29 avr. 2013
 */
package View;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.GameManager;

/**
 * Project - TowerDefense</br>
 * <b>Class - EnemySprite</b></br>
 * <p>The EnemySprite class represents the enemy images displayed on the PlayMenu</p>
 * <b>Creation :</b> 29/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
public class EnemySprite extends Sprite{

	/**
	 * 
	 */
	public EnemySprite(PlayMenu menu, Point position, boolean clickable, int playerId, int width, int height) {
		super(menu, position,clickable,playerId,width,height);
		
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
	}
	
	public void resetImage(int id){
		playerId = id;
		
		//Loading the new image
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
		
		revalidate();
		repaint();
	}

}
