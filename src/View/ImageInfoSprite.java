/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 8 mai 2013
 */
package View;

import java.awt.Point;

import GameEngine.GameManager;
import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - ImageInfoSprite</b></br>
 * <p>The ImageInfoSprite class represents the simple, non-cliquable image displayed around the view</p>
 * <b>Creation :</b> 08/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
public class ImageInfoSprite extends Sprite{

	
	/**
	 * 
	 */
	public ImageInfoSprite(MainViews view, Point position, PlayerType playerType, int width, int height) {
		super(view, -1, position,false,playerType,width,height);
	}

}
